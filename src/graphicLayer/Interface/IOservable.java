package graphicLayer.Interface;

import graphicLayer.Modele.Balise;


public interface IOservable {
    void addObserver(Balise o);
    void deleteObserver(Balise o);
}

