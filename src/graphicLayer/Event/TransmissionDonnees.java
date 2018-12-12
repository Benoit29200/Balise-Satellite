package graphicLayer.event;

import graphicLayer.modele.Entite;

public class TransmissionDonnees implements Event {

    private Entite source;


    public TransmissionDonnees() {
    }


    @Override
    public Entite getSource() {
        return source;
    }

    @Override
    public void setSource(Entite source) {
        this.source = source;
    }

    public void doEvent(Entite e){
            source.receptionDonnees();
            e.receptionDonnees();
    }


}
