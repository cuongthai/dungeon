package com.rmit.sea.view;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.charactermodel.CharacterManager;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.command.AttackCommand;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.command.MoveCommand;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.SkillManager;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InformationPanel extends JPanel implements Observer {

    private static final float FONT_SIZE = 15f;
    private static final int TAB_SIZE = (int) (FONT_SIZE) * 7;
    private static final byte MONSTER_NUM = 5;
    private static final byte LOG_NUM = 5;
    private static final int LOG_PADDING = 10;
    private static final int LOG_HEIGHT = LOG_NUM * ((int) FONT_SIZE + LOG_PADDING);
    private static final Color UNDER_ATTACK_COLOR = Color.RED;
    private static final Color NAME_COLOR = Color.GREEN;
    private static final Color GOLD_COLOR = Color.YELLOW;
    private static final Color HP_COLOR = Color.WHITE;
    private static final Color PLAYER_HP_COLOR = Color.YELLOW;
    private GameEngine gameEngine;
    private Queue commands;
    private static final Object commandsLock = new Object();
    // Variables for drawing
    private int x, y;

    public InformationPanel() {
        setPreferredSize(new Dimension(Constant.INFORMATION_PANEL_WIDTH, Constant.INFORMATION_PANEL_HEIGHT));

        // Add border
        Border border = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.WHITE);
        this.setBorder(border);

        initPanel();
    }

    public final void initPanel() {
        commands = new ArrayBlockingQueue<Command>(LOG_NUM);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //has started
        if (!gameEngine.gameStarted()) {
            return;
        }

        Font font = FontResources.getInstance().getDefaultFont(Font.PLAIN, FONT_SIZE);
        g.setFont(font);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        //first position
        x = 10;
        y = 20;

        //Frame per second
        drawFPS(g);
        y += Constant.LINE_PADDING - 20;

        //level information
        g.drawString("Level: " + gameEngine.getCurrentGroundLevel(), x, y);
        y += Constant.LINE_PADDING;

        //draw player information
        drawPlayerInfo(g);
        y += Constant.LINE_PADDING;

        // Draw monsters info
        drawMonstersInfo(g);
        y += getHeight() - y - LOG_HEIGHT;

        // Draw player log
        drawPlayerLog(g);
    }

    private void drawFPS(Graphics g) {
        long elipse = System.currentTimeMillis() - gameEngine.getStartTime();
        if (elipse < 1000 && elipse > 0) {
            g.drawString("Fps: " + 1000 / elipse, x, y);
        } else {
            g.drawString("Fps: 0", x, y);
        }
    }

    private void drawPlayerInfo(Graphics g) {

        Player player = gameEngine.getCharacterManager().getPlayer();
        SkillManager skillManager = player.getSkillManager();
        Skill currentTrainSkill = skillManager.getCurrentTrainSkill();
        Skill currentEquipSkill = skillManager.getCurrentEquipSkill();

        // Draw player info
        drawInformation(player, g);
        y += Constant.LINE_PADDING - 5;

        //draw skill point
        g.drawString(currentTrainSkill.getSkillName(), x, y);
        y += Constant.LINE_PADDING - 20;

        //draw train skill
        Damage totalDamage = player.getDamage();
        if (player.getSkillManager().getCurrentEquipSkill() instanceof AttackableSkill) {
            totalDamage = player.getDamage().mergeDamage(((AttackableSkill) player.getSkillManager().getCurrentEquipSkill()).getDamage(player.getSkillManager().getCurrentEquipSkill().getCurrentLevel()));
        }
        g.drawString(currentTrainSkill.getSkillPoints() + " Skill Points [" + totalDamage.toString() + "]", x, y);
        y += Constant.LINE_PADDING - 20;

        //draw armor
        y += Constant.LINE_PADDING - 20;



        //draw equipments
        g.drawString("Left Arm: " + (player.getEquipments().getLeftArmEquipment() != null ? ((Item) (player.getEquipments().getLeftArmEquipment())).getName() : "nothing"), x, y);
        y += Constant.LINE_PADDING - 20;

        g.drawString("Right Arm: " + (player.getEquipments().getRightArmEquipment() != null ? ((Item) (player.getEquipments().getRightArmEquipment())).getName() : "nothing"), x, y);
        y += Constant.LINE_PADDING - 20;

        g.drawString("Armor: " + (player.getEquipments().getBodyEquipment() != null ? ((Item) (player.getEquipments().getBodyEquipment())).getName() : "nothing"), x, y);
        y += Constant.LINE_PADDING;


        //draw equip skill
        g.drawString("Equip: " + currentEquipSkill.getSkillName() + " Lv" + currentEquipSkill.getCurrentLevel(), x, y);
    }

    private synchronized void drawMonstersInfo(Graphics g) {
        AttackCommand attackCommand = getPlayerAttackCommand();
        List<Coordinate> targets = new ArrayList<Coordinate>();

        // Get the target if there is any
        if (attackCommand != null) {
            AttackBehaviour behaviour = attackCommand.getAttackBehaviour();
            for (Vulnerable target : behaviour.getTargetsToAttack()) {
                if (target instanceof ViewablePixel) {
                    targets.add(((ViewablePixel) target).getCoordinate());
                }
            }
        }

        //TODO may sort coordinate by distance

        // draw monster information
        CharacterManager characterManager = gameEngine.getCharacterManager();
        List<GameCharacter> allComputerCharacters = new ArrayList<GameCharacter>(characterManager.getComputerCharacters());
        Player player = characterManager.getPlayer();
        List<Coordinate> charactersOnSight =
                player.getActivationDistance().getCoordinatesOnSight();
        Coordinate tempCoor = null;
        int bottom = this.getHeight() - LOG_HEIGHT - Constant.LINE_HEIGHT * 2;

        for (GameCharacter c : allComputerCharacters) {
            if (y < bottom&&c !=null) {

                tempCoor = c.getCoordinate();
                if (charactersOnSight!=null&&charactersOnSight.contains(tempCoor)) {
                    if (targets.contains(tempCoor)) {
                        drawInformation(c, g, UNDER_ATTACK_COLOR);
                    } else {
                        drawInformation(c, g);
                    }

                    y += Constant.LINE_PADDING;
                }
            }
        }
    }

    private void drawInformation(GameCharacter c, Graphics g) {
        Color hpColor = HP_COLOR;
        if (c instanceof Player) {
            hpColor = (PLAYER_HP_COLOR);
            drawPlayerInformation(c, g, NAME_COLOR, hpColor);
        } else {
            drawInformation(c, g, NAME_COLOR, hpColor);
        }
    }

    private void drawInformation(GameCharacter c, Graphics g, Color color) {
        drawInformation(c, g, color, color);
    }

    private void drawInformation(GameCharacter c, Graphics g, Color nameColor, Color hpColor) {
        Color temp = g.getColor();

        // Draw name
        g.setColor(nameColor);
        g.drawString(c.getName(), x, y);

        // Draw details
        g.setColor(hpColor);
        g.drawString(c.getCharacterDetailInfo().toString(),
                x, y + Constant.LINE_HEIGHT);

        g.setColor(temp);
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsoleView.out("Game Information receives update");
        // Update player commands if it's changed
        changeCommands();

        revalidate();
        repaint();
    }

    private void changeCommands() {
        List<Command> temp = gameEngine.getCurrentCommands();
        if (temp == null || temp.isEmpty()) {
            return;
        }
        List<Command> newCommands = new ArrayList<Command>();

        // filt, do not get move commands
        for (Command c : temp) {
            if (c != null && !(c instanceof MoveCommand)) {
                newCommands.add(c);
            }
        }

        int addedSize = newCommands.size();
        Command c = null;
        synchronized (commandsLock) {
            // Manage the size of commands
            for (int i = 0; i < addedSize; ++i) {
                c = newCommands.get(i);

                boolean offered = commands.offer(c);
                if (!offered) {
                    // The queue is full, poll and try again
                    commands.poll();
                    commands.offer(c);
                }
            }
        }
    }

    private AttackCommand getPlayerAttackCommand() {
        AttackCommand temp = null;
        synchronized (commandsLock) {
            if (commands == null || commands.isEmpty()) {
                return null;
            }

            Iterator i = commands.iterator();
            Command c = null;
            while (i.hasNext()) {
                c = (Command) i.next();

                if (c instanceof AttackCommand) {
                    if (((AttackCommand) c).getAttackBehaviour().getAttacker() instanceof Player) {
                        temp = (AttackCommand) c;
                    }
                }
            }
        }

        return temp;
    }

    private void drawPlayerLog(Graphics g) {
        synchronized (commandsLock) {
            if (commands == null || commands.isEmpty()) {
                return;
            }

            Command c = null;
            String log = null;
            Iterator i = commands.iterator();

            while (i.hasNext()) {
                c = (Command) i.next();
                if (c == null) {
                    continue;
                }

                log = c.toString();
                if (log == null || log.isEmpty()) {
                    continue;
                }

                g.drawString(log, x, y);
                y += Constant.LINE_PADDING - 10;
            }
        }
    }

    private void drawPlayerInformation(GameCharacter c, Graphics g, Color nameColor, Color hpColor) {
        Color temp = g.getColor();

        // Draw name
        g.setColor(nameColor);
        g.drawString(c.getName()+" "+((Player)c).getTitle(), x, y);
        
        // Draw details
        g.setColor(hpColor);
        g.drawString(c.getCharacterDetailInfo().toString(),
                x, y + Constant.LINE_HEIGHT);

        g.setColor(temp);
    }
}
