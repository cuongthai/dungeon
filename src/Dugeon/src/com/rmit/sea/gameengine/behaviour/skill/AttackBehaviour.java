/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.behaviour.skill;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.CostHp;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thailycuong1202
 */
public abstract class AttackBehaviour {

    private GameCharacter attacker;
    private List<GameCharacter> allCharacters;
    private Map<Coordinate, ViewablePixel> fullMap;
    private Coordinate executeCoordinate;

    public AttackBehaviour(GameCharacter attacker, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate) {
        this.attacker = attacker;
        this.allCharacters = allCharacters;
        this.executeCoordinate = executeCoordinate;
        this.fullMap = fullMap;


    }

    /**
     * call to execute skill, it will inflict damage and execute special effect
     */
    public void inflictDamage() {
        if (getSkill() instanceof AttackableSkill) {
            for (Vulnerable target : getTargetsToAttack()) {
                target.receiveDamage(getDamage());
            }
        }
        if (getSkill() instanceof CostHp) {

            getAttacker().getCharacterDetailInfo().increaseHp(-((CostHp) getSkill()).getHpCost(getSkill().getCurrentLevel()));
        }
        specialEffect();

    }

    public List<GameCharacter> getAllCharacters() {
        return allCharacters;
    }

    public Map<Coordinate, ViewablePixel> getFullMap() {
        return fullMap;
    }

    public GameCharacter getAttacker() {
        return attacker;
    }

    public Coordinate getExecuteCoordinate() {
        return executeCoordinate;
    }

    public Skill getSkill() {
        return (getAttacker().getEquipSkill());
    }

    public abstract List<Vulnerable> getTargetsToAttack();

    public abstract Damage getDamage();

    public abstract void specialEffect();
}
