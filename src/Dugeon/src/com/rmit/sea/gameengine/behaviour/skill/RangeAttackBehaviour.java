/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.behaviour.skill;

import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.DoubleDamage;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author gia
 */
public class RangeAttackBehaviour extends AttackBehaviour{
    private Coordinate targetCoordinate;
    private Direction direction;

    public RangeAttackBehaviour(GameCharacter attacker, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate, Direction direction) {
        super(attacker, fullMap, allCharacters, executeCoordinate);
        this.direction=direction;
        targetCoordinate=scanForTarget(direction, fullMap, attacker, ((RangeAttack)(attacker.getEquipSkill())).range(attacker.getEquipSkill().getCurrentLevel()));
    }

    @Override
    public List<Vulnerable> getTargetsToAttack() {
        List<Vulnerable> targets=new ArrayList<Vulnerable>();
        //ConsoleView.out(getPlayer().getEquipSkill());
        if (getSkill() instanceof AttackableSkill&&targetCoordinate!=null) {
            targets = ((AttackableSkill) getSkill()).getTargetsToAttack(getAllCharacters(),
                    (Attackable) getAttacker(),
                    targetCoordinate,
                    getSkill().getCurrentLevel());
        }
        return targets;
    }



    @Override
    public Damage getDamage() {

        Damage skillDamage = ((AttackableSkill) getSkill()).getDamage(getAttacker().getEquipSkill().getCurrentLevel());
        Damage playerDamage = ((Attackable) getAttacker()).getDamage();
        if (getSkill() instanceof DoubleDamage) {
            Random r = new Random();
            if (r.nextInt(100) <= ((DoubleDamage) getSkill()).getDoubleDamagePercentage(getSkill().getCurrentLevel())) {
                skillDamage.setDamage(skillDamage.getDamage() * 2);
            }
        }
        return playerDamage.mergeDamage(skillDamage);
    }

    @Override
    public void specialEffect() {
        if (getSkill() instanceof SpecialEffect) {
            ((SpecialEffect) getSkill()).executeSpecialEffect((Attackable)getAttacker(),
                    getSkill().getCurrentLevel(),
                    targetCoordinate,
                    getAllCharacters(),
                    getFullMap(), direction);
        }
    }



    private  Coordinate scanForTarget(Direction direction, Map<Coordinate, ViewablePixel> fullMap, GameCharacter player, int range) {

        int signumX = 0;
        int signumY = 0;
        switch (direction) {
            case FORWARD:
                signumY = -1;
                break;
            case BACKWARD:
                signumY = 1;
                break;
            case LEFT:
                signumX = -1;
                break;
            case RIGHT:
                signumX = 1;
                break;
        }

        int i = 1;
        Coordinate newCoordinate;
        do {
            newCoordinate = new Coordinate(player.getCoordinate().getX() + signumX * i, player.getCoordinate().getY() + signumY * i);
            i++;
        } while (fullMap.get(newCoordinate) instanceof Walkable&&i<=range+1);
        if (fullMap.get(newCoordinate) instanceof Vulnerable) {
            return newCoordinate;
        } else {
            return null;
        }
    }

}
