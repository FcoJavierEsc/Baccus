package com.utad.baccus.controller.fragment;

import java.util.List;

import android.R.animator;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.utad.baccus.R;
import com.utad.baccus.model.Wine;
import com.utad.baccus.model.Winehouse;

public class WineListFragment extends Fragment {
	private OnWineSelectedListener mOnWineSelectedListener = null;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.fragment_wine_list, container,
				false);

		final ListView list = (ListView) root.findViewById(R.id.wine_list);

		AsyncTask<Void, Void, List<Wine>> asyncTask = new AsyncTask<Void, Void, List<Wine>>() {
			private ProgressDialog progressDialog = null;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				progressDialog = new ProgressDialog(getActivity());
				progressDialog.setTitle("Descargando vinos...");
				progressDialog.setIndeterminate(true);
				progressDialog.setCancelable(false);
				progressDialog.show();
			}

			@Override
			protected List<Wine> doInBackground(Void... params) {
				// Esto bloquea!!
				Winehouse winehouse = Winehouse.getInstance();
				return winehouse.cloneWineList();
			}

			@Override
			protected void onPostExecute(List<Wine> result) {
				super.onPostExecute(result);
				list.setAdapter(new WineRowAdapter(getActivity(),
						R.layout.list_item_wine_row, result));
				progressDialog.dismiss();
			}
		};

		asyncTask.execute();

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mOnWineSelectedListener != null) {
					mOnWineSelectedListener.onWineSelected(position);
				}
			}
		});

		return root;
	}

	public void setOnWineSelectedListener(
			OnWineSelectedListener onWineSelectedListener) {
		mOnWineSelectedListener = onWineSelectedListener;
	}

	public interface OnWineSelectedListener {
		void onWineSelected(int index);
	}

	private class WineRowAdapter extends ArrayAdapter<Wine> {
		private int mLayout;

		public WineRowAdapter(Context context, int resource, List<Wine> wines) {
			super(context, resource, wines);
			mLayout = resource;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View wineRow = inflater.inflate(mLayout, parent, false);

			final ImageView wineImage = (ImageView) wineRow
					.findViewById(R.id.wine_image);
			wineImage.setVisibility(View.INVISIBLE);

			final Handler downloadImageHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					if (wineImage != null) {
						wineImage.setImageBitmap((Bitmap) msg.obj);
						wineImage.setVisibility(View.VISIBLE);
						//Animation
						Animation loadAnim= AnimationUtils.loadAnimation(getContext(), R.anim.image_loader);
						wineImage.startAnimation(loadAnim);
						wineRow.findViewById(R.id.loading).setVisibility(
								View.GONE);
					}
				}
			};

			final Wine wine = getItem(position);

			Thread downloader = new Thread(new Runnable() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.obj = wine.getBitmap(getContext());
					downloadImageHandler.sendMessage(msg);
				}
			});

			downloader.start();

			TextView wineName = (TextView) wineRow.findViewById(R.id.wine_name);
			wineName.setText(wine.getName());

			TextView winehouse = (TextView) wineRow
					.findViewById(R.id.winehouse);
			winehouse.setText(wine.getWinehouse());

			return wineRow;
		}

	}
}
