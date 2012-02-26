/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.Shield;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class WallShield extends Item implements Shield,Valuable{
    private int defense;
    private int blockChance;
    private int blockDefense;
    private int value;

    public WallShield(Coordinate co,int level){
        super(co);
        generateCharacteristic(level);
    }


    @Override
    public String getDescription() {
        return "A Shield with the shape of a wall. Can make you feel at home . Defense: "+defense+". Block chance: "+blockChance+". Block defense: "+blockDefense;
    }

    @Override
    public String getName() {
        return generatePrefix()+" Wall Shield";
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getBlockChance() {
        return blockChance;
    }

    @Override
    public int getBlockDefense() {
        return blockDefense;
    }

    private void generateCharacteristic(int level){
        Random r=new Random();

        defense=(level/10+1)*2;
        if(level>50){
            defense=defense*3;
        }
        blockChance=10+r.nextInt(20);
        blockDefense=defense*10;
        value=defense*blockChance*10;
    }

    private String generatePrefix(){
        if(blockChance<20){
            return "Poor man's";
        }else if(blockChance<25){
            return "Hard";

        }else if(blockChance<29){
            return "Tough";
        }else{
            return "Indestructible";
        }
    }

    @Override
    public int getValue() {
        return value;
    }



}
