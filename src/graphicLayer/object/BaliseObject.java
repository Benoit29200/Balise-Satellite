package graphicLayer.object;

import graphicLayer.modele.Balise;

import java.awt.*;

public class BaliseObject extends EntiteVue {

	public BaliseObject(Color color, Point pos, Dimension dim) {
		super(color, pos, dim);
		image = Toolkit.getDefaultToolkit().getImage("balise.png");
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		//g.fillOval(bounds.x,bounds.y,bounds.height,bounds.width);
		g.drawImage(image,bounds.x,bounds.y,null);
		g.setColor(c);
		super.draw(g);
	}

	public void update(Balise b, Object o){
		Point p = new Point(b.getPosition().getWidth(),b.getPosition().getHeight());
		this.setPosition(p);
	}

	public void changeColorForTransmission(){
		super.setColor(Color.WHITE);
	}

	public void changeColorForSearching(){
		super.setColor(Color.GREEN);
	}



}
