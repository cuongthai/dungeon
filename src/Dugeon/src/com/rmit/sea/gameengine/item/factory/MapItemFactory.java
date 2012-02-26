package com.rmit.sea.gameengine.item.factory;

import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.item.Arrow;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.HearingAids;
import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.item.MpPotion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hoanggia
 */
public class MapItemFactory implements ItemFactory {

    /**
     * get the item that the map will have when go to a new map
     * @param coordinate where to put the item
     * @param level ground level
     * @return
     */
    @Override
    public Item createItem(Coordinate coordinate, int level) {
        Random r = new Random();
        int randomInt = r.nextInt(75);
        if (randomInt < 10) {
            return new HpPotion(coordinate, level,1);
        } else if (randomInt < 20) {
            return new MpPotion(coordinate, level,1);
        } else if (randomInt < 40) {
            return new Arrow(coordinate, level);
        } else if(randomInt<41){
            return new HearingAids(coordinate, level);
        } else {
            return new Gold(coordinate, level);
        }
    }

    /**
     * generate a list of item and put them in the map
     * @param gameMap the current GameMap
     * @param level the current ground level
     * @return
     */
    public List<Item> createItems(GameMap gameMap, int level) {
        Random r = new Random();
        List<Item> items = new ArrayList<Item>();
        List<Coordinate> coordinateContainSomethings = new ArrayList<Coordinate>();
        int numberOfItem = Constant.MIN_ITEMS_NUMBER;
        while (numberOfItem < Constant.MAX_ITEMS_NUMBER && r.nextInt(100) < Constant.ITEMS_INCREASE_PERCENTAGE) {
            numberOfItem++;
        }
        for (int i = 0; i < numberOfItem; i++) {
            Coordinate nextCoordinate;
            do {
                nextCoordinate = gameMap.getWalkableWays().get(r.nextInt(gameMap.getWalkableWays().size()));

            } while (coordinateContainSomethings.contains(nextCoordinate));
            coordinateContainSomethings.add(nextCoordinate);
            Item item = createItem(nextCoordinate, level);
            items.add(item);
            if (coordinateContainSomethings.size() == gameMap.getWalkableWays().size() - 1) {
                break;
            }

        }
        return items;
    }
}
