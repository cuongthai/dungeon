package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.behaviour.skill.RangeAttackBehaviour;
import com.rmit.sea.gameengine.behaviour.skill.SimpleAttackBehaviour;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.Direction;
import java.util.List;
import java.util.Map;

public class PlayerBehaviourFactory implements BehaviourFactory {

    @Override
    public MoveBehaviour createMoveBehaviour(Direction direction, int steps) {
        return new MoveIntentlyBehaviour(direction, steps);
    }

    @Override
    public AttackBehaviour createAttackBehaviour(GameCharacter player,Map<Coordinate,ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate) {
        //select the right attack behaviour class here(the right skill)

        return new SimpleAttackBehaviour(player,fullMap,allCharacters, executeCoordinate);
    }

    @Override
    public GetItemBehaviour createGetItemBehaviour(GameCharacter player,List<Item> items){
        return new GetItemBehaviour(player,items);
    }

    @Override
    public UseItemBehaviour createUseItemBehaviour(Item item) {
        return new UseItemBehaviour(item,1);
    }

    @Override
    public AttackBehaviour createRangeAttackBehaviour(GameCharacter attacker, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate, Direction direction) {
        return new RangeAttackBehaviour(attacker,fullMap,allCharacters,executeCoordinate,direction);
    }
}
