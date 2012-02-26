package com.rmit.sea.view;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class PlayerInformationPanel extends JPanel implements Observer {

    private GameEngine gameEngine;

    public PlayerInformationPanel() {
        setPreferredSize(new Dimension(Constant.PLAYER_INFORMATION_PANEL_WIDTH, Constant.PLAYER_INFORMATION_PANEL_HEIGHT));
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Font font = FontResources.getInstance().getDefaultFont(Font.PLAIN, 20f);
        g.setFont(font);
        g.setColor(Constant.DEFAULT_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        //has started
        if (!gameEngine.gameStarted()) {
            return;
        }


        //first position
        int x = 10;
        int y = 25;
        //get skill manager
        if (gameEngine.getCharacterManager() == null) {
            return;
        }

        g.drawString("Help : H", x, y);
        x += Constant.TAB_WIDTH * 2;
        
        if (gameEngine.getCharacterManager().getPlayer().getEquipSkill() instanceof NotRequireTarget) {
            g.drawString("Execute Skill: x", x, y);
            x += Constant.TAB_WIDTH*2;

        } else if (gameEngine.getCharacterManager().getPlayer().getEquipSkill() instanceof RangeAttack) {
            g.drawString("Shoot: SHIFT + Direction", x, y);
            x += Constant.TAB_WIDTH*2;

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsoleView.out("Game Information receives update");
        revalidate();
        repaint();
    }
}
