package graphicLayer.event;

import graphicLayer.modele.Entite;
import graphicLayer.Properties;

public class DeplacementHorizontal implements Event {

    private Entite source;


    public DeplacementHorizontal() {
    }


    @Override
    public Entite getSource() {
        return source;
    }

    @Override
    public void setSource(Entite source) {
        this.source = source;
    }

    public void doEvent(Entite e){

        int widthScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.width"));

        e.getPosition().setWidth((e.getPosition().getWidth()+e.getVitesse()) % widthScreen);

    }


}
