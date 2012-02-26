/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.iteminterface;

import com.rmit.sea.gameengine.charactermodel.Damage;

/**
 *
 * @author gia
 */
public interface Weapon extends Equipable{
    Damage getDamage();
}
