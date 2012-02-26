/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thailycuong1202
 */
public interface BehaviourFactory {

    public MoveBehaviour createMoveBehaviour(Direction direction, int steps);

    public AttackBehaviour createAttackBehaviour(GameCharacter attacker,Map<Coordinate,ViewablePixel> fullMap,List<GameCharacter> allCharacters, Coordinate executeCoordinate);

    public AttackBehaviour createRangeAttackBehaviour(GameCharacter attacker,Map<Coordinate,ViewablePixel> fullMap,List<GameCharacter> allCharacters, Coordinate executeCoordinate, Direction direction);

    public GetItemBehaviour createGetItemBehaviour(GameCharacter player,List<Item> items);

    public UseItemBehaviour createUseItemBehaviour(Item item);
}
