package graphicLayer.modele;

import graphicLayer.announcer.Observable;
import graphicLayer.announcer.Observer;
import graphicLayer.environment.Environment;

public abstract class Entite implements Observable {

    private Position position;
    protected int vitesse;
    protected boolean pretATransmettre;

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public int getVitesse(){
        return this.vitesse;
    }
    public void inverseVitesse(){
        vitesse = -vitesse;
    }

    public boolean isPretATransmettre() {
        return pretATransmettre;
    }
    public void setPretATransmettre(boolean pretATransmettre) {
        this.pretATransmettre = pretATransmettre;
    }

    /////////////////////////////
    ///////ABSTRACT METHODE//////
    /////////////////////////////
    public abstract void receptionDonnees();
    public abstract void visit(Environment app);
    public abstract void majVue();
}
