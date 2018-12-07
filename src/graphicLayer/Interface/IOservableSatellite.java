package graphicLayer.Interface;

import graphicLayer.ObjetVue.SatelliteObject;


public interface IOservableSatellite {
    void addObserver(SatelliteObject o);
    void deleteObserver(SatelliteObject o);
}

