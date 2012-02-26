/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.model.lineofsight;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.lineofsight.LOSGenerator;
import com.rmit.sea.gameengine.model.lineofsight.LOSHearingGenerator;
import java.util.List;

/**
 *
 * @author gia
 */
public class HearingActivationDistance  implements ActivationDistance {

    private int distance;
    private List<Coordinate> coors;

    public HearingActivationDistance(int distance) {
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

    public LOSGenerator getLOSGenerator(){
        return new LOSHearingGenerator();
    }

     @Override
    public String toString(){
        return "Hear "+distance;
    }
}