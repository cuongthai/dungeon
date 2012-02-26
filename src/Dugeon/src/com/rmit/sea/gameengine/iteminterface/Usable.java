/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.iteminterface;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;

/**
 *
 * @author hoanggia
 */
public interface Usable {
    boolean use(GameCharacter character,int amount);
}
