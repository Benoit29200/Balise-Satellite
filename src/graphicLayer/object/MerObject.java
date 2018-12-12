package graphicLayer.object;

import java.awt.*;

public class MerObject extends EntiteVue {

    public MerObject(Color color, Point pos, Dimension dim) {
        super(color, pos, dim);
    }

    public void draw(Graphics gr) {
        Color c = gr.getColor();
         gr.setColor(color);
        //gr.fillRect(bounds.x,bounds.y,bounds.height,bounds.width);
        Image image = Toolkit.getDefaultToolkit().getImage("mer.jpg");
        gr.drawImage(image,bounds.x,bounds.y,null);
        gr.setColor(c);
        super.draw(gr);
    }
}
