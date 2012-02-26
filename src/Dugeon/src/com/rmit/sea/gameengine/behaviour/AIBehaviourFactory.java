package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.behaviour.skill.SimpleAttackBehaviour;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.Direction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thailycuong1202
 */
public class AIBehaviourFactory implements BehaviourFactory {

    @Override
    public MoveBehaviour createMoveBehaviour(Direction direction, int steps) {
        if (direction == null) {
            return new MoveRandomlyBehaviour(steps);

        } else {
            return new MoveIntentlyBehaviour(direction, steps);
        }
    }

    @Override
    public AttackBehaviour createAttackBehaviour(GameCharacter attacker, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate) {
        return new SimpleAttackBehaviour(attacker, fullMap, allCharacters, executeCoordinate);
    }

    @Override
    public GetItemBehaviour createGetItemBehaviour(GameCharacter player, List<Item> items) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UseItemBehaviour createUseItemBehaviour(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AttackBehaviour createRangeAttackBehaviour(GameCharacter attacker, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
