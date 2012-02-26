package com.rmit.sea.gameengine.mapmodel;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.component.MapComponent;
import com.rmit.sea.gameengine.mapmodel.pixel.Door;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.mapmodel.pixel.Way;
import com.rmit.sea.dungeon.resources.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MapView extends GameMap {

    public MapView(List<MapComponent> mapComponents, Map<Coordinate, ViewablePixel> mapPixels) {
        super(mapComponents, mapPixels);
    }

    
}
