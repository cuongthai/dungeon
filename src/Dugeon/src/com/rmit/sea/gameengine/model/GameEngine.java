package com.rmit.sea.gameengine.model;

import com.rmit.sea.gameengine.charactermodel.monster.AIAlgorithm;
import com.rmit.sea.gameengine.charactermodel.player.PlayerLoader;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.controller.GameKeyListener;
import com.rmit.sea.controller.KeyController;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.charactermodel.CharacterManager;
import com.rmit.sea.gameengine.charactermodel.monster.MonsterCharacterFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.monster.EasyCharacterFactory;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.charactermodel.monster.Monster;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.rmit.sea.gameengine.mapmodel.MapGenerator;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.lineofsight.LOSGenerator;
import com.rmit.sea.view.ConsoleView;
import com.rmit.sea.view.GameFrame;
import com.rmit.sea.webserver.model.Score;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameEngine extends Observable implements Game {

    // Variables that are injected into this
    private MapGenerator mapGenerator;
    private GameKeyListener gameKeyListener;
    private PlayerLoader playerLoader;
    private LOSGenerator losg;  //Generate Light of sight
    private int currentGroundLevel;//first level is level 0, then increase
    private GameFrame gameFrame;
    // Variables that are created inside this
    private GameMap gameMap;
    private CharacterManager characterManager;
    private int turnCounter; // To reduce skill abuse
    private boolean hasStarted;
    private boolean hasEnded;
    private long start;  //to call culate FPS
    private List<Command> currentCommands;
    private TurnManager turnManager;    //Switching Turn

    public GameEngine() {
    }

    @Override
    public void createGame() {
        if (gameFrame != null) {
            addObserver(gameFrame.getPlayerInformationPanel());
            addObserver(gameFrame.getInformationPanel());
            addObserver(gameFrame.getGameView());
            gameFrame.createGame();
        }
    }

    @Override
    public void startGame(PlayerLoader playerLoader) {
        initNewGame(playerLoader);


        Command playerCommand = null;
        List<Command> computerCommands = null;
        turnManager = new TurnManager(Turn.Human);

        //Run game loop
        do {
            long l1 = System.currentTimeMillis();
            currentCommands = new ArrayList<Command>();
            Turn currentTurn = turnManager.getCurrentTurn();

            ConsoleView.out("\t" + currentTurn + " Turn");

            switch (currentTurn) {
                case Human:
                    playerCommand = processHumanTurn();
                    if (playerCommand != null) {
                        currentCommands.add(playerCommand);
                    }
                    break;
                case Computer:
                    start = System.currentTimeMillis();
                    computerCommands = processComputerTurn(characterManager.getComputerCharacters());
                    currentCommands.addAll(computerCommands);
                    break;
            }

            long t = System.currentTimeMillis();

            //execute command
            for (Command command : currentCommands) {
                command.execute();
            }

            if (currentTurn == Turn.Human) {

                if (currentGroundLevel < characterManager.getPlayer().getLevel()) {

                    initLevel(true);
                } else if (currentGroundLevel > characterManager.getPlayer().getLevel()) {
                    initLevel(false);
                }

                characterManager.getPlayer().increaseSkillPoint();
                if (turnCounter % Constant.MAX_TURN_TO_RECOVER_MP == 0) {
                    characterManager.getPlayer().getCharacterDetailInfo().increaseMp(characterManager.getPlayer().getMpRecovery());
                }

                turnCounter++;

            }
            //manage dead bodies here...
            characterManager.removeDeadCharacters(gameMap.getMapInventory(), currentGroundLevel);


            //after execute command
            if (currentTurn == Turn.Computer) {
                Map<Coordinate, ViewablePixel> fullMap = mergeAllPixels();
                losg.remove(fullMap);

                // Update LOS
                long l4 = System.currentTimeMillis();
                for (GameCharacter c : characterManager.getAllGameCharacters()) {
                    if (characterManager.getPlayer().getCoordinate().distanceTo(c.getCoordinate()) <= Constant.GAME_ACTIVE_AREA_RADIUS) {
                        c.getActivationDistance().getLOSGenerator().generate(c, fullMap, getGameMap().getMapPixels(), 360);
                    }

                }
                ConsoleView.out("LOS GameEngine " + (System.currentTimeMillis() - l4));

                reduceAbuseSkillTrain(characterManager.getPlayer().getCoordinate());

                //Update View
                setChanged();
                notifyObservers();
            } else {
                setChanged();
                notifyObservers(Constant.NOT_NOTIFY_GAMEVIEW);
            }

            turnManager.nextTurn();
            ConsoleView.out("Total " + (System.currentTimeMillis() - t));

        } while (isAlive());

        // Game over
        //Save Game Info To Server
        saveGame(false);
        System.out.println("end");
        hasEnded = true;
        resetGame();
        System.out.println("here");
        gameFrame.createGame();
    }

    private void reduceAbuseSkillTrain(Coordinate playerCo) {
        if (turnCounter % Constant.MAX_TURN_TO_CHECK_ABUSE == 0) {
            if (characterManager.getPlayer().isStandingStill()) {

                MonsterCharacterFactory computerCharacterFactory = new EasyCharacterFactory();
                //Create Monster here him
                characterManager.getComputerCharacters().
                        add(computerCharacterFactory.createMonsterForAbuse(
                        gameMap, playerCo, currentGroundLevel));

            }
            characterManager.getPlayer().cleanCoordinateLog();
        }
    }

    public Map<Coordinate, ViewablePixel> mergeAllPixels() {
        Map<Coordinate, ViewablePixel> fullMap = new HashMap<Coordinate, ViewablePixel>();
        //put map in first
        fullMap.putAll(this.getGameMap().getMapPixels());



        //then item
        List<Item> items = gameMap.getMapInventory().getItems();
        for (Item item : items) {
            ViewablePixel p = (ViewablePixel) item;
            fullMap.put(p.getCoordinate(), p);
        }
        List<GameCharacter> computerCharacters = characterManager.getComputerCharacters();
        //then monster
        for (GameCharacter c : computerCharacters) {
            fullMap.put(c.getCoordinate(), c);
        }
        //finally player
        fullMap.put(characterManager.getPlayer().getCoordinate(), characterManager.getPlayer());
        return fullMap;
    }

    public Map<Coordinate, ViewablePixel> mergeAllViewableThing() {
        Map<Coordinate, ViewablePixel> fullMap = new HashMap<Coordinate, ViewablePixel>();
        Map<Coordinate, ViewablePixel> fullMergeMap = mergeAllPixels();
        //put map in first
        fullMap.putAll(this.getGameMap().getMapPixels());
        List<Item> items = gameMap.getMapInventory().getItems();
        //put items in
        for (Item item : items) {
            ViewablePixel p = (ViewablePixel) item;
            fullMap.put(p.getCoordinate(), p);
        }

        List<Coordinate> coordinatesOnSight = characterManager.getPlayer().getActivationDistance().getCoordinatesOnSight();
        for (Coordinate c : coordinatesOnSight) {
            ViewablePixel p = fullMergeMap.get(c);
            if (p != null) {
                fullMap.put(p.getCoordinate(), p);
            }
        }
        return fullMap;
    }

    @Override
    public void saveGame(boolean isSaveAndExit) {
        ObjectOutputStream outputStream = null;
        if (hasStarted) {


            try {

                //Construct the LineNumberReader object
                outputStream =
                        new ObjectOutputStream(new FileOutputStream(getCharacterManager().getPlayer().getName()));

                Player p = getCharacterManager().getPlayer();

                //outputStream.writeObject(p);
                saveHighScoreToServer();
                saveGameToServer();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                //Close the ObjectOutputStream
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

        }
        if (isSaveAndExit) {
            System.exit(0);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        ConsoleView.out("Adding Observer");
    }

    private void initLevel(boolean justGoDown) {
        currentGroundLevel = characterManager.getPlayer().getLevel();
        Random r = new Random();
        //Initialize Game Map
        initGameMap();


        //Initialize Game Characters
        MonsterCharacterFactory computerCharacterFactory = new EasyCharacterFactory();
        List<GameCharacter> computerCharacters = computerCharacterFactory.createComputerCharacter(
                gameMap,
                1,
                Constant.MIN_MONSTERS_NUMBER,
                currentGroundLevel);

        // init player
        Coordinate playerCoordinate = gameMap.getWalkableWays().get(r.nextInt(gameMap.getWalkableWays().size()));


        Player oldPlayer = characterManager.getPlayer();

        oldPlayer.setCoordinate(playerCoordinate);

        characterManager = new CharacterManager(oldPlayer, computerCharacters);


        //init door
        gameMap.initDoors(characterManager.getPlayer().getCoordinate(), justGoDown, characterManager.getPlayer().getLevel());

        //init los
        Map<Coordinate, ViewablePixel> fullMap = mergeAllPixels();
        for (GameCharacter c : characterManager.getAllGameCharacters()) {
            c.getActivationDistance().getLOSGenerator().generate(c, fullMap, getGameMap().getMapPixels(), 360);

        }
    }

    private void initGameMap() {
        gameMap = mapGenerator.generate();
        //init store
        gameMap.initStore(currentGroundLevel);

        //Init game items
        gameMap.initItems(currentGroundLevel);

    }

    private Command processHumanTurn() {
        KeyController keyController = new KeyController(this, gameFrame);
        Command command = null;
        while (command == null) {
            command = keyController.convertKeyToCommand();
            if (command == null) {
                setChanged();
                notifyObservers();
            }
        }
        return command;
    }

    private List<Command> processComputerTurn(List<GameCharacter> computerCharacters) {
        List<Command> commandLists = new ArrayList<Command>();

        Map<Coordinate, ViewablePixel> fullMap = mergeAllPixels();
        for (GameCharacter computer : computerCharacters) {
            AIAlgorithm AIFactory = ((Monster) computer).getAi();
            commandLists.add(AIFactory.getCommand(computer,
                    characterManager.getPlayer(),
                    getGameMap(),
                    fullMap,
                    characterManager.getAllGameCharacters()));
        }
        return commandLists;


    }

    /*
     * Initilize new game base on playerloader info
     */
    public void initNewGame(PlayerLoader playerLoader) {
        characterManager = null;
        //init player loader

        this.playerLoader = playerLoader; // Help us to inject different state of player(new, reload player...)
        //create character manager
        Player player = playerLoader.getPlayer(new Coordinate(0, 0));
        characterManager = new CharacterManager(player, new ArrayList<GameCharacter>());
        currentGroundLevel = player.getLevel();

        //init level
        initLevel(true);
        hasStarted = true;
        hasEnded = false;
        setChanged();
        notifyObservers();
    }

    public boolean isAlive() {
        return characterManager.getPlayer().isAlive();
    }

    private void resetGame() {
        currentGroundLevel = 0;
        gameFrame.removeInformationPanel();
        playerLoader = null;
        hasStarted = false;
    }

    private void saveHighScoreToServer() {
        //ScoreCalculator calculator=new ScoreCalculator();
        if (characterManager != null && characterManager.getPlayer() != null) {
            if (!characterManager.getPlayer().getName().equals(Constant.SUPER_PLAYER_NAME)) {
                Score score = new Score(characterManager.getPlayer());
                Communicator communicator = new Communicator();
                communicator.send(score);
            }

        }
    }

    /*
     * Call communicator to save game
     */
    private void saveGameToServer() {
        if (characterManager != null && characterManager.getPlayer() != null) {
            Communicator communicator = new Communicator();
            communicator.send(characterManager.getPlayer());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Getter And Setter">
    public int getCurrentGroundLevel() {
        return currentGroundLevel;
    }

    public boolean gameStarted() {
        return hasStarted;
    }

    public List<Command> getCurrentCommands() {
        return currentCommands;
    }

    public boolean isHumanTurn() {
        if (turnManager == null) {
            return false;
        }

        return turnManager.getCurrentTurn() == Turn.Human;
    }

    public void setPlayerLoader(PlayerLoader playerLoader) {
        this.playerLoader = playerLoader;
    }

    public long getStartTime() {
        return start;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setCharacterManager(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public GameKeyListener getGameKeyListener() {
        return gameKeyListener;
    }

    public void setGameKeyListener(GameKeyListener gameKeyListener) {
        this.gameKeyListener = gameKeyListener;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setLosg(LOSGenerator losg) {
        this.losg = losg;
    }

    public void setMapGenerator(MapGenerator mapGenerator) {
        this.mapGenerator = mapGenerator;
    }
    // </editor-fold>
}
