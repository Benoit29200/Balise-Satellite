package graphicLayer.ObjetVue;

import graphicLayer.Interface.IObserverBalise;
import graphicLayer.Modele.Balise;

import java.awt.*;

public class BaliseObject extends EntiteVue implements IObserverBalise {

	public BaliseObject(Color color, Point pos, Dimension dim) {
		super(color, pos, dim);
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(bounds.x,bounds.y,bounds.height,bounds.width);
		g.setColor(c);
		super.draw(g);
	}

	public void update(Balise b, Object o){
		//TODO
		System.out.println(this.getPosition());
		Point p = new Point(b.getPosition().getWidth(),b.getPosition().getHeight());
		this.setPosition(p);
	}

}
