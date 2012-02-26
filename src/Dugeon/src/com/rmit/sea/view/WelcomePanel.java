package com.rmit.sea.view;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.charactermodel.player.LoadNewPlayer;
import com.rmit.sea.gameengine.charactermodel.player.LoadPlayerFromServer;
import com.rmit.sea.gameengine.charactermodel.player.LoadPlayerFromServer;
import com.rmit.sea.gameengine.charactermodel.player.PlayerLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.String;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel implements KeyListener {

    private String keyPress = "";
    private String password = "";
    private int x = 400;
    private int y = 200;
    private int cursorX = x;
    private int cursorY = 200;
    private Color FIRST_CHOICE;
    private Color SECOND_CHOICE;
    private Color LAST_CHOICCE;
    private boolean pressed = false;
    private boolean isTypingPassword = false;
    private GameFrame gameFrame;

    public WelcomePanel() {
        FIRST_CHOICE = Color.GREEN;
        SECOND_CHOICE = Color.WHITE;
        LAST_CHOICCE = Color.WHITE;
        setFocusable(false);
        setPreferredSize(new Dimension(Constant.GAMEVIEW_WIDTH, Constant.GAMEVIEW_HEIGHT));
        addKey();
    }

    private void addKey() {
        addKeyListener(this);
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Constant.DEFAULT_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        addComponents(g);
    }

    private void addComponents(Graphics g) {
        g.setFont(FontResources.getInstance().getDefaultFont(Font.PLAIN, 48f));
        g.drawString("Dorfle", 320, 100);
        g.setFont(FontResources.getInstance().getDefaultFont(Font.PLAIN, 20f));
        g.drawString("Character Name: ", 200, 200);
        g.drawString("Password: ", 273, 230);


    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.setColor(Color.yellow);
        g.drawString(keyPress + "", x, y);
        String hidden = "";
        for (int i = 0; i < password.length(); i++) {
            hidden += '*';
        }
        g.drawString(hidden + "", x, y + 30);
        if (pressed) {
            cursorX += 10;
        }
        g.drawLine(cursorX, cursorY, cursorX + 10, cursorY);

        g.setColor(FIRST_CHOICE);
        g.drawString("Start Game ", 350, 280);
        g.setColor(SECOND_CHOICE);
        g.drawString("Load Game", 350, 330);
        g.setColor(LAST_CHOICCE);
        g.drawString("Exit Game", 350, 380);
        pressed = false;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (isTypingPassword) {
                if (password.length() >= 1) {
                    password = password.substring(0, password.length() - 1);
                    cursorX -= 10;
                    repaint();
                }
            } else {
                if (keyPress.length() >= 1) {
                    keyPress = keyPress.substring(0, keyPress.length() - 1);
                    cursorX -= 10;
                    repaint();
                }
            }
        } else {
            if (isTypingPassword) {
                if (password.length() < 25) {

                    if (Character.isLetterOrDigit(ke.getKeyChar())) {
                        password += ke.getKeyChar();
                        pressed = true;
                        repaint();
                    }

                }
            } else {
                if (keyPress.length() < 25) {

                    if (Character.isLetterOrDigit(ke.getKeyChar())) {
                        keyPress += ke.getKeyChar();
                        pressed = true;
                        repaint();
                    }

                }
            }
        }
        if (ke.getKeyCode() == 38 || ke.getKeyCode() == 40) {
            processArrow(ke);
            repaint();
        }
        if (isTypingPassword) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER && FIRST_CHOICE != Color.WHITE) {

                PlayerLoader playerLoader = new LoadNewPlayer(keyPress, password);
                if (playerLoader.isSuccess()) {
                    gameFrame.startGame(playerLoader);
                }
            }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER && SECOND_CHOICE != Color.WHITE) {
                PlayerLoader playerLoader = new LoadPlayerFromServer(keyPress, password);
                if (playerLoader.isSuccess()) {
                    gameFrame.startGame(playerLoader);
                }
            }
        } else {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!keyPress.isEmpty()) {

                    cursorY += 30;
                    cursorX = 400;
                    isTypingPassword = true;
                    repaint();
                }
            }
        }

        if (ke.getKeyCode() == KeyEvent.VK_ENTER && LAST_CHOICCE != Color.WHITE) {
            System.exit(0);
        }

    }

    private void processArrow(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 40:
                if (FIRST_CHOICE != Color.WHITE) {
                    SECOND_CHOICE = FIRST_CHOICE;
                    FIRST_CHOICE = Color.WHITE;
                    LAST_CHOICCE = FIRST_CHOICE;
                    return;
                }
                if (SECOND_CHOICE != Color.WHITE) {
                    LAST_CHOICCE = SECOND_CHOICE;
                    SECOND_CHOICE = Color.WHITE;
                    FIRST_CHOICE = SECOND_CHOICE;

                    return;
                }
                if (LAST_CHOICCE != Color.WHITE) {
                    FIRST_CHOICE = LAST_CHOICCE;
                    SECOND_CHOICE = Color.WHITE;
                    LAST_CHOICCE = SECOND_CHOICE;
                    return;
                }
                break;
            case 38:
                if (FIRST_CHOICE != Color.WHITE) {
                    LAST_CHOICCE = FIRST_CHOICE;
                    SECOND_CHOICE = Color.WHITE;
                    FIRST_CHOICE = SECOND_CHOICE;
                    return;
                }
                if (SECOND_CHOICE != Color.WHITE) {
                    FIRST_CHOICE = SECOND_CHOICE;
                    LAST_CHOICCE = Color.WHITE;
                    SECOND_CHOICE = LAST_CHOICCE;


                    return;
                }
                if (LAST_CHOICCE != Color.WHITE) {
                    SECOND_CHOICE = LAST_CHOICCE;
                    FIRST_CHOICE = Color.WHITE;
                    LAST_CHOICCE = FIRST_CHOICE;
                    return;
                }
                break;
        }
    }
}
