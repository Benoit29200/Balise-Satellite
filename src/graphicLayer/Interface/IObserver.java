package graphicLayer.Interface;

import graphicLayer.Modele.Satellite;

public interface IObserver {

    void update(Satellite obs, Object obj);
}
