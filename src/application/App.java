package application;

import graphicLayer.commande.Interpreteur;
import graphicLayer.environment.Environment;

public class App {


    public static void main(String[] args){



        Environment environment = new Environment();

        Interpreteur i = new Interpreteur(environment);
        i.start();

        environment.run();


    }
}
