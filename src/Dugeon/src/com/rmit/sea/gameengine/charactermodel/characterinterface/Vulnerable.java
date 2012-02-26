/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.charactermodel.characterinterface;

import com.rmit.sea.gameengine.charactermodel.Damage;

/**
 *
 * @author hoanggia
 */
public interface Vulnerable {
    void receiveDamage(Damage damage);
}
