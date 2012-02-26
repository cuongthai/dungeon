/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.model.lineofsight;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.lineofsight.LOSGenerator;
import com.rmit.sea.gameengine.model.lineofsight.LOSTrigoGenerator;
import java.util.List;

/**
 *
 * @author thailycuong1202
 */
public class SightActivationDistance implements ActivationDistance {

    private int distance;
    private List<Coordinate> coors;

    public SightActivationDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int getActivationDistance() {
        return distance;
    }

    @Override
    public List<Coordinate> getCoordinatesOnSight() {
        return coors;
    }

    @Override
    public void setCoordinatesOnSight(List<Coordinate> coors) {

        this.coors = coors;
    }

    @Override
    public LOSGenerator getLOSGenerator() {
        return new LOSTrigoGenerator();
    }

    @Override
    public String toString(){
        return "Sight "+distance;
    }
}
