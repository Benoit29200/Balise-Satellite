package graphicLayer.commande;

import graphicLayer.environment.Environment;
import graphicLayer.event.DeplacementHorizontal;
import graphicLayer.event.DeplacementVertical;
import graphicLayer.event.TransmissionDonnees;
import graphicLayer.modele.Balise;
import graphicLayer.modele.Satellite;
import graphicLayer.object.BaliseObject;
import graphicLayer.object.SatelliteObject;

import java.awt.*;

public class AjoutSatelliteCommand implements Command {

    private Environment environment;
    private String nom;
    private int posX;
    private int posY;
    private int dim;

    public AjoutSatelliteCommand(Environment environment, String nom, int posX, int posY, int dim){
        this.environment = environment;
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
        this.dim = dim;
    }

    @Override
    public void execute() {
        if(this.environment.getEntites().containsKey(nom)){
            System.out.println("\n>> Nom du satellite déja utilisé <\n");
            return;
        }

        Satellite satellite = new Satellite(this.posX, this.posY);
        this.environment.getEntites().put(nom,satellite);

        SatelliteObject satelliteVue = new SatelliteObject(Color.GREEN,new Point(this.posX,this.posY),new Dimension(dim,dim));
        this.environment.getEntitesVue().put(nom,satelliteVue);

        this.environment.getAnnouncer().register(satellite, DeplacementHorizontal.class);
        this.environment.getAnnouncer().register(satellite, TransmissionDonnees.class);

        satellite.setObserverVue(satelliteVue);
        this.environment.getJc().add(satelliteVue);
        System.out.println("\n>> Satellite ajoutée ! <<\n");
    }


}
