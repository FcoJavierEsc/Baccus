package com.utad.baccus.model;

import java.util.Arrays;
import java.util.List;

import com.utad.baccus.R;

public class Winehouse {
	/**
	 * Singleton
	 */
	private static Winehouse sInstance = null;

	/**
	 * Lista de vinos
	 */
	private List<Wine> mWines = null;

	/**
	 * Método para obtener la instancia del singleton
	 */
	public static Winehouse getInstance() {
		if (sInstance == null) {
			sInstance = new Winehouse();
		}

		return sInstance;
	}

	/**
	 * Constructor de la bodega
	 */
	private Winehouse() {
		// Creamos los modelos
		Wine bembibre = new Wine(
				"Bembibre",
				"Tinto",
				"http://www.dominiodetares.com/index.php/es/vinos/baltos/74-bembibrevinos",
				"Dominio de Tares",
				R.drawable.bembibre,
				5,
				"Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.");
		bembibre.addGrape("Mencía1");
		bembibre.addGrape("Mencía2");
		bembibre.addGrape("Mencía3");
		bembibre.addGrape("Mencía4");
		bembibre.addGrape("Mencía5");
		bembibre.addGrape("Mencía6");
		bembibre.addGrape("Mencía7");

		Wine vegaval = new Wine(
				"Vegaval Plata Gran Reserva 2004",
				"Tinto",
				"http://www.vegaval.com/es/",
				"Miguel Calatayud",
				R.drawable.vegaval,
				5,
				"Un vino de esmerado proceso de elaboración y larga crianza. Presenta un color rojo cereza con matices a teja y una brillante capa media alta. Nariz compleja, fina y elegante. Es excelentemente estructurado, amplio y muy sabroso. Recomendado para acompañar quesos curados, estofados y todo tipo de carnes rojas y de caza. La temperatura recomendada para servir está entre los 16º C y 18º C.");
		vegaval.addGrape("Tempranillo");

		Wine zarate = new Wine(
				"Zárate",
				"Blanco",
				"http://www.albarino-zarate.com",
				"Miguel Calatayud",
				R.drawable.zarate,
				4,
				"El albariño Zarate es un vino blanco monovarietal que pertenece a la Denominación de Origen Rías Baixas. Considerado por la crítica especializada como uno de los grandes vinos blancos del mundo, el albariño ya es todo un mito.");
		zarate.addGrape("Albariño");
		zarate.addGrape("Albariño");
		zarate.addGrape("Albariño");
		zarate.addGrape("Albariño");
		
		Wine champagne = new Wine(
				"Comtes de Champagne",
				"Otro",
				"http://www.taittinger.fr",
				"Champagne Taittinger",
				R.drawable.champagne,
				5,
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc purus. Curabitur eu velit mauris. Curabitur magna nisi, ullamcorper ac bibendum ac, laoreet et justo. Praesent vitae tortor quis diam luctus condimentum. Suspendisse potenti. In magna elit, interdum sit amet facilisis dictum, bibendum nec libero. Maecenas pellentesque posuere vehicula. Vivamus eget nisl urna, quis egestas sem. Vivamus at venenatis quam. Sed eu nulla a orci fringilla pulvinar ut eu diam. Morbi nibh nibh, bibendum at laoreet egestas, scelerisque et nisi. Donec ligula quam, semper nec bibendum in, semper eget dolor. In hac habitasse platea dictumst. Maecenas adipiscing semper rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		champagne.addGrape("Chardonnay");
		

		// Añadimos los vinos
		mWines = Arrays.asList(new Wine[] { bembibre, vegaval, zarate,
				champagne });
	}

	// Métodos para obtener un vino a partir de un índice
	public Wine getWine(int index) {
		return mWines.get(index);
	}

	public int getWineCount() {
		return mWines.size();
	}
}
