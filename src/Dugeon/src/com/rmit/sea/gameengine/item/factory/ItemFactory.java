/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item.factory;

import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

/**
 *
 * @author gia
 */
public interface ItemFactory {

    Item createItem(Coordinate coordinate, int level);
}
