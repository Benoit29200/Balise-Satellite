package graphicLayer.Modele;

import graphicLayer.Interface.IOservable;
import graphicLayer.Interface.IOservableSatellite;
import graphicLayer.ObjetVue.SatelliteObject;

import java.util.ArrayList;

public class Satellite extends Entite implements IOservable, IOservableSatellite {

    private ArrayList<Balise> observers;
    private ArrayList<SatelliteObject> observersVue;

    public Satellite(int width, int height){
        observers = new ArrayList<>();
        observersVue = new ArrayList<>();
        this.setPosition(new Position(width,height));
    }

    public ArrayList<Balise> getObservers() {
        return observers;
    }


    public void addObserver(Balise o){
        this.observers.add(o);
    }
    public void deleteObserver(Balise o){
        if(observers.isEmpty()) new Exception("Vous essayer de supprimer un observer balise alors que la liste est vide");
        this.observers.remove(o);
    }

    public void addObserver(SatelliteObject s){
        this.observersVue.add(s);
    }

    public void deleteObserver(SatelliteObject s){
        if(observersVue.isEmpty()) new Exception("Vous essayer de supprimer un observer satellite alors que la liste est vide");
        this.observersVue.remove(s);
    }

    public void majVue(){
        for(SatelliteObject satelliteVue: observersVue){
            satelliteVue.update(this,null);
        }
    }

    public void majTransfertDonnee(){
        for(Balise balise: observers){
            balise.update(this,null);
        }
    }

    public void recevoirDonnees(){
        System.out.println("Données reçues");
    }
}
