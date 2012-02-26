/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.view;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.SkillManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author hoanggia
 */
public class SkillSelectorPanel extends JPanel implements KeyListener {

    private List<Skill> availableSkills;
    private int currentPage;
    private int totalPages;
    private int currentNumberSelected;
    private int slot;
    private SkillManager skillManager;
    private GameFrame parent;
    private String forAction;

    public SkillSelectorPanel(String forAction, SkillManager skillManager, GameFrame parent) {
        this.skillManager = skillManager;
        this.parent = parent;
        this.forAction = forAction;
        this.availableSkills = skillManager.getAvailableSkills();
        currentPage = 1;
        totalPages = (availableSkills.size()-1) / 10 + 1;
        currentNumberSelected = 0;
        //totalPages = 10;
        setPreferredSize(new Dimension(Constant.GAMEVIEW_WIDTH, Constant.GAMEVIEW_HEIGHT));
        addKeyListener(this);
        setFocusable(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Constant.DEFAULT_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        Font defaultFont=g.getFont();
        g.setFont(FontResources.getInstance().getDefaultFont(Font.PLAIN,100f));
        g.drawString("Skills",300,100);
        g.setFont(defaultFont);

        drawPageNumber(g);
        drawSkillsList(g);
        drawDescription(g);

    }

    private void drawDescription(Graphics g) {
        int x = 300;
        int y = 300;
        int index = currentNumberSelected + (currentPage - 1) * 10;
        g.drawString("Name: " + availableSkills.get(index).getSkillName(), x, y);
        y += Constant.LINE_HEIGHT;
        String des = availableSkills.get(index).getSkillDescription();

        String[] words = des.split(" ");
        for (int i = 0; i < words.length / 7 + 1; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = i * 7; j < Math.min(7 + i * 7, words.length); j++) {
                line.append(words[j] + " ");
            }
            g.drawString(line.toString(), x, y);
            y += Constant.LINE_HEIGHT;
        }
    }

    private void drawPageNumber(Graphics g) {

        g.drawString("Page " + currentPage, getWidth() - 100, getHeight() - 10);
    }

    private void drawSkillsList(Graphics g) {
        int x = 100;
        int y = 200;

        if (currentPage == totalPages) {
            for (int i = (currentPage - 1) * 10; i < availableSkills.size(); i++) {
                drawSkill(g, i - (currentPage - 1) * 10, availableSkills.get(i), x, y);
                y += Constant.LINE_PADDING;
            }
        } else {
            for (int i = (currentPage - 1) * 10; i < currentPage * 10; i++) {
                drawSkill(g, i - (currentPage - 1) * 10, availableSkills.get(i), x, y);
                y += Constant.LINE_PADDING;

            }
        }
    }

    private void drawSkill(Graphics g, int order, Skill skill, int x, int y) {
        if (order == currentNumberSelected) {
            g.setColor(Color.BLUE);
        }
        g.drawString(order + ". " + skill.getSkillName(), x, y);
        g.setColor(Color.WHITE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (currentPage != totalPages) {
                currentPage++;
                currentNumberSelected = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (currentPage != 1) {
                currentPage--;
                currentNumberSelected = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            if (currentNumberSelected != 0) {
                currentNumberSelected--;

            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (currentPage != totalPages) {
                if (currentNumberSelected != 9) {
                    currentNumberSelected++;
                }
            } else {
                if (currentNumberSelected != (availableSkills.size() - 1) % 10) {
                    currentNumberSelected++;
                }
            }

        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (forAction.equals(Constant.TO_TRAIN_SKILL)) {
                skillManager.trainSkill(availableSkills.get(currentNumberSelected + (currentPage - 1) * 10));
            }else{
                skillManager.equipSkill(availableSkills.get(currentNumberSelected + (currentPage - 1) * 10));
            }
            parent.removeCenterPanel();

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            parent.removeCenterPanel();
        }
        revalidate();
        repaint();
    }
}
