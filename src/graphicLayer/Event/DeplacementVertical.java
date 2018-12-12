package graphicLayer.event;

import graphicLayer.modele.Entite;
import graphicLayer.Properties;


public class DeplacementVertical implements Event {

    private Entite source;
    @Override
    public Entite getSource() {
        return source;
    }

    @Override
    public void setSource(Entite source) {
        this.source = source;
    }

    public void doEvent(Entite e){

        // Récupération du niveau de l'eau et de la profondeur maximum de la balise à partir du fichier properties
        int niveauDeLeau = Integer.parseInt(Properties.getInstance().getPropertie("niveauDeLeau"));
        int profondeurBalise = Integer.parseInt(Properties.getInstance().getPropertie("profondeurBalise"));

        // Si la balise est toujours en dessous de l'eau, on incrémente sa position en hauteur
        if(e.getPosition().getHeight() > niveauDeLeau){
            int heightWidth = Integer.parseInt(Properties.getInstance().getPropertie("fenetre.height"));
            e.getPosition().setHeight((e.getPosition().getHeight()-e.getVitesse()) % heightWidth);
        }

        // Si la balise est au dessus de l'eau, on la fige à la surface et on lui dit qu'elle est prêt à transmettre
        if(e.getPosition().getHeight() < niveauDeLeau){
            e.getPosition().setHeight(niveauDeLeau);
            // mise à jour de l'état de transmission à true
            e.setPretATransmettre(true);
        }

        if(e.getPosition().getHeight() == niveauDeLeau){
            e.setPretATransmettre(true);

        }

        // Si la balise est au dessous de la profondeur maximum de la balise, on la fige à sa profondeur et on inverse la vitesse pour qu'elle puisse repartir à la surface
        if(e.getPosition().getHeight() > (niveauDeLeau+profondeurBalise)){
            e.getPosition().setHeight(niveauDeLeau+profondeurBalise);
            e.inverseVitesse();
        }
    }


}
