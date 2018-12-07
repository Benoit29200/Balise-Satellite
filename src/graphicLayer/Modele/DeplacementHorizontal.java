package graphicLayer.Modele;

import graphicLayer.Properties;

public class DeplacementHorizontal extends Deplacement {

    public void deplace(Entite e){

        Position p = e.getPosition();

        int vitesseSatellite = Integer.parseInt(Properties.getInstance().getPropertie("vitesseSatellite"));

        int widthScreen = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.width"));
        p.setWidth((p.getWidth()+vitesseSatellite) % widthScreen);
    }
}
