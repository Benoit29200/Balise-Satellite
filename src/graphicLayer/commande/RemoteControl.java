package graphicLayer.commande;

public class RemoteControl {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand(){
        this.command.execute();
    }
}
