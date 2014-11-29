package com.utad.baccus.controller.fragment;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.utad.baccus.R;
import com.utad.baccus.model.Wine;
import com.utad.baccus.model.Winehouse;

public class WineListFragment extends Fragment  {

	private OnWineSelectedListener mOnWineSelectedListener = null;
	private ListView mList = null;

	public void setOnWineSelectedListener(
			OnWineSelectedListener onWineSelectedListener) {
		mOnWineSelectedListener = onWineSelectedListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.fragment_wine_list, container,
				false);

		

		mList = (ListView) root.findViewById(R.id.wine_list);
		
		AsyncTask<Void, Void, List<Wine>>  asyncTask = new AsyncTask<Void, Void , List<Wine>>(){

			private ProgressDialog proDialog = null;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				proDialog = new ProgressDialog(getActivity());
				proDialog.setTitle("Descargando");
				proDialog.setIndeterminate(true);
				proDialog.setCancelable(false);
				proDialog.show();
			
			}
			
			
			@Override
			protected List<Wine> doInBackground(Void... params) {
				//bloqueante
				Winehouse winehouse = Winehouse.getInstance();
				return winehouse.cloneWineList();
			}
            //se ejecuta en el hilo principal
			@Override
			protected void onPostExecute(List<Wine> result) {

				super.onPostExecute(result);
				proDialog.dismiss();
				mList.setAdapter(new ArrayAdapter<Wine>(getActivity(),
						android.R.layout.simple_spinner_dropdown_item, Winehouse
								.getInstance().cloneWineList()));
				
			}
			
			
		};

		asyncTask.execute();

		mList.setOnItemClickListener(new OnItemClickListener() {

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

	public interface OnWineSelectedListener {

		void onWineSelected(int index);

	}

}
