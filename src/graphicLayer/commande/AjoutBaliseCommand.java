package graphicLayer.commande;

import graphicLayer.environment.Environment;
import graphicLayer.event.DeplacementVertical;
import graphicLayer.modele.Balise;
import graphicLayer.object.BaliseObject;

import java.awt.*;

public class AjoutBaliseCommand implements Command {

    private Environment environment;
    private String nom;
    private int posX;
    private int posY;
    private int dim;

    public AjoutBaliseCommand(Environment environment, String nom, int posX, int posY, int dim){
        this.environment = environment;
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
        this.dim = dim;
    }

    @Override
    public void execute() {
        if(this.environment.getEntites().containsKey(nom)) System.out.println("\n>> Nom de la balise déja utilisé <\n");
        Balise balise = new Balise(this.posX, this.posY);
        this.environment.getEntites().put(nom,balise);

        BaliseObject baliseVue = new BaliseObject(Color.GREEN,new Point(this.posX,this.posY),new Dimension(dim,dim));
        this.environment.getEntitesVue().put(nom,baliseVue);

        this.environment.getAnnouncer().register(balise, DeplacementVertical.class);
        balise.setObserverVue(baliseVue);
        this.environment.getJc().add(baliseVue);
        System.out.println("\n>> Balise ajoutée ! <<\n");
    }


}
