package com.rmit.sea.gameengine.mapmodel;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.component.MapComponent;
import com.rmit.sea.gameengine.mapmodel.pixel.Door;
import com.rmit.sea.gameengine.mapmodel.pixel.DownDoor;
import com.rmit.sea.gameengine.mapmodel.pixel.UpDoor;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.item.NonStackInventory;
import com.rmit.sea.gameengine.item.factory.MapItemFactory;
import com.rmit.sea.gameengine.store.EquipmentStore;
import com.rmit.sea.gameengine.store.PotionStore;
import com.rmit.sea.gameengine.store.Store;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.HashMap;

public abstract class GameMap {

    private List<MapComponent> mapComponents;
    private Map<Coordinate, ViewablePixel> mapPixels;
    private List<Coordinate> walkableWays;
    private Map<Coordinate, Door> doors;
    private List<Store> stores;
    private Inventory mapInventory;

    public GameMap(List<MapComponent> mapComponents, Map<Coordinate, ViewablePixel> mapPixels) {
        this.mapComponents = mapComponents;
        this.mapPixels = mapPixels;

    }

    public List<MapComponent> getMapComponents() {
        return Collections.unmodifiableList(mapComponents);
    }

    public Map<Coordinate, ViewablePixel> getMapPixels() {
        return Collections.unmodifiableMap(mapPixels);
    }

    public List<Coordinate> getWalkableWays() {
        if (walkableWays == null) {
            walkableWays = new ArrayList<Coordinate>();
            for (Entry<Coordinate, ViewablePixel> entry : mapPixels.entrySet()) {
                if (entry.getValue() instanceof Walkable) {
                    walkableWays.add(entry.getKey());
                }
            }

            walkableWays = Collections.unmodifiableList(walkableWays);
        }
        return walkableWays;

    }

    public boolean isWalkable(Coordinate pixelCoordinate) {
        ViewablePixel vp = mapPixels.get(pixelCoordinate);
        return (vp != null && vp instanceof Walkable);
    }

    public Map<Coordinate, Door> getDoors() {

        return doors;
    }

    public List<Store> getStores() {
        return stores;
    }



    public Door getDoor(Coordinate co) {
        for (Door door : getDoors().values()) {
            if (door.getCoordinate().equals(co)) {
                return door;
            }
        }
        return null;
    }
    public Store getStore(Coordinate co) {
        for (Store store : getStores()) {
            if (store.getCoordinate().equals(co)) {
                return store;
            }
        }
        return null;
    }

    public Inventory getMapInventory() {
        return mapInventory;
    }



    public void initDoors(Coordinate entrance, boolean justGoDown, int level) {
        doors = new HashMap<Coordinate, Door>();
        Door entranceDoor = (!justGoDown) ? new DownDoor(entrance) : new UpDoor(entrance);
        if (!(level == 0 && entranceDoor instanceof UpDoor)) {
            doors.put(entranceDoor.getCoordinate(), entranceDoor);
            mapPixels.put(entranceDoor.getCoordinate(), entranceDoor);
        } else {
            entranceDoor = null;
        }


        Random r = new Random();
        for (int i = 0; i < Constant.MAX_DOORS_NUMBER; i++) {

            if (entranceDoor == null || entranceDoor instanceof UpDoor) {
                Door door = new DownDoor(getWalkableWays().get(r.nextInt(getWalkableWays().size())));
                doors.put(door.getCoordinate(), door);
                mapPixels.put(door.getCoordinate(), door);

            } else if (level != 0) {
                Door door = new UpDoor(getWalkableWays().get(r.nextInt(getWalkableWays().size())));
                doors.put(door.getCoordinate(), door);
                mapPixels.put(door.getCoordinate(), door);
            }
        }


    }

    public void initStore(int level) {
        stores = new ArrayList<Store>();
        List<Coordinate> walks = getWalkableWays();
        Random r = new Random();
        
        if (r.nextInt(100) < Constant.EQUIPMENT_STORE_APPEAR) {
            Store s = new EquipmentStore(walks.get(r.nextInt(walks.size())), level);
            stores.add(s);
            mapPixels.put(s.getCoordinate(), s);
        }
        if (r.nextInt(100) < Constant.POTION_STORE_APPEAR) {
            Store s = new PotionStore(walks.get(r.nextInt(walks.size())), level);
            stores.add(s);
            mapPixels.put(s.getCoordinate(), s);
        }
    }

    public void initItems(int level){
        if (mapInventory == null) {
            mapInventory = new NonStackInventory(new ArrayList<Item>());
        }
        MapItemFactory itemFactory = new MapItemFactory();
        mapInventory.setItems(itemFactory.createItems(this, level));

    }
}
