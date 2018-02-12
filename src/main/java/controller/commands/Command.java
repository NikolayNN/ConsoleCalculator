package controller.commands;

public abstract class Command {

    protected String rawCommand;

    public void setup(String rawCommand){
        this.rawCommand = rawCommand;
    }

    public abstract String perform();
}
