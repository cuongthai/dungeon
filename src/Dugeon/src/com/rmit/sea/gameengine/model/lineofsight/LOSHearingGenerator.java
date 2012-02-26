/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.model.lineofsight;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gia
 */
public class LOSHearingGenerator implements LOSGenerator {

    private final int STEP = 1;

    @Override
    public void generate(
            GameCharacter character,
            Map<Coordinate, ViewablePixel> map, Map<Coordinate, ViewablePixel> mapOnly,
            double degree) {
        // Validate params
        if (character == null || map == null || map.isEmpty()
                || degree <= 0 || degree > 360) {
            return;
        }

        List<Coordinate> onsightsCoor = null;
        onsightsCoor = new ArrayList<Coordinate>();

        Coordinate characterCoordinate = character.getCoordinate();
        onsightsCoor.add(characterCoordinate);

        int characterActivationDistance =
                character.getActivationDistance().
                getActivationDistance();

        for (int i = 0; i < degree; i += STEP) {
            generateLine(map, characterCoordinate, i, characterActivationDistance, onsightsCoor);
        }

        if (character instanceof Player) {



            for (Coordinate coordinate : onsightsCoor) {
                ViewablePixel vp = map.get(coordinate);
                if (vp != null) {
                    vp.setOnSight(true);
                    vp.setDiscovered(true);
                }
            }
//                ViewablePixel vpMap = mapOnly.get(coordinate);
//                if (vpMap != null) {
//                    vpMap.setOnSight(true);
//                    vpMap.setDiscovered(true);
//                }
//
//            }

        }
        character.getActivationDistance().setCoordinatesOnSight(onsightsCoor);
    }

    /**
     * Generate a line of light
     * @param map
     * @param source of the light
     * @param degree
     * @param length of line
     * @param destinations to store the line
     */
    private void generateLine(Map<Coordinate, ViewablePixel> map, Coordinate source, int degree, int length, List<Coordinate> destinations) {
        double radians = Math.toRadians(degree);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        for (int i = 1; i <= length; ++i) {
            Coordinate c = calPoint(source, sin, cos, i);
            destinations.add(c);
        }







    }

    private Coordinate calPoint(Coordinate source, double sin, double cos, int distance) {
        int xNow = (int) (Math.round(source.getX() + distance * cos));
        int yNow = (int) (Math.round(source.getY() + distance * sin));
        return new Coordinate(xNow, yNow);
    }

    @Override
    public void remove(Map<Coordinate, ViewablePixel> map) {
        for (ViewablePixel p : map.values()) {
            p.setOnSight(false);
        }
    }
}

