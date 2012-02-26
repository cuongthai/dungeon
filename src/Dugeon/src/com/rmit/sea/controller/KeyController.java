/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.controller;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.behaviour.BehaviourFactory;
import com.rmit.sea.gameengine.behaviour.GetItemBehaviour;
import com.rmit.sea.gameengine.behaviour.MoveBehaviour;
import com.rmit.sea.gameengine.behaviour.PlayerBehaviourFactory;
import com.rmit.sea.gameengine.behaviour.UseItemBehaviour;
import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.charactermodel.CharacterManager;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.command.AttackCommand;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.command.GetItemCommand;
import com.rmit.sea.gameengine.command.MoveCommand;
import com.rmit.sea.gameengine.command.UseItemCommand;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.gameengine.model.NavigationManager;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import com.rmit.sea.gameengine.store.Store;
import com.rmit.sea.view.ConsoleView;
import com.rmit.sea.view.GameFrame;
import com.rmit.sea.view.HelpPanel;
import com.rmit.sea.view.ItemSelectorPanel;
import com.rmit.sea.view.ItemSellBuyPanel;
import com.rmit.sea.view.SkillSelectorPanel;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gia
 */
public class KeyController {

    private Player player;
    private Map<Coordinate, ViewablePixel> fullMap;
    private GameKeyListener gameKeyListener;
    private GameFrame gameFrame;
    private Inventory mapInventory;
    private CharacterManager characterManager;
    private GameMap gameMap;

    public KeyController(GameEngine gameEngine, GameFrame gameFrame) {
        this.player = gameEngine.getCharacterManager().getPlayer();
        this.fullMap = gameEngine.mergeAllPixels();
        this.gameFrame = gameFrame;
        this.gameMap = gameEngine.getGameMap();
        this.mapInventory = gameMap.getMapInventory();
        this.characterManager = gameEngine.getCharacterManager();
        this.gameKeyListener = gameEngine.getGameKeyListener();

    }

    public Command convertKeyToCommand() {
        Command command = null;

        BehaviourFactory actionFactory = new PlayerBehaviourFactory();

        //waiting for user key
        boolean isACommand = false;
        Direction direction = null;

        char key = gameKeyListener.getInput();
        ConsoleView.out((char) key);
        KeyMeaning keyMeaning = gameKeyListener.getKeyMeaning();
        if (key == keyMeaning.getKeyDown()) {
            direction = Direction.DOWN;
            isACommand = true;
        } else if (key == keyMeaning.getKeyUp()) {
            direction = Direction.UP;
            isACommand = true;
        } else if (key == keyMeaning.getKeyBackward()) {
            direction = Direction.BACKWARD;
            isACommand = true;
        } else if (key == keyMeaning.getKeyForward()) {
            direction = Direction.FORWARD;
            isACommand = true;
        } else if (key == keyMeaning.getKeyLeft()) {
            direction = Direction.LEFT;
            isACommand = true;
        } else if (key == keyMeaning.getKeyRight()) {
            direction = Direction.RIGHT;
            isACommand = true;
            //these key down from this point will not become a command
        } else if (key == keyMeaning.getKeyToEquipSkill()) {
            gameFrame.removeCenterPanel(new SkillSelectorPanel(Constant.TO_EQUIP_SKILL, player.getSkillManager(), gameFrame));
            sync();
            gameFrame.displayGameView();
        } else if (key == keyMeaning.getKeyToSelectSkillToTrain()) {
            gameFrame.removeCenterPanel(new SkillSelectorPanel(Constant.TO_TRAIN_SKILL, player.getSkillManager(), gameFrame));
            sync();
            gameFrame.displayGameView();
        } else if (key == keyMeaning.getKeyToGetItem()) {
            isACommand = true;
            GetItemBehaviour getItemBehaviour = actionFactory.createGetItemBehaviour(player, mapInventory.getItems());
            command = new GetItemCommand(player, getItemBehaviour);
            return command;
        } else if (key == keyMeaning.getKeyToOpenInventory()) {
            ItemSelectorPanel selector = new ItemSelectorPanel(player.getInventory(), player.getEquipments(), gameFrame);
            gameFrame.removeCenterPanel(selector);
            sync();
            gameFrame.displayGameView();
            if (selector.getSelectedItem() != null) {
                UseItemBehaviour behave = actionFactory.createUseItemBehaviour(selector.getSelectedItem());
                command = new UseItemCommand(player, behave);
                isACommand = true;
                return command;
            } else {
                isACommand = false;
            }
        } else if (key == keyMeaning.getKeyToBuy()) {
            Coordinate playerCo = player.getCoordinate();
            Store s = gameMap.getStore(playerCo);

            if (s != null) {
                ItemSellBuyPanel panel = new ItemSellBuyPanel(s.getInventory(), player, player.getEquipments(), gameFrame, false);
                gameFrame.removeCenterPanel(panel);
                sync();
                gameFrame.displayGameView();
            }

            isACommand = false;

        } else if (key == keyMeaning.getKeyToHelp()) {


            HelpPanel panel = new HelpPanel(gameFrame);
            gameFrame.removeCenterPanel(panel);
            sync();
            gameFrame.displayGameView();


            isACommand = false;

        } else if (key == keyMeaning.getKeyToSell()) {
            Coordinate playerCo = player.getCoordinate();
            Store s = gameMap.getStore(playerCo);

            if (s != null) {
                ItemSellBuyPanel panel = new ItemSellBuyPanel(player.getInventory(), player, player.getEquipments(), gameFrame, true);
                gameFrame.removeCenterPanel(panel);
                sync();
                gameFrame.displayGameView();
            }

            isACommand = false;
        } else if (key == keyMeaning.getKeyToExecuteSkill()) {
            if ((player.getEquipSkill() instanceof NotRequireTarget)) {
                AttackBehaviour attack = actionFactory.createAttackBehaviour(player,
                        fullMap,
                        characterManager.getAllGameCharacters(),
                        player.getCoordinate());

                command = new AttackCommand(attack);
                return command;
            } else {
                isACommand = false;
            }
        } else if (key == keyMeaning.getKeyToShootForward()) {
            if ((player.getEquipSkill() instanceof RangeAttack)) {
                AttackBehaviour attack = actionFactory.createRangeAttackBehaviour(player,
                        fullMap,
                        characterManager.getAllGameCharacters(),
                        player.getCoordinate(), Direction.FORWARD);

                command = new AttackCommand(attack);
                return command;

            }
            isACommand = false;



        } else if (key == keyMeaning.getKeyToShootBackward()) {
            if ((player.getEquipSkill() instanceof RangeAttack)) {

                AttackBehaviour attack = actionFactory.createRangeAttackBehaviour(player,
                        fullMap,
                        characterManager.getAllGameCharacters(),
                        player.getCoordinate(), Direction.BACKWARD);

                command = new AttackCommand(attack);
                return command;

            }
            isACommand = false;



        } else if (key == keyMeaning.getKeyToShootLeft()) {
            if ((player.getEquipSkill() instanceof RangeAttack)) {
                AttackBehaviour attack = actionFactory.createRangeAttackBehaviour(player,
                        fullMap,
                        characterManager.getAllGameCharacters(),
                        player.getCoordinate(), Direction.LEFT);

                command = new AttackCommand(attack);
                return command;

            }
            isACommand = false;



        } else if (key == keyMeaning.getKeyToShootRight()) {
            if ((player.getEquipSkill() instanceof RangeAttack)) {
                AttackBehaviour attack = actionFactory.createRangeAttackBehaviour(player,
                        fullMap,
                        characterManager.getAllGameCharacters(),
                        player.getCoordinate(), Direction.RIGHT);

                command = new AttackCommand(attack);
                return command;

            }
            isACommand = false;

        }


        if (!isACommand) {
            return null;
        }



        Coordinate target = NavigationManager.move(player.getCoordinate(), direction, 1);
        ViewablePixel targetPixel = fullMap.get(target);
        if (targetPixel instanceof Vulnerable && !(targetPixel instanceof Player)) {

            if (player.getEquipSkill() instanceof NotRequireTarget) {
                target = player.getCoordinate();
            }
            ConsoleView.out("attack");

            AttackBehaviour attack = actionFactory.createAttackBehaviour(player,
                    fullMap,
                    characterManager.getAllGameCharacters(),
                    target);

            command = new AttackCommand(attack);

        } else {
            MoveBehaviour moveOne = actionFactory.createMoveBehaviour(direction, 1);
            command = new MoveCommand(player, gameMap, characterManager.getAllGameCharacters(), moveOne);

        }
        return command;
    }

    private void sync() {
        //Fixing MAC OS Support
        while (gameFrame.getCurrentCenterPanel() != null) {
            try {
                Thread.sleep(10);



            } catch (InterruptedException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }
}
