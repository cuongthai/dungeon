package com.rmit.sea.controller;

public class DefaultKeyMeaning implements KeyMeaning {

    public static char KEY_UP = 'z';
    public static char KEY_DOWN = 'c';
    public static char KEY_LEFT = 'a';
    public static char KEY_RIGHT = 'd';
    public static char KEY_FORWARD = 'w';
    public static char KEY_BACKWARD = 's';
    public static char KEY_EXIT_GAME = 'q';
    public static char KEY_ATTACK = 'o';
    public static char KEY_PICKUP = 'g';
    public static char KEY_SELECT_SKILL_TO_TRAIN = 't';
    public static char KEY_TO_EQUIP_SKILL = 'e';
    public static char KEY_TO_EXECUTE_SKILL = 'x';
    //range attack
    public static char KEY_TO_SHOOT_FORWARD = 'W';
    public static char KEY_TO_SHOOT_LEFT = 'A';
    public static char KEY_TO_SHOOT_RIGHT = 'D';
    public static char KEY_TO_SHOOT_BACKWARD = 'S';
    public static char KEY_GET_ITEM = 'g';
    public static char KEY_INVENTORY = 'i';
    public static char KEY_BUY = 'b';
    public static char KEY_SELL = 'f';
    public static char KEY_HELP = 'h';

    @Override
    public char getKeyUp() {
        return KEY_UP;
    }

    @Override
    public char getKeyDown() {
        return KEY_DOWN;
    }

    @Override
    public char getKeyLeft() {
        return KEY_LEFT;
    }

    @Override
    public char getKeyRight() {
        return KEY_RIGHT;
    }

    @Override
    public char getKeyForward() {
        return KEY_FORWARD;
    }

    @Override
    public char getKeyBackward() {
        return KEY_BACKWARD;
    }

    @Override
    public char getKeyExit() {
        return KEY_EXIT_GAME;
    }

    @Override
    public char getKeyAttack() {
        return KEY_ATTACK;
    }

    public static char getKeyPickUp() {
        return KEY_PICKUP;
    }

    @Override
    public char getKeyToSelectSkillToTrain() {
        return KEY_SELECT_SKILL_TO_TRAIN;
    }

    @Override
    public char getKeyToGetItem() {
        return KEY_GET_ITEM;
    }

    @Override
    public char getKeyToEquipSkill() {
        return KEY_TO_EQUIP_SKILL;
    }

    @Override
    public char getKeyToOpenInventory() {
        return KEY_INVENTORY;
    }

    @Override
    public char getKeyToExecuteSkill() {
        return KEY_TO_EXECUTE_SKILL;
    }

    @Override
    public char getKeyToShootForward() {
        return KEY_TO_SHOOT_FORWARD;
    }

    @Override
    public char getKeyToShootBackward() {
        return KEY_TO_SHOOT_BACKWARD;
    }

    @Override
    public char getKeyToShootLeft() {
        return KEY_TO_SHOOT_LEFT;
    }

    @Override
    public char getKeyToShootRight() {
        return KEY_TO_SHOOT_RIGHT;

    }

    @Override
    public char getKeyToBuy() {
        return KEY_BUY;
    }

    @Override
    public char getKeyToSell() {
        return KEY_SELL;
    }

    @Override
    public char getKeyToHelp() {
        return KEY_HELP;
    }
}
