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
public class MirrorShield extends Item implements Shield,Valuable{
    private int defense;
    private int blockChance;
    private int blockDefense;
    private int value;

    public MirrorShield(Coordinate co,int level){
        super(co);
        generateCharacteristic(level);
    }


    @Override
    public String getDescription() {
        return "The mirror shield which contains a mirror behind the shield will guarantee that you can keep admiring yourself while fighting with monster. Defense: "+defense+". Block chance: "+blockChance+". Block defense: "+blockDefense;
    }

    @Override
    public String getName() {
        return generatePrefix()+" Mirror Shield";
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
        
        defense=level/10+1;
        if(level>50){
            defense=defense*2;
        }
        blockChance=10+r.nextInt(50);
        blockDefense=defense*10;
        value=defense*blockChance*10;
    }
    
    private String generatePrefix(){
        if(blockChance<20){
            return "Poor man's";
        }else if(blockChance<35){
            return "Hard";
            
        }else if(blockChance<55){
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
