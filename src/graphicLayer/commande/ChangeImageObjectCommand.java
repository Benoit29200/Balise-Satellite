package graphicLayer.commande;

import graphicLayer.object.EntiteVue;

import java.awt.*;

public class ChangeImageObjectCommand implements Command {

    EntiteVue object;
    String path;

    public ChangeImageObjectCommand(EntiteVue object, String path){
        this.object = object;
        this.path = path;
    }

    public void execute(){
        Image image = Toolkit.getDefaultToolkit().getImage(this.path);
        this.object.setImage(image);
    }
}
