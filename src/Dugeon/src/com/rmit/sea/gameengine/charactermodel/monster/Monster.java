package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.CharacterDetailInfo;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.item.NoInventory;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.item.factory.ItemFactory;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import java.awt.Color;

public abstract class Monster implements GameCharacter, Attackable, Vulnerable {

    private Coordinate coordinate;
    private Damage damage;
    private ActivationDistance distance;
    private Skill skill;
    private String name;
    private CharacterDetailInfo computerDetailInfo;
    private Inventory inventoryList;
    private ItemFactory itemFactory;
    private boolean onSight;
    private boolean isDiscovered;
    private String difficulty;
    private AIAlgorithm ai;

    public Monster(Coordinate coordinate, String difficulty, int level) {
        this.coordinate = coordinate;
        this.name = MonsterNameGenerator.getInstance().getNextName(difficulty, getMonsterNameType());
        this.inventoryList = new NoInventory();
        //just for test
        onSight = false;
        this.difficulty = difficulty;
        setupDifficulty(difficulty, level);
    }

    public abstract String getMonsterNameType();

    public void setComputerDetailInfo(CharacterDetailInfo computerDetailInfo) {
        this.computerDetailInfo = computerDetailInfo;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate newCoordinate) {
        coordinate = newCoordinate;
    }

    @Override
    public ActivationDistance getActivationDistance() {
        return distance;
    }

    public void setDistance(ActivationDistance distance) {
        this.distance = distance;
    }

    @Override
    public Skill getEquipSkill() {
        return skill;
    }

    public void setEquipSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public Inventory getInventory() {
        return inventoryList;
    }

    @Override
    public boolean isOnSight() {
        return onSight;
    }

    @Override
    public void setOnSight(boolean onSight) {
        this.onSight = onSight;
    }

    @Override
    public CharacterDetailInfo getCharacterDetailInfo() {
        return computerDetailInfo;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

    @Override
    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    @Override
    public void receiveDamage(Damage damage) {
        //if there is armor use armor
        //no armor now so just deduce the hp
        computerDetailInfo.increaseHp(-damage.getDamage());

    }

    @Override
    public Color getColor() {

        return ColorResources.getColorFor(this);
    }

    @Override
    public char getRepresentChar() {
        return CharacterResources.getCharFor(this);
    }

    @Override
    public boolean getItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isDiscovered() {
        return isDiscovered;
    }

    @Override
    public void setDiscovered(boolean isDiscovered) {
        this.isDiscovered = isDiscovered;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public AIAlgorithm getAi() {
        return ai;
    }

    public void setAi(AIAlgorithm ai) {
        this.ai = ai;
    }

    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    public void setItemFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }



    public abstract void setupDifficulty(String difficulty, int level);

    @Override
    public boolean useMp(int mp) {
        if (getCharacterDetailInfo().getMp() < mp) {
            return false;
        } else {
            getCharacterDetailInfo().increaseMp(-mp);
            return true;
        }
    }

    @Override
    public boolean isSkillUsable(Skill skill) {
        return useMp(skill.getMpCost(skill.getCurrentLevel())) ;

    }
}

