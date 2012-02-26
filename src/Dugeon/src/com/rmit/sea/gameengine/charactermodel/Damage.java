/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel;

import java.io.Serializable;

/**
 *
 * @author hoanggia
 */
public class Damage implements Serializable {

    private String element;
    private int damage;

    public Damage(String element, int damage) {
        this.element = element;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = Math.max(0, damage);
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Damage mergeDamage(Damage damage) {
        return new Damage(getDamage() > damage.getDamage() ? getElement() :
            damage.getElement(), getDamage() + damage.getDamage());
    }

    @Override
    public String toString() {
        return element + ":" + damage;
    }

    @Override
    public boolean equals(Object o) {
        Damage other=(Damage)o;
        return other.damage==damage && other.element.equalsIgnoreCase(element);
    }
    
}
