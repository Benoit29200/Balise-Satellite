package graphicLayer.announcer;

import graphicLayer.event.Event;

public interface Observer {
	void receive(Event e);
}
