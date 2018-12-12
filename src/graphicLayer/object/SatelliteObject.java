
package graphicLayer.object;

import graphicLayer.modele.Satellite;

import java.awt.*;


public class SatelliteObject extends EntiteVue {

	public SatelliteObject(Color color, Point pos, Dimension dim) {
		super(color, pos, dim);		
	}

	public void draw(Graphics g) {

		Color c = g.getColor();
		g.setColor(color);
		//g.fillRect(bounds.x,bounds.y,bounds.width, bounds.height);
		g.setColor(c);
		Image image = Toolkit.getDefaultToolkit().getImage("satellite.png");
		g.drawImage(image,bounds.x,bounds.y,null);
		super.draw(g);
	}

	public void update(Satellite obs, Object obj)
	{
		//TODO

		Point p = new Point(obs.getPosition().getWidth(),obs.getPosition().getHeight());
		this.setPosition(p);
	}

}
