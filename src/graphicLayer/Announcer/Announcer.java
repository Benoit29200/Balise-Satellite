package graphicLayer.announcer;

import graphicLayer.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Announcer {
	Map<Class<? extends Event>, List<Observer>> registrationIndex;
	
	public Announcer() {
		registrationIndex = new HashMap<>();
	}
	
	public void register(Observer o, Class<? extends Event> eventClass) {
		List<Observer> l = registrationIndex.get(eventClass);
		if (l == null) {
			l = new ArrayList<Observer>();
			registrationIndex.put(eventClass, l );
		}
		l.add(o);
	}

	public void unregister (Observer o, Class<? extends Event> eventClass) {
		List<Observer> l = registrationIndex.get(eventClass);
		Iterator<Observer> itor =  l.iterator();
		while (itor.hasNext()) {
			Observer current = itor.next();
			if (o == current) itor.remove();
		}
		if (l.isEmpty()) {
			registrationIndex.remove(eventClass);
		}
	}
	
	public void announce(Event anEvent) {
		Class<?> eventClass = anEvent.getClass();
		List<Observer> l = registrationIndex.get(eventClass);
		if (l == null) return;
		Iterator<Observer> itor =  l.iterator();
		while (itor.hasNext()) {
			Observer current = itor.next();
			current.receive(anEvent);
		}
	}
}