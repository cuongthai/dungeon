package com.rmit.sea.webserver.model;

import com.rmit.sea.gameengine.charactermodel.player.Player;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Steve
 */
public class Score implements Serializable {

    private String userName;
    private float score;
    private Date date;
    
    private int level;
    private int gold;
    private String title;
    private boolean isAlive;


    public Score(Player player) {
        this.userName=player.getName();
        this.date=new Date();
        this.level=player.getLevel();
        this.gold=player.getInventory().getCurrentGold();
        this.title=player.getTitle();
        this.isAlive=player.isAlive();
    }

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Called Equal");
        return userName.equalsIgnoreCase(((Score)obj).userName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        return hash;
    }


    

    public Date getDate() {
        return date;
    }

    public float getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }
}
