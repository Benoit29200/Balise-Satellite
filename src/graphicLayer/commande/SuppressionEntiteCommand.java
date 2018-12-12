package graphicLayer.commande;

import graphicLayer.environment.Environment;
import graphicLayer.event.DeplacementVertical;
import graphicLayer.modele.Balise;
import graphicLayer.object.BaliseObject;
import graphicLayer.object.EntiteVue;

import java.awt.*;

public class SuppressionEntiteCommand implements Command {

    private Environment environment;
    private String nom;


    public SuppressionEntiteCommand(Environment environment, String nom){
        this.environment = environment;
        this.nom = nom;

    }

    @Override
    public void execute() {
       if(!this.environment.getEntites().containsKey(nom)){
           System.out.println("\n>> Balise introuvable ! <<\n");
           return;
       }

        EntiteVue vue= this.environment.getEntitesVue().get(nom);
       this.environment.getJc().remove(vue);

       this.environment.getEntitesVue().remove(nom);
       this.environment.getEntites().remove(nom);
        System.out.println("\n>> Balise supprimée ! <<\n");


    }


}
