package graphicLayer.announcer;

import graphicLayer.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Announcer {
	Map<Class<? extends Event>, List<Observable>> registrationIndex;
	
	public Announcer() {
		registrationIndex = new HashMap<>();
	}

	public void register(Observable o, Class<? extends Event> eventClass) {
		List<Observable> l = registrationIndex.get(eventClass);
		if (l == null) {
			l = new ArrayList<Observable>();
			registrationIndex.put(eventClass, l );
		}
		l.add(o);
	}

	public void announce(Event anEvent) {
		Class<?> eventClass = anEvent.getClass();
		List<Observable> l = registrationIndex.get(eventClass);
		if (l == null) return;
		Iterator<Observable> itor =  l.iterator();
		while (itor.hasNext()) {
			Observable current = itor.next();
			current.receive(anEvent);
		}
	}

	public void unregister (Observable o, Class<? extends Event> eventClass) {
		List<Observable> l = registrationIndex.get(eventClass);
		Iterator<Observable> itor =  l.iterator();
		while (itor.hasNext()) {
			Observable current = itor.next();
			if (o == current) itor.remove();
		}
		if (l.isEmpty()) {
			registrationIndex.remove(eventClass);
		}
	}
	

}