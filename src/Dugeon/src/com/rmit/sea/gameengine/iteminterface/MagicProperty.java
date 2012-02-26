/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.iteminterface;

import com.rmit.sea.gameengine.charactermodel.player.Player;

/**
 *
 * @author gia
 */
public interface MagicProperty extends Equipable {

    /**
     * apply the special effect and storing the old state before this happen
     * @param p
     */
    void applySpecialEffect(Player p);

    /**
     * restore the old state before the special effect
     * @param p
     */
    void removeSpecialEffect(Player p);
}
