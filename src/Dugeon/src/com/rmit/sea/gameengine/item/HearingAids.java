/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.MagicProperty;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.model.lineofsight.HearingActivationDistance;

/**
 *
 * @author gia
 */
public class HearingAids extends Item implements MagicProperty,Valuable {

    private int level;
    private int value;
    private int distance;
    private ActivationDistance oldDistance;

    public HearingAids(Coordinate co, int level) {
        super(co);
        this.level = level;
        generateCharacteristic(level);
    }


    @Override
    public String getDescription() {
        return "Hold this in your left hand and you will have no problem hearing people talking behind your back";
    }

    @Override
    public String getName() {
        return "Hearing Aids";
    }

    @Override
    public int getValue() {
        return value;
    }

    private void generateCharacteristic(int level) {
        if(level<30){
            value=3000;
            distance=3;
        }else if(level <60){
            value=6000;
            distance=4;
        }else{
            distance=5;
            value=10000;
        }
    }

    @Override
    public void applySpecialEffect(Player p) {
        oldDistance=p.getActivationDistance();
        p.setActivationDistance(new HearingActivationDistance(distance));
    }

    @Override
    public void removeSpecialEffect(Player p) {
        p.setActivationDistance(oldDistance);

    }

}
