package graphicLayer.modele;

import graphicLayer.announcer.Observer;
import graphicLayer.environment.Environment;
import graphicLayer.event.Event;
import graphicLayer.event.TransmissionDonnees;
import graphicLayer.object.BaliseObject;
import graphicLayer.Properties;



public class Balise extends Entite implements Observer{

    BaliseObject observerVue;


    public Balise(int width, int height){
        this.vitesse = Integer.parseInt(Properties.getInstance().getPropertie("vitesseBalise"));
        this.setPosition(new Position(width,height));
        this.pretATransmettre = false;
    }


    public void setObserverVue(BaliseObject observerVue) {
        this.observerVue = observerVue;
    }


    // on met à jour la position de la vue balise associé à la balise
    public void majVue(){
        observerVue.update(this,null);
    }


    public TransmissionDonnees getEventTransmissionDonnee(){
        TransmissionDonnees eventTransmission = new TransmissionDonnees();
        eventTransmission.setSource(this);
        observerVue.changeColorForTransmission();
        return eventTransmission;
    }

    public void receptionDonnees(){
        this.getPosition().setHeight(this.getPosition().getHeight()+2);
        this.inverseVitesse();
        this.setPretATransmettre(false);
        observerVue.changeColorForSearching();
    }

    public void receive(Event e){
        if(e.getSource() != this) return;
        e.doEvent(this);
    }

    public void visit(Environment app){
        app.actionForBalise(this);
    }
}
