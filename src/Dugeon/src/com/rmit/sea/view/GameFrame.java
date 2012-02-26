package com.rmit.sea.view;

import com.rmit.sea.controller.GameKeyListener;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.gameengine.charactermodel.player.PlayerLoader;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements WindowListener {

    private GameView gameView;
    private PlayerInformationPanel playerInformationPanel;
    private InformationPanel informationPanel;
    private final GameKeyListener gameKeyListener;
    private final WelcomePanel welcomePanel;
    private final GameEngine gameEngine;
    private Component currentCenterPanel;
    private BufferStrategy strategy;

    public GameFrame(GameEngine gameEngine,
            GameView gameView, InformationPanel informationPanel,
            PlayerInformationPanel playerInfo, WelcomePanel welcomePanel) {
        this.gameEngine = gameEngine;
        this.gameView = gameView;
        this.informationPanel = informationPanel;
        this.playerInformationPanel = playerInfo;
        this.gameKeyListener = gameEngine.getGameKeyListener();
        this.welcomePanel = welcomePanel;
        addWindowListener(this);
        setLayout(new BorderLayout());
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public InformationPanel getInformationPanel() {
        return informationPanel;
    }

    public void setInformationPanel(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
    }

    public PlayerInformationPanel getPlayerInformationPanel() {
        return playerInformationPanel;
    }

    public void setPlayerInformationPanel(PlayerInformationPanel playerInformationPanel) {
        this.playerInformationPanel = playerInformationPanel;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public GameKeyListener getGameKeyListener() {
        return gameKeyListener;
    }

    public WelcomePanel getWelcomePanel() {
        return welcomePanel;
    }

    public Component getCurrentCenterPanel() {
        return currentCenterPanel;
    }

    /**
     * setup game for player to play
     * calling gameEngine start game here
     * @param playerLoader
     */
    public void startGame(final PlayerLoader playerLoader) {
        removeCenterPanel(gameView);
        add(informationPanel, BorderLayout.EAST);
        add(playerInformationPanel, BorderLayout.SOUTH);
        new Thread(new Runnable() {

            @Override
            public void run() {
                informationPanel.initPanel();
                gameEngine.startGame(playerLoader);
            }
        }).start();
        setFocusable(true);
        requestFocus();
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        

    }

    public void addCenterPanel(Component panel) {
        add(panel, BorderLayout.CENTER);
        if (panel instanceof KeyListener) {
            addKeyListener((KeyListener) panel);
        } else {
            addKeyListener(gameKeyListener);
        }
        currentCenterPanel = panel;
    }

    public void removeCenterPanel() {
        remove(currentCenterPanel);
        if (currentCenterPanel instanceof KeyListener) {
            removeKeyListener((KeyListener) currentCenterPanel);
        } else {
            removeKeyListener(gameKeyListener);
        }
        currentCenterPanel = null;
    }
    public void removeInformationPanel(){
        remove(informationPanel);
    }


    /**
     * remove the old panel staying in the middle of the frame and add the newPanel in
     * @param newPanel the new panel to add in
     */
    public void removeCenterPanel(Component newPanel) {
        remove(currentCenterPanel);
        if (currentCenterPanel instanceof KeyListener) {
            removeKeyListener((KeyListener) currentCenterPanel);
        } else {
            removeKeyListener(gameKeyListener);
        }
        addCenterPanel(newPanel);
    }

    /**
     * setup necessary UI for starting the game
     */
    public final void createGame() {
        addCenterPanel(welcomePanel);

        setFocusable(true);
        requestFocus();
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public void displayGameView() {
        addCenterPanel(gameView);
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
         gameEngine.saveGame(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
