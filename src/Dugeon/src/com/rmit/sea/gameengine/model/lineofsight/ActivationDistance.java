/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.model.lineofsight;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thailycuong1202
 */
public interface ActivationDistance extends Serializable {
    public int getActivationDistance();

    public List<Coordinate> getCoordinatesOnSight();
    public void setCoordinatesOnSight(List<Coordinate> coors);

    public LOSGenerator getLOSGenerator();



}
