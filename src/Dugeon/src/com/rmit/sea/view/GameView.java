package com.rmit.sea.view;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.view.renderers.GameViewRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class GameView extends JPanel implements Observer {

    private GameEngine gameEngine;
    private GameViewRenderer gameViewRenderer;
    private BufferStrategy strategy;
    private boolean update = false;

    private Image currentImage;

    public GameView() {
        setFocusable(false);
        setPreferredSize(new Dimension(Constant.GAMEVIEW_WIDTH, Constant.GAMEVIEW_HEIGHT));
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

    }

    public GameViewRenderer getGameViewRenderer() {
        return gameViewRenderer;
    }

    public void setGameViewRenderer(GameViewRenderer gameViewRenderer) {
        this.gameViewRenderer = gameViewRenderer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (update) {
            // Create buffer image
            Image offScreenImg = createImage(getWidth(), getHeight());
            Graphics offG = offScreenImg.getGraphics();
            offG.setColor(Constant.DEFAULT_BACKGROUND);
            offG.fillRect(0, 0, getWidth(), getHeight());

            long l = System.currentTimeMillis();
            if (gameEngine.gameStarted()) {
                gameViewRenderer.render(gameEngine, offG, strategy);
            }
            ConsoleView.out("paintComponent " + (System.currentTimeMillis() - l));

            currentImage = offScreenImg;
        }

        // Put the offscreen image on the screen.
        g.drawImage(currentImage, 0, 0, null);
        

    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            String flag = (String) arg;

            if (flag.equals(Constant.NOT_NOTIFY_GAMEVIEW)) {
                // He doesn't want me to repaint
                return;
            }
        } catch (NullPointerException ex) {
            // no flag, just do it
        }

        update = true;
        revalidate();
        repaint();
    }
}
