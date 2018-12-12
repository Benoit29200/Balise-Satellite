package graphicLayer.event;

import graphicLayer.modele.Entite;

public interface Event {
	void setSource(Entite src);
	Entite getSource();

	void doEvent(Entite e);

}
