/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel;

import com.rmit.sea.view.ConsoleView;
import java.io.Serializable;

/**
 *
 * @author thailycuong1202
 */
public abstract class CharacterDetailInfo implements Serializable {

    private int hp;
    private int mp;
    private int maxHp;
    private int maxMp;

    public CharacterDetailInfo(int maxHp, int maxMp) {
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        hp = maxHp;
        mp = maxMp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public void increaseHp(int hp) {
        this.hp += hp;
        if (this.hp > getMaxHp()) {
            this.hp = maxHp;
        }
        if(this.hp<0){
            this.hp=0;
        }
    }

    public void increaseMp(int mp) {
        this.mp += mp;
        if (this.mp > maxMp) {
            this.mp = maxMp;
        }

        if(this.mp<0){
            this.mp=0;
        }
    }

    public int getHp() {
        return hp;

    }

    public int getMp() {
        return mp;
    }

    public void increaseMaxHp(int hp) {
        maxHp += hp;
    }

    public void increaseMaxMp(int mp) {
        maxMp += mp;
    }

    @Override
    public String toString() {
        return "HP: "
                + hp + "/"
                + maxHp
                + "\t  MP: "
                + mp + "/"
                + maxMp;
    }
}
