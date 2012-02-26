package com.rmit.sea.gameengine.charactermodel.player;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.CharacterDetailInfo;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.item.Equipments;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.iteminterface.MagicProperty;
import com.rmit.sea.gameengine.iteminterface.Armor;
import com.rmit.sea.gameengine.iteminterface.BowWeapon;
import com.rmit.sea.gameengine.iteminterface.Equipable;
import com.rmit.sea.gameengine.iteminterface.HammerWeapon;
import com.rmit.sea.gameengine.iteminterface.Shield;
import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.iteminterface.SwordWeapon;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.iteminterface.Weapon;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.SkillManager;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.ArrowClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.HammerClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements GameCharacter, Attackable, Vulnerable, Serializable {

    private Coordinate coordinate;
    private Damage damage;
    private ActivationDistance activationDistance;
    private String name;
    private String password;
    private String title;
    private int level;
    private int mpRecovery;
    private SkillManager skillManager;
    private CharacterDetailInfo playerDetailInfo;
    private Inventory inventory;
    private Equipments equipments;
    private List<Coordinate> logCoordinate;
    private boolean onSight;
    private boolean isDiscovered;

    public Player(
            SkillManager skillManager,
            Coordinate coordinate,
            int level,
            int mpRecovery,
            String name,
            String password,
            String title,
            ActivationDistance activationDistance,
            Inventory inventory,
            Equipments equipments,
            Damage damage,
            PlayerDetailInfo characterDetailInfo) {

        this.skillManager = skillManager;
        this.coordinate = coordinate;
        this.level = level;
        this.mpRecovery = mpRecovery;
        this.name = name;
        this.password=password;
        this.title = title;
        this.activationDistance = activationDistance;
        this.inventory = inventory;
        this.equipments = equipments;
        this.damage = damage;
        this.playerDetailInfo = characterDetailInfo;
        logCoordinate = new ArrayList<Coordinate>();
        onSight = false;
    }

    public String getPassword() {
        return password;
    }



    public boolean sell(Item item) {
        if (item instanceof Valuable) {
            if (item instanceof Stackable) {
                ((Stackable) item).setAmount(((Stackable) item).getAmount() - 1);
            }
            inventory.addItem(new Gold(((Valuable) item).getValue()/2));
            
            return true;
        } else {
            return false;
        }
    }

    public boolean buy(Item item) {
        if (item instanceof Valuable) {
            Valuable buyItem = (Valuable) item;
            if (buyItem.getValue() > inventory.getCurrentGold()) {
                return false;
            } else {
                if (item instanceof Stackable) {
                    inventory.addItem(((Stackable) item).getOneItem());
                    ((Stackable) item).setAmount(((Stackable) item).getAmount() - 1);
                } else {
                    inventory.addItem(item);
                }
                inventory.useGold(buyItem.getValue());
                
                return true;
            }
        } else {
            return false;
        }
    }

    public void increaseSkillPoint() {
        skillManager.getCurrentTrainSkill().increaseSkillPoints();
    }

    public int getMpRecovery() {
        return mpRecovery;
    }

    public void setMpRecovery(int mpRecovery) {
        this.mpRecovery = mpRecovery;
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
        return activationDistance;
    }

    @Override
    public Skill getEquipSkill() {
        return skillManager.getCurrentEquipSkill();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
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
        return playerDetailInfo;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Damage getDamage() {
        Damage calDamage = damage;
        if (equipments.getRightArmEquipment() != null && equipments.getRightArmEquipment() instanceof Weapon) {
            calDamage = calDamage.mergeDamage(((Weapon) (equipments.getRightArmEquipment())).getDamage());
        }


        if (equipments.getLeftArmEquipment() != null && equipments.getLeftArmEquipment() instanceof Weapon) {
            calDamage = calDamage.mergeDamage(((Weapon) (equipments.getLeftArmEquipment())).getDamage());
        }

        return calDamage;
    }

    @Override
    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    @Override
    public void receiveDamage(Damage damage) {
        //if there is armor use armor
        Damage calDamage = new Damage(damage.getElement(), damage.getDamage());
        if (equipments.getLeftArmEquipment() != null && equipments.getLeftArmEquipment() instanceof Shield) {
            Random r = new Random();
            Shield shield = (Shield) (equipments.getLeftArmEquipment());
            if (r.nextInt(100) < shield.getBlockChance()) {
                calDamage.setDamage(calDamage.getDamage() - shield.getBlockDefense());
            } else {
                calDamage.setDamage(calDamage.getDamage() - shield.getDefense());
            }
        }
        if (equipments.getBodyEquipment() != null && equipments.getBodyEquipment() instanceof Armor) {
            calDamage.setDamage(calDamage.getDamage() - ((Armor) (equipments.getBodyEquipment())).getDefense());
        }

        getCharacterDetailInfo().increaseHp(-calDamage.getDamage());

    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    @Override
    public boolean isSkillUsable(Skill skill) {
        if (useMp(skill.getMpCost(skill.getCurrentLevel()))) {
            if (skill instanceof GeneralClass) {
                return true;
            } else {
                Equipable rightArm = getEquipments().getRightArmEquipment();
                if (rightArm != null) {
                    if (skill instanceof SwordClass && rightArm instanceof SwordWeapon) {
                        return true;
                    } else if (skill instanceof HammerClass && rightArm instanceof HammerWeapon) {
                        return true;
                    } else if (skill instanceof ArrowClass && rightArm instanceof BowWeapon) {
                        return inventory.useArrow(((ArrowClass) skill).getAmountOfArrowUse());
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

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
    public Color getColor() {
        return ColorResources.getColorFor(this);
    }

    @Override
    public char getRepresentChar() {
        return CharacterResources.getCharFor(this);
    }

    @Override
    public boolean getItem(Item item) {

        inventory.addItem(item);

        return true;
    }

    /**
     * call to equip item. It will equip item and remove it from inventory
     * @param item item to equip
     * @return
     */
    public boolean equipItem(Item item) {
        if (item instanceof Equipable) {
            inventory.removeItem(item);
            Equipable old = equipments.equip((Equipable) item);
            if (old != null) {
                inventory.addItem((Item) old);
                if(old instanceof MagicProperty){
                    ((MagicProperty)old).removeSpecialEffect(this);
                }
            }

            if(item instanceof MagicProperty){
                ((MagicProperty)item).applySpecialEffect(this);
            }

            return true;
        } else {
            return false;
        }
    }

    public void setActivationDistance(ActivationDistance activationDistance) {
        this.activationDistance = activationDistance;
    }



    public Equipments getEquipments() {
        return equipments;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAlive() {
        return getCharacterDetailInfo().getHp() > 0;
    }

    /**
     * use the coordinate log to calculate if the player standing still to get more point
     * @return
     */
    public boolean isStandingStill() {
        if (logCoordinate.isEmpty()) {
            return false;
        }
        int aveX = 0;
        int aveY = 0;
        for (Coordinate c : logCoordinate) {
            aveX += c.getX();
            aveY += c.getY();
        }
        aveX = aveX / logCoordinate.size();
        aveY = aveY / logCoordinate.size();
        Coordinate ave = new Coordinate(aveX, aveY);
        return ave.distanceTo(coordinate) < Constant.MIN_DISTANCE_FROM_AVERAGE_POSITION;
    }

    public void addCoordinateToLog(Coordinate co) {

        logCoordinate.add(co);
    }

    public void cleanCoordinateLog() {
        logCoordinate.clear();
    }

    @Override
    public boolean isDiscovered() {
        return isDiscovered;
    }

    @Override
    public void setDiscovered(boolean isDiscovered) {
        this.isDiscovered = isDiscovered;
    }

    /**
     * call this to get the new title, if the title calculated is the same as the old one, return false
     * @return
     */
    public boolean isAcquireNewTitle() {
        String newTitle = "";
        int swordTotalSkill = 0;
        int hammerTotalSkill = 0;
        int arrowTotalSkill = 0;
        int generalTotalSkill = 0;

        for (Skill skill : skillManager.getSwordClassSkills()) {
            swordTotalSkill += skill.getSkillPoints();
        }

        for (Skill skill : skillManager.getHammerClassSkills()) {
            hammerTotalSkill += skill.getSkillPoints();
        }

        for (Skill skill : skillManager.getArrowClassSkills()) {
            arrowTotalSkill += skill.getSkillPoints();
        }

        for (Skill skill : skillManager.getGeneralClassSkills()) {
            generalTotalSkill += skill.getSkillPoints();
        }

        int max = Math.max(Math.max(Math.max(swordTotalSkill, arrowTotalSkill), hammerTotalSkill), generalTotalSkill);
        if (max == generalTotalSkill) {
            if (max > 1000) {
                newTitle = "the Beginner";
            }
            if (max > 3000) {
                newTitle = "the Professional";
            }
            if (max > 5000) {
                newTitle = "the Expert";
            }
        } else if (max == arrowTotalSkill) {
            if (max > 3000) {
                newTitle = "the Archer";
            }
            if (max > 3000) {
                newTitle = "the Hunter";
            }
            if (max > 5000) {
                newTitle = "the Hawkeye";
            }
        } else if (max == hammerTotalSkill) {
            if (max > 3000) {
                newTitle = "the Blacksmith";
            }
            if (max >5000) {
                newTitle= "the Champion";
            }
            if (max > 7000) {
                newTitle = "the Hammerdin";
            }
            if (max > 10000) {
                newTitle = "the Paladin";
            }
        } else if (max == swordTotalSkill) {
            if (max > 1000) {
                newTitle = "the Swordman";
            }
            if (max > 3000) {
                newTitle = "the Knight";
            }
            if (max > 5000) {
                newTitle = "the Samurai";
            }

            if (max > 7000) {
                newTitle = "the Sword Master";
            }


            if (max > 10000) {
                newTitle = "the Invincible";
            }
        }

        int total = generalTotalSkill + hammerTotalSkill + swordTotalSkill + arrowTotalSkill;
        if (total > 50000) {
            newTitle = "the Ultimate Being";
        }

        if(level>=100){
            newTitle = "the Conqueror";
        }

        if(title==null){
            title="";
        }

        if (newTitle==null||title.equals(newTitle)) {
            return false;
        } else {
            title = newTitle;
            return true;
        }

    }
}
