/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.view;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author gia
 */
public class HelpPanel extends JPanel implements KeyListener{
    private static final float FONT_SIZE = 15f;
    private int x;
    private int y;
    private GameFrame parent;


    public HelpPanel(GameFrame parent){
        setPreferredSize(new Dimension(Constant.GAMEVIEW_WIDTH, Constant.GAMEVIEW_HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        this.parent=parent;

    }

    @Override
    public  void paint(Graphics g) {
        super.paint(g);

        Font font = FontResources.getInstance().getDefaultFont(Font.PLAIN, FONT_SIZE);
        g.setFont(font);
        g.setColor(Constant.DEFAULT_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        //first position
        x = 10;
        y = 20;

        g.drawString("W - S - A - D : Up - Down - Left - Right", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("Z : Go up one level (need a door)", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("C : Go down one level (need a door)", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("Shift + W or S or A or D : Shooting arrow to that direction", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("X : Execute skill (only a certain number of skills can be executed like this)", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("E : Open skills list to select skill to equip", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("T : Open skills list to select skill to train", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("I : Open inventory to select item to use or equip", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("G : Pick up item", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("B : Buy (when standing over a store)", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("F : Sell (when standing over a store)", x, y);


        y += Constant.LINE_PADDING ;

        
        g.drawString("Inside skill list or item list", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("Enter : Select the object to use, equip, train, sell, buy(can be skill or item)", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("W - S - A - D : Up - Down - Previous Page - Next Page", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("R : Remove item (only in inventory)", x, y);
        y += Constant.LINE_PADDING - 20;
        g.drawString("ESC : Go back to game", x, y);
        y += Constant.LINE_PADDING - 20;

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            parent.removeCenterPanel();
        }
        revalidate();
        repaint();
    }


}
