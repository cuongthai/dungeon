package com.rmit.sea.dungeon.resources;

import java.awt.Color;

public class Constant {
    //Server Constants
    public static final String SERVER_ADDRESS = "localhost";
    public static final String HIGH_SCORE_FILE = "high_score.dat";
    public static final int APP_SERVER_PORT =52002;
    public static final int WEB_SERVER_PORT =52000;

    public static final String SPRING_CONFIG = "beans.xml";
    public static final String RES_DIR = "res/";
    public static final String NOT_NOTIFY_GAMEVIEW = "not notify gameView";
    public static final String GAME_END="end";
    public static final String SUPER_PLAYER_NAME = "kevinse2";
    public static final String WEAK_PLAYER_NAME = "binmaster";
    public static final int GAMEVIEW_WIDTH = 900;
    public static final int GAMEVIEW_HEIGHT = 600;
    public static final int INFORMATION_PANEL_WIDTH = 300;
    public static final int INFORMATION_PANEL_HEIGHT = 600;
    public static final int PLAYER_INFORMATION_PANEL_WIDTH = 900;
    public static final int PLAYER_INFORMATION_PANEL_HEIGHT = 50;
    public static final Color DEFAULT_BACKGROUND = Color.BLACK;
    public static final int LINE_HEIGHT = 17;
    public static final int LINE_PADDING = 40;
    public static final int TAB_WIDTH = 120;
    public static final int MAX_TURN_BEFORE_NEW_MONSTER_SPAM = 30;
    public static final int MIN_SIDE_OF_COMPONENT = 10;
    public static final int MAX_DISTANCE_BETWEEN_MAP_COMPONENT = 5;
    public static final String TO_TRAIN_SKILL = "TRAIN";
    public static final String TO_EQUIP_SKILL = "EQUIP";
    public static final String FIRE_ELEMENT = "fire";
    public static final String EARTH_ELEMENT = "earth";
    public static final String WIND_ELEMENT = "wind";
    public static final String PHYSICAL_ELEMENT = "physical";
    public static final String SWORD_ATTACK_SKILL_NAME = "Sword Attack";
    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String MAZE_TYPE = "maze";
    public static final String CAVERN_TYPE = "cavern";
    public static final int MAX_ITEMS_NUMBER = 200;
    public static final int MIN_ITEMS_NUMBER =3;
    public static final int MIN_MONSTERS_NUMBER = 10;

    //density monster per pixel
    public  static final double MONSTERS_DENSITY=0.05;


    //player
    public static final int PLAYER_ACTIVATION_DISTANCE= 3;
    public static final int MAX_PLAYER_HP = 200;
    public static final int MAX_PLAYER_MP = 100;


    //only thing inside this area around the player will be calculate lineofsight and behaviour
    public static final int GAME_ACTIVE_AREA_RADIUS= 20;

    //map

    public static final int MAX_SIDE_OF_COMPONENT = 26;
    public static final int MAX_DOORS_NUMBER = 2;
    public static final int MAX_COMPONENTS_NUMBER = 5;



    public static final int MONSTERS_DENSITY_INCREASE_PERCENTAGE = 50;
    public static final int ITEMS_INCREASE_PERCENTAGE = 50;
    public static final int EASY_LOOT_DROP_PERCENTAGE = 30;
    public static final int MEDIUM_LOOT_DROP_PERCENTAGE = 50;
    public static final int HARD_LOOT_DROP_PERCENTAGE = 80;
    public static final int MAX_SKILL_LEVEL=5;
    public static int MIN_DISTANCE_FROM_AVERAGE_POSITION=4;
    public static int MAX_TURN_TO_CHECK_ABUSE=30;
    public static int MAX_TURN_TO_RECOVER_MP=5;
    public static int MP_RECOVER=1;

    //store appear percentage
    public static int EQUIPMENT_STORE_APPEAR=100;
    public static int POTION_STORE_APPEAR=100;
}
