package graphicLayer.announcer;

import graphicLayer.modele.Entite;
import graphicLayer.modele.Satellite;

public interface Observer {

    public void update(Entite e, Object o);
}
