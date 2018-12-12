package graphicLayer.object;

import java.awt.*;

public class CielObject extends EntiteVue {

    public CielObject(Color color, Point pos, Dimension dim) {
        super(color, pos, dim);
    }

    public void draw(Graphics gr) {
        Color c = gr.getColor();
         gr.setColor(color);
        gr.fillRect(bounds.x,bounds.y,bounds.height,bounds.width);
        gr.setColor(c);
        super.draw(gr);
    }
}
