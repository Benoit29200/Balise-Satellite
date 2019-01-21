package graphicLayer.event;

import graphicLayer.modele.Entite;
import graphicLayer.Properties;

public class DeplacementHorizontal implements Event {

    private Entite source;

    public DeplacementHorizontal() { }

    @Override
    public Entite getSource() {
        return source;
    }

    @Override
    public void setSource(Entite source) {
        this.source = source;
    }

    public void doEvent(Entite e){

        // on récupère la largeur de l'écran dans le fichier properties
        int widthScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.width"));

        // on met à jour la position en x de l'entité
        e.getPosition().setWidth((e.getPosition().getWidth()+e.getVitesse()) % widthScreen);
    }
}
