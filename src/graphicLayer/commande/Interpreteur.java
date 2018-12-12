package graphicLayer.commande;

import graphicLayer.environment.Environment;
import graphicLayer.event.DeplacementVertical;
import graphicLayer.modele.Balise;
import graphicLayer.modele.Satellite;
import graphicLayer.object.BaliseObject;
import graphicLayer.object.SatelliteObject;

import java.awt.*;
import java.util.Scanner;

public class Interpreteur extends Thread {

    Environment environment;
    RemoteControl remote;
    ChangeImageObjectCommand changeImageObject;
    public Interpreteur(Environment environment){
        super();
        this.environment = environment;
        this.remote = new RemoteControl();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);



        String nomBalise="";

        while(true){

            int choixPrincipal=0;
            int choixSecondaire=0;
            int choixTernaire=0;

            System.out.println("******  MENU  *******\n");
            System.out.print(" 1- Ajouter balise\n" +
                    " 2- Ajouter satellite\n" +
                    " 3- Supprimer élement\n" +
                    "-1- Quitter l'application\n\n> ");

            choixPrincipal = sc.nextInt();

            switch (choixPrincipal) {
                case 1: { // ajout balise
                    int posX, posY, dim;
                    String nom;
                    System.out.print("Nom de la balise: ");
                    nom = sc.next();

                    System.out.print("Position X: ");
                    posX = sc.nextInt();
                    System.out.print("Position Y: ");
                    posY = sc.nextInt();
                    System.out.print("Dimension Balise: ");
                    dim = sc.nextInt();
                    //System.out.print("\n >-- Nom de la nouvelle balise ?\n\n> ");
                    //nomBalise = sc.nextLine();
                    remote.setCommand(new AjoutBaliseCommand(this.environment, nom, posX, posY, dim));
                    remote.executeCommand();
                    sc.nextLine();
                    break;

                }
                case 2: { // ajout satellite
                    int posX, posY, dim;
                    String nom;
                    System.out.print("Nom du satellite: ");
                    nom = sc.next();
                    System.out.print("Position X: ");
                    posX = sc.nextInt();
                    System.out.print("Position Y: ");
                    posY = sc.nextInt();
                    System.out.print("Dimension Satellite: ");
                    dim = sc.nextInt();
                    remote.setCommand(new AjoutSatelliteCommand(this.environment, nom, posX, posY, dim));
                    remote.executeCommand();
                    sc.nextLine();
                    break;
                }


                case 3: { // Supprimer entité
                    String nom;
                    System.out.print("Nom de l'entité à supprimer: ");
                    nom = sc.next();
                    remote.setCommand(new SuppressionEntiteCommand(this.environment, nom));
                    remote.executeCommand();
                    sc.nextLine();
                    break;

                }


                case -1: { // Fermer l'application
                    System.exit(0);
                }

                default: {
                }
            }

        }


    }
}
