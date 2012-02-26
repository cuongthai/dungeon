/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.model;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

/**
 *
 * @author thailycuong1202
 */
public class NavigationManager {

    public static Coordinate move(Coordinate current, Direction direction, int steps) {
        int x = 0, y = 0;
        switch (direction) {
            case FORWARD:
                y = -steps;
                break;
            case BACKWARD:
                y = steps;
                break;
            case LEFT:
                x = -steps;
                break;
            case RIGHT:
                x = steps;
        }

        // Check new position of player with Walls
        return new Coordinate(
                current.getX() + x,
                current.getY() + y);
    }
}
