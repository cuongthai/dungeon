/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.Armor;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class ChainMail extends Item implements Armor,Valuable {
    private int defense;
    private int level;
    private int value;

    public ChainMail(Coordinate co,int level){
        super(co);
        this.level=level;
        generateCharacteristic(level);
    }


    @Override
    public String getDescription() {
        return "Quite heavy but keep youself protected. Armor: "+defense;
    }

    @Override
    public String getName() {
        return generatePrefix(level)+" Chain Mail";
    }

    @Override
    public int getDefense() {
        return defense;
    }

    private void generateCharacteristic(int level) {
        Random r=new Random();
        int characteristic=r.nextInt((level/10+1));
        defense=((level/10+1)+characteristic)*2;
        value=((level/10+1)+characteristic)*500;
        if(level>50){
            defense=defense*2;
            value=value*2;
        }
    }

    private String generatePrefix(int level) {
        int max=(level/10+1)*4-2;
        int min=(level/10+1)*2;
        if(level>50){
            max=max*2;
            min=min*2;
        }

        if(defense<min+min/2){
            return "Iron";
        }else if(defense<max-min/4){
            return "Silver";
        }else if(defense==max){
            return "Mythril";
        }else{
            return "Golden";
        }
    }

    @Override
    public int getValue() {
        return value;
    }



}
