package com.rmit.sea.gameengine.command;

import com.rmit.sea.view.ConsoleView;

public class QuitCommand implements Command {

    private boolean executed;

    public QuitCommand() {
        executed = false;
    }

    @Override
    public void execute() {
        ConsoleView.out("Character say bye bye");
        executed = true;
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public String toString() {
        return "Player quits the game.";
    }
}
