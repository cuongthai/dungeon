package com.rmit.sea.gameengine.model.lineofsight;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import java.util.List;
import java.util.Map;

public interface LOSGenerator {

    void generate(GameCharacter character,
            Map<Coordinate, ViewablePixel> map,Map<Coordinate,ViewablePixel> mapOnly,
            double angle);
    void remove(Map<Coordinate, ViewablePixel> map);
}
