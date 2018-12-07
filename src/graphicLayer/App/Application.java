
package graphicLayer.App;

import graphicLayer.Modele.Balise;
import graphicLayer.Modele.DeplacementHorizontal;
import graphicLayer.Modele.DeplacementVertical;
import graphicLayer.Modele.Satellite;
import graphicLayer.ObjetVue.*;
import graphicLayer.Properties;
import graphicLayer.Vue.BaliseWorld;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Application {

	public static void main(String[] args) {

		// récupération des propriétés souhaités pour la fenetre
		Integer widthScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.width"));
		Integer heightScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.height"));

		// Récupération dun niveau de la mer et de la profondeur des balises
		Integer niveauDeLeau = Integer.parseInt(Properties.getInstance().getPropertie("niveauDeLeau"));
		Integer profondeurBalise = Integer.parseInt(Properties.getInstance().getPropertie("profondeurBalise"));

		// Récupération des dimensions des satellites et des balises et du soleil
		Integer dimensionSatellite = Integer.parseInt(Properties.getInstance().getPropertie("dimensionSatellite"));
		Integer dimensionBalise = Integer.parseInt(Properties.getInstance().getPropertie("dimensionBalise"));
		Integer dimensionSoleil = Integer.parseInt(Properties.getInstance().getPropertie("dimensionSoleil"));

		// Récupération du nombre de satellite et du nombre de balise
		Integer nombreSatellite = Integer.parseInt(Properties.getInstance().getPropertie("nombreSatellite"));
		Integer nombreBalise = Integer.parseInt(Properties.getInstance().getPropertie("nombreBalise"));

		// Récupération de la position du premier satellite
		Integer positionPremierSatellite = Integer.parseInt(Properties.getInstance().getPropertie("positionPremierSatellite"));
		Integer positionPremiereBalise = Integer.parseInt(Properties.getInstance().getPropertie("positionPremiereBalise"));

		// Récupération de la hauteur des satellites
		Integer hauteurSatellite = Integer.parseInt(Properties.getInstance().getPropertie("hauteurSatellite"));

		// Déclaration des listes de satellite et balise
		ArrayList<Satellite> satellites = new ArrayList<>();
		ArrayList<Balise> balises = new ArrayList<>();

		// Déclaration des listes de SatelliteVue, BaliseVue
		ArrayList<SatelliteObject> satelliteVues = new ArrayList<>();
		ArrayList<BaliseObject> baliseVues = new ArrayList<>();

		// environnement de la fenêtre
		BaliseWorld jc = new BaliseWorld("Transmission entre balises et satellites");
		jc.setBackground(Color.WHITE);
		jc.setPreferredSize(new Dimension(widthScreen,heightScreen));

		//Dimension des objets
		Dimension dimMer = new Dimension((heightScreen-niveauDeLeau),widthScreen);
		Dimension dimBalise = new Dimension(dimensionBalise,dimensionBalise);
		Dimension dimSatellite = new Dimension(dimensionSatellite,dimensionSatellite);
		Dimension dimCiel = new Dimension(niveauDeLeau,widthScreen);
		Dimension dimSoleil = new Dimension(dimensionSoleil,dimensionSoleil);


		// Création des balises et leur vue
		for(int i=positionPremiereBalise; i < widthScreen;i+=widthScreen/nombreBalise){
			Balise b = new Balise(i,niveauDeLeau+profondeurBalise);
			BaliseObject bVue = new BaliseObject(Color.GREEN,new Point(i,niveauDeLeau+profondeurBalise),dimBalise);
			balises.add(b);
			baliseVues.add(bVue);
			b.addObserver(bVue);
		}

		// Création des satellites et leur vue
		for(int i = positionPremierSatellite ; i < widthScreen ; i+=widthScreen/nombreSatellite){
			Satellite s = new Satellite(i,hauteurSatellite);
			SatelliteObject sVue = new SatelliteObject(Color.RED,new Point(i,hauteurSatellite),dimSatellite);
			satellites.add(s);

			for(Balise b : balises){
				s.addObserver(b);
			}
			satelliteVues.add(sVue);
			s.addObserver(sVue);
		}


		// Ajout de la mer, du ciel et du soleil dans la fenêtre
		jc.add(new MerObject(Color.BLUE,new Point(0,niveauDeLeau),dimMer));
		jc.add(new CielObject(Color.CYAN,new Point(0,0),dimCiel));
		jc.add(new SoleilObject(Color.YELLOW,new Point(-dimensionSoleil/2,-dimensionSoleil/2),dimSoleil));

		// ajout des satellites à la fenêtre
		for(SatelliteObject sVue: satelliteVues){
			jc.add(sVue);
		}

		// ajout des balises à la fenêtre
		for(BaliseObject bVue: baliseVues){
			jc.add(bVue);
		}

		// ouverture de la fenêtre
		jc.open();
		while (true) {

			// Génération des déplacements
			DeplacementHorizontal deplacementHorizontal = new DeplacementHorizontal();
			DeplacementVertical deplacementVertical = new DeplacementVertical();


			// Déplacement des satellites
			for(Satellite satellite: satellites){
				deplacementHorizontal.deplace(satellite);
				satellite.majVue();
				satellite.majTransfertDonnee();
			}

			for(Balise balise: balises){
				deplacementVertical.deplace(balise);
				balise.majVue();
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
