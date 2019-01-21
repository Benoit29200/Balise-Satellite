package graphicLayer.announcer;

import graphicLayer.event.Event;

public interface Observable {
	void receive(Event e);
}
