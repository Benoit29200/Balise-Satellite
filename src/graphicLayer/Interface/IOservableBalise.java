package graphicLayer.Interface;

import graphicLayer.ObjetVue.BaliseObject;
import graphicLayer.ObjetVue.SatelliteObject;


public interface IOservableBalise {
    void addObserver(BaliseObject o);
    void deleteObserver(BaliseObject o);
}

