/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.view;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.Equipments;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.iteminterface.Unusable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
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
public class ItemSellBuyPanel extends JPanel implements KeyListener {

    private int currentPage;
    private int totalPages;
    private int currentNumberSelected;
    private boolean isSell;
    private GameFrame parent;
    private Inventory inventory;
    private Equipments equipments;
    private Item selectedItem;
    private Player player;

    public ItemSellBuyPanel(Inventory displayInventory, Player player, Equipments equipments, GameFrame parent, boolean isSell) {
        this.inventory = displayInventory;
        this.equipments = equipments;
        this.parent = parent;
        this.isSell = isSell;
        this.player = player;
        currentPage = 1;
        totalPages = (displayInventory.getItems().size() - 1) / 10 + 1;
        currentNumberSelected = 0;
        selectedItem = null;


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
        Font defaultFont = g.getFont();
        g.setFont(FontResources.getInstance().getDefaultFont(Font.PLAIN, 100f));
        g.drawString(isSell?"Sell":"Buy", 300, 100);
        g.setFont(defaultFont);

        if(!isSell){
            ((Gold)(inventory.getItems().get(0))).setAmount(player.getInventory().getCurrentGold());
        }
        
        if (!inventory.getItems().isEmpty()) {
            drawPageNumber(g);
            drawItemsList(g);
            drawDescription(g);

            drawCurrentEquipItem(g);

        }
    }

    private void drawDescription(Graphics g) {
        int x = 300;
        int y = 300;
        int index = currentNumberSelected + (currentPage - 1) * 10;
        Item item = inventory.getItems().get(index);
        g.drawString("Name: " + item.getName(), x, y);
        y += Constant.LINE_HEIGHT;
        String des = item.getDescription();

        String[] words = des.split(" ");
        for (int i = 0; i < words.length / 5 + 1; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = i * 5; j < Math.min(5 + i * 5, words.length); j++) {
                line.append(words[j] + " ");
            }
            g.drawString(line.toString(), x, y);
            y += Constant.LINE_HEIGHT;
        }
        if (item instanceof Valuable) {
            g.drawString("Value: "+((Valuable)item).getValue()+" Gold",x,y);

        }
    }

    private void drawPageNumber(Graphics g) {

        g.drawString("Page " + currentPage, getWidth() - 100, getHeight() - 10);
    }

    private void drawItemsList(Graphics g) {
        int x = 100;
        int y = 200;

        if (currentPage == totalPages) {
            for (int i = (currentPage - 1) * 10; i < inventory.getItems().size(); i++) {
                drawItem(g, i - (currentPage - 1) * 10, inventory.getItems().get(i), x, y);
                y += Constant.LINE_PADDING;
            }
        } else {
            for (int i = (currentPage - 1) * 10; i < currentPage * 10; i++) {
                drawItem(g, i - (currentPage - 1) * 10, inventory.getItems().get(i), x, y);
                y += Constant.LINE_PADDING;

            }
        }
    }


    private void drawItem(Graphics g, int order, Item item, int x, int y) {
        if (order == currentNumberSelected) {
            g.setColor(Color.BLUE);
        }
        g.drawString(order + ". " + item.getName(), x, y);
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
                if (currentNumberSelected != (inventory.getItems().size() - 1) % 10) {
                    currentNumberSelected++;
                }
            }

        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (inventory.getItems().size() != 0) {
                Item chosenItem = inventory.getItems().get(currentNumberSelected + (currentPage - 1) * 10);
                if (!(chosenItem instanceof Unusable)) {
                    if (chosenItem instanceof Valuable) {
                        if (isSell && player.sell(chosenItem)) {
                            if (inventory.removeItem(chosenItem)) {

                                currentNumberSelected--;
                                if (currentNumberSelected < 0) {
                                    currentNumberSelected = 9;
                                    currentPage--;
                                }
                                totalPages = (inventory.getItems().size() - 1) / 10 + 1;

                            }
                        }else if(!isSell&&player.buy(chosenItem)){
                            if (inventory.removeItem(chosenItem)) {

                                currentNumberSelected--;
                                if (currentNumberSelected < 0) {
                                    currentNumberSelected = 9;
                                    currentPage--;
                                }
                                totalPages = (inventory.getItems().size() - 1) / 10 + 1;

                            }
                        }
                    }
                }
            }

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            parent.removeCenterPanel();
        }
        revalidate();
        repaint();
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    private void drawCurrentEquipItem(Graphics g) {
        int x = 600;
        int y = 200;
        g.drawString("Equipments", x, y);
        y += Constant.LINE_HEIGHT * 2;
        if (equipments.getLeftArmEquipment() != null) {

            Item item = (Item) (equipments.getLeftArmEquipment());
            g.drawString("Left Arm: " + item.getName(), x, y);
            y += Constant.LINE_HEIGHT;
            String des = item.getDescription();

            String[] words = des.split(" ");
            for (int i = 0; i < words.length / 5 + 1; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = i * 5; j < Math.min(5 + i * 5, words.length); j++) {
                    line.append(words[j] + " ");
                }
                g.drawString(line.toString(), x, y);
                y += Constant.LINE_HEIGHT;
            }
        }
        y += Constant.LINE_HEIGHT;
        if (equipments.getRightArmEquipment() != null) {

            Item item = (Item) (equipments.getRightArmEquipment());
            g.drawString("Right Arm: " + item.getName(), x, y);
            y += Constant.LINE_HEIGHT;
            String des = item.getDescription();

            String[] words = des.split(" ");
            for (int i = 0; i < words.length / 5 + 1; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = i * 5; j < Math.min(5 + i * 5, words.length); j++) {
                    line.append(words[j] + " ");
                }
                g.drawString(line.toString(), x, y);
                y += Constant.LINE_HEIGHT;
            }

        }
        y += Constant.LINE_HEIGHT;

        if (equipments.getBodyEquipment() != null) {

            Item item = (Item) (equipments.getBodyEquipment());
            g.drawString("Body: " + item.getName(), x, y);
            y += Constant.LINE_HEIGHT;
            String des = item.getDescription();

            String[] words = des.split(" ");
            for (int i = 0; i < words.length / 5 + 1; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = i * 5; j < Math.min(5 + i * 5, words.length); j++) {
                    line.append(words[j] + " ");
                }
                g.drawString(line.toString(), x, y);
                y += Constant.LINE_HEIGHT;
            }
        }




    }
}
