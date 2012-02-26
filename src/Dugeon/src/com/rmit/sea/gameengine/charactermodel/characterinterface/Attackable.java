package com.rmit.sea.gameengine.charactermodel.characterinterface;

import com.rmit.sea.gameengine.charactermodel.Damage;

/**
 *
 * @author thailycuong1202
 */
public interface Attackable {
    public Damage getDamage();

    public void setDamage(Damage damage);
}
