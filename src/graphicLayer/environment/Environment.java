
package graphicLayer.environment;

import graphicLayer.announcer.Announcer;
import graphicLayer.event.TransmissionDonnees;
import graphicLayer.modele.Balise;
import graphicLayer.event.DeplacementHorizontal;
import graphicLayer.event.DeplacementVertical;
import graphicLayer.modele.Entite;
import graphicLayer.modele.Satellite;
import graphicLayer.object.*;
import graphicLayer.Properties;
import graphicLayer.vue.BaliseWorld;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Environment {

	BaliseWorld jc;

	private Announcer announcer;

	LinkedHashMap<String,Entite> entites;
	LinkedHashMap<String,EntiteVue> entitesVue;

	public BaliseWorld getJc() {
		return jc;
	}

	public Announcer getAnnouncer() {
		return announcer;
	}


	public LinkedHashMap<String,Entite> getEntites() {
		return entites;
	}


	public LinkedHashMap<String,EntiteVue> getEntitesVue() {
		return entitesVue;
	}


	public Environment(){
		this.jc = new BaliseWorld("Transmission entre balises et satellites");
		this.announcer = new Announcer();
		entites = new LinkedHashMap<>();
		entitesVue = new LinkedHashMap<>();
	}


	public void run() {

		// récupération des propriétés souhaités
		Integer widthScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.width"));
		Integer heightScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.height"));
		Integer niveauDeLeau = Integer.parseInt(Properties.getInstance().getPropertie("niveauDeLeau"));
		Integer profondeurBalise = Integer.parseInt(Properties.getInstance().getPropertie("profondeurBalise"));
		Integer dimensionSatellite = Integer.parseInt(Properties.getInstance().getPropertie("dimensionSatellite"));
		Integer dimensionBalise = Integer.parseInt(Properties.getInstance().getPropertie("dimensionBalise"));
		Integer dimensionSoleil = Integer.parseInt(Properties.getInstance().getPropertie("dimensionSoleil"));
		Integer nombreSatellite = Integer.parseInt(Properties.getInstance().getPropertie("nombreSatellite"));
		Integer nombreBalise = Integer.parseInt(Properties.getInstance().getPropertie("nombreBalise"));
		Integer positionPremierSatellite = Integer.parseInt(Properties.getInstance().getPropertie("positionPremierSatellite"));
		Integer positionPremiereBalise = Integer.parseInt(Properties.getInstance().getPropertie("positionPremiereBalise"));
		Integer hauteurSatellite = Integer.parseInt(Properties.getInstance().getPropertie("hauteurSatellite"));



		// environnement de la fenêtre
		jc.setPreferredSize(new Dimension(widthScreen,heightScreen));

		//Dimension des objets
		Dimension dimMer = new Dimension((heightScreen-niveauDeLeau),widthScreen);
		Dimension dimBalise = new Dimension(dimensionBalise,dimensionBalise);
		Dimension dimSatellite = new Dimension(dimensionSatellite,dimensionSatellite);
		Dimension dimCiel = new Dimension(niveauDeLeau,widthScreen);
		Dimension dimSoleil = new Dimension(dimensionSoleil,dimensionSoleil);

		// Création des balises et leur vue
		for(int i=positionPremiereBalise,j=1; i < widthScreen;i+=widthScreen/nombreBalise,j++){
			Balise b = new Balise(i,niveauDeLeau+profondeurBalise);
			entites.put("b"+j,b);

			BaliseObject bVue = new BaliseObject(Color.GREEN,new Point(i,niveauDeLeau+profondeurBalise),dimBalise);
			entitesVue.put("b"+j,bVue);

			announcer.register(b,DeplacementVertical.class);
			b.setObserverVue(bVue);
		}

		// Création des satellites et leur vue
		for(int i = positionPremierSatellite,j=1 ; i < widthScreen ; i+=widthScreen/nombreSatellite,j++){
			Satellite s = new Satellite(i,hauteurSatellite);
			entites.put("s"+j,s);

			SatelliteObject sVue = new SatelliteObject(Color.RED,new Point(i,hauteurSatellite),dimSatellite);
			entitesVue.put("s"+j,sVue);

			s.setObserverVue(sVue);

			announcer.register(s,DeplacementHorizontal.class);
			announcer.register(s, TransmissionDonnees.class);
		}

		// Ajout de la mer, du ciel et du soleil dans la fenêtre
		jc.add(new MerObject(Color.BLUE,new Point(0,niveauDeLeau),dimMer));
		jc.add(new CielObject(Color.CYAN,new Point(0,0),dimCiel));
		jc.add(new SoleilObject(Color.YELLOW,new Point(-dimensionSoleil/2,-dimensionSoleil/2),dimSoleil));

		// ajout des satellites à la fenêtre

		Set<String> cles = entitesVue.keySet();
		Iterator<String>  ite = cles.iterator();
		while(ite.hasNext()){
			jc.add(entitesVue.get(ite.next()));
		}

		// ouverture de la fenêtre
		jc.open();


		while (true) {

			Iterator<String> iterator = entites.keySet().iterator();
			// Déplacement des satellites
			while(iterator.hasNext()){
				Entite e = entites.get(iterator.next());
				e.visit(this);
				e.majVue();
			}


			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void actionForSatellite(Satellite s){
		DeplacementHorizontal deplacementHorizontal = new DeplacementHorizontal();
		deplacementHorizontal.setSource(s);
		announcer.announce(deplacementHorizontal);
	}

	public void actionForBalise(Balise b){
		DeplacementVertical deplacementVertical = new DeplacementVertical();
		deplacementVertical.setSource(b);

		announcer.announce(deplacementVertical);

		if(b.isPretATransmettre()){
			announcer.announce(b.getEventTransmissionDonnee());
		}
	}

}
