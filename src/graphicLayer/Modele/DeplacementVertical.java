package graphicLayer.Modele;

import graphicLayer.Properties;


public class DeplacementVertical extends Deplacement {

    public void deplace(Entite b){

        Balise balise = (Balise) b;
        Position p = balise.getPosition();

        // Récupération du niveau de l'eau et de la profondeur maximum de la balise à partir du fichier properties
        int niveauDeLeau = Integer.parseInt(Properties.getInstance().getPropertie("niveauDeLeau"));
        int profondeurBalise = Integer.parseInt(Properties.getInstance().getPropertie("profondeurBalise"));

        // Si la balise est toujours en dessous de l'eau, on incrémente sa position en hauteur
        if(p.getHeight() > niveauDeLeau){
            int heightWidth = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.height"));
            p.setHeight((p.getHeight()-balise.getVitesse()) % heightWidth);
        }

        // Si la balise est au dessus de l'eau, on la fige à la surface et on lui dit qu'elle est prêt à transmettre
        if(p.getHeight() < niveauDeLeau){
            p.setHeight(niveauDeLeau);
            // mise à jour de l'état de transmission à true
            balise.setPretATransmettre(true);

        }

        if(p.getHeight() == niveauDeLeau){
            balise.setPretATransmettre(true);


        }


        // Si la balise est au dessous de la profondeur maximum de la balise, on la fige à sa profondeur et on inverse la vitesse pour qu'elle puisse repartir à la surface
        if(p.getHeight() > (niveauDeLeau+profondeurBalise)){
            p.setHeight(niveauDeLeau+profondeurBalise);
            balise.inverseVitesse();
        }
    }
}
