package graphicLayer.modele;

import graphicLayer.announcer.Observable;
import graphicLayer.environment.Environment;
import graphicLayer.event.Event;
import graphicLayer.event.TransmissionDonnees;
import graphicLayer.object.BaliseObject;
import graphicLayer.Properties;



public class Balise extends Entite {

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

    @Override
    public void receive(Event e){
        // si c'est pas moi qui me suis déplacé, je ne fais rien
        if(e.getSource() != this) return;
        // sinon j'applique l'évènement sur moi
        e.doEvent(this);
        // comme je me suis déplacé, je l'indique à mon observer
        observerVue.update(this,null);
    }

    public void visit(Environment app){
        app.actionForBalise(this);
    }
}
