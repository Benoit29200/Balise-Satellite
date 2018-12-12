package graphicLayer.modele;

import graphicLayer.announcer.Observer;
import graphicLayer.environment.Environment;
import graphicLayer.event.Event;
import graphicLayer.object.SatelliteObject;
import graphicLayer.Properties;


public class Satellite extends Entite implements Observer {

    private SatelliteObject observerVue;

    public Satellite(int width, int height){
        this.setPosition(new Position(width,height));
        this.vitesse = Integer.parseInt(Properties.getInstance().getPropertie("vitesseSatellite"));
    }



    public void setObserverVue(SatelliteObject observersVue) {
        this.observerVue = observersVue;
    }

    public void majVue(){
        observerVue.update(this,null);
    }

    public void receptionDonnees(){
        //System.out.println("Reception des données");
    }



    public void receive(Event e){

        if(Math.abs(e.getSource().getPosition().getWidth()-this.getPosition().getWidth()) > 30){
            return;
        }
        e.doEvent(this);
    }

    public void visit(Environment app){
        app.actionForSatellite(this);
    }
}
