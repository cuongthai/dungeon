/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.webserver.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Steve
 */
public class ScoreData implements Serializable {

    private Set<Score> highScoredata;

    public ScoreData(Set<Score> highScoredata) {
        this.highScoredata = highScoredata;
    }

    public Set<Score> getHighScoredata() {
        return highScoredata;
    }

    public int size() {
        return highScoredata.size();
    }

    public void add(Score score) {
        System.out.println("Before add -----" + highScoredata.size());
        if (highScoredata.isEmpty()) {
            highScoredata.add(score);
        } else if (highScoredata.contains(score)) {
            highScoredata.remove(score);
            highScoredata.add(score);
        }else if (!highScoredata.contains(score)){
            highScoredata.add(score);
        }
        System.out.println("After add -----" + highScoredata.size());
    }
}
