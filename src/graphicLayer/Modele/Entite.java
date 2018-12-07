package graphicLayer.Modele;
import graphicLayer.Interface.IOservable;

public abstract class Entite {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
