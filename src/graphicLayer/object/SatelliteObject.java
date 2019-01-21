
package graphicLayer.object;

import graphicLayer.announcer.Observer;
import graphicLayer.modele.Entite;
import graphicLayer.modele.Satellite;

import java.awt.*;


public class SatelliteObject extends EntiteVue implements Observer {

	public SatelliteObject(Color color, Point pos, Dimension dim) {
		super(color, pos, dim);
		image = Toolkit.getDefaultToolkit().getImage("satellite.png");
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void draw(Graphics g) {

		Color c = g.getColor();
		g.setColor(color);
		//g.fillRect(bounds.x,bounds.y,bounds.width, bounds.height);
		g.setColor(c);
		g.drawImage(image,bounds.x,bounds.y,null);
		super.draw(g);
	}

	public void update(Entite s, Object obj)
	{
		Point p = new Point(s.getPosition().getWidth(),s.getPosition().getHeight());
		this.setPosition(p);
	}

}
