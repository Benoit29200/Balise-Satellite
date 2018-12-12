
package graphicLayer.object;

import graphicLayer.vue.BaliseWorld;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class EntiteVue {
	protected BaliseWorld world;
	protected Rectangle bounds;
	protected Color color;
	protected List<EntiteVue> submorphs = new ArrayList<EntiteVue>();

	public EntiteVue(Color color, Point pos, Dimension dim) {
		this.color = color;
		this.bounds = new Rectangle(dim);
		setPosition(pos);

	}

	public void setWorld(BaliseWorld w) {
		world = w;
	}

	public void setColor (Color c) {
		color = c;
		if (world != null)
			world.repaint();		
	}
	
	public void draw(Graphics g) {
		Iterator<EntiteVue> itor = submorphs.iterator();
		while (itor.hasNext()) {
			EntiteVue m = itor.next();
			m.draw(g);
		}
	}

	public Rectangle getBounds() {
		return (Rectangle) bounds.clone();
	}

	public void addSubmorph(EntiteVue m) {
		if (submorphs.contains(m))
			return;
		submorphs.add(m);
	}

	public Point getPosition() {
		Point p = bounds.getLocation();
		return p;
	}

	public void setPosition(Point p) {
		bounds.x = p.x;
		bounds.y = p.y;
		if (world != null)
			world.repaint();
	}

	public void setX(int x) {
		Point p = getPosition();
		setPosition(new Point(x, p.y));
	}

	public int getX() {
		return (getPosition().x);
	}

	public void setY(int y) {
		Point p = getPosition();
		setPosition(new Point(p.x, y));
	}

	public int getY() {
		return (getPosition().y);
	}

	public void moveRight(int gap) {
		setX(getX() + gap);
	}

	public void moveLeft(int gap) {
		setX(getX() - gap);
	}

	public void moveUp(int gap) {
		setY(getY() - gap);
	}

	public void moveDown(int gap) {
		setY(getY() + gap);
	}

}
