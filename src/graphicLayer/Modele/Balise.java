package graphicLayer.Modele;

import graphicLayer.Interface.IObserver;
import graphicLayer.Interface.IOservableBalise;
import graphicLayer.ObjetVue.BaliseObject;
import graphicLayer.Properties;

import java.util.ArrayList;


public class Balise extends Entite implements IObserver, IOservableBalise {

    private ArrayList<BaliseObject> observers;
    private boolean pretATransmettre = false;
    private int vitesse;

    // On récupère la vitesse de la balise à partir du fichier properties
    // et on set la position de la balise
    public Balise(int width, int height){
        this.vitesse = Integer.parseInt(Properties.getInstance().getPropertie("vitesseBalise"));
        observers = new ArrayList<>();
        Position positionBalise = new Position(width,height);
        this.setPosition(positionBalise);
    }

    public void addObserver(BaliseObject b){
        observers.add(b);
    }

    public void deleteObserver(BaliseObject b){
        if(observers.isEmpty()) new Exception("Erreur la liste des observers BaliseObject sur balise vide");
        observers.remove(b);
    }

    // on met à jour la position de la vue balise associé à la balise
    public void majVue(){
        for(BaliseObject bVue: observers){
            bVue.update(this,null);
        }
    }

    public int getVitesse() {
        return vitesse;
    }

    public void inverseVitesse(){
        vitesse = -vitesse;
    }


    public void setPretATransmettre(boolean pretATransmettre) {
        this.pretATransmettre = pretATransmettre;
    }


    // Si on est prêt à transmettre && si on est dans la fenêtre de transmission
    // Alors le satellite au dessus de la balise reçoit les données et on inverse la vitesse de la balise pour qu'elle reparte sous l'eau
    public void update(Satellite obs, Object obj)
    {
        if(pretATransmettre){
            //TODO A CONTROLLER TRES IMPORTANT
            if((Math.abs(obs.getPosition().getWidth()-this.getPosition().getWidth())) < 30){
                obs.recevoirDonnees();
                pretATransmettre = false;
                this.getPosition().setHeight(this.getPosition().getHeight()+2);
                this.inverseVitesse();
            }
        }
    }
}
