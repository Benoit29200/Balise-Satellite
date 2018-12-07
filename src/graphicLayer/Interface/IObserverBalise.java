package graphicLayer.Interface;

import graphicLayer.Modele.Balise;
import graphicLayer.Modele.Satellite;

public interface IObserverBalise {

    void update(Balise obs, Object obj);
}
