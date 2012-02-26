/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.iteminterface;

/**
 *
 * @author gia
 */
public interface Shield extends Equipable{
    int getDefense();
    int getBlockChance();
    int getBlockDefense();
}
