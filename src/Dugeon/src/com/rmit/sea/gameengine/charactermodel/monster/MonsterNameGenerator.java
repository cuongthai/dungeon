/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.dungeon.resources.Constant;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoanggia
 */
public class MonsterNameGenerator {

    private static List<String> names;
    private static MonsterNameGenerator instance;

    private MonsterNameGenerator() {
    }

    public static MonsterNameGenerator getInstance() {
        if (instance == null) {
            instance = new MonsterNameGenerator();
        }
        return instance;
    }

    public String getNextName(String difficulty, String type) {
        if (!difficulty.equals(Constant.HARD)) {
            //in sprint 2, it will return the actual monster type
            return type;
        }
        if (names == null) {
            getNamesList();
        }
        
        if(names.isEmpty()){
            return "monster";
        }
        
        Random r = new Random();
        return names.get(r.nextInt(names.size())) + " the " + type;
    }

    private static void getNamesList() {
        try {
            names = new ArrayList<String>();
            Scanner s = new Scanner(new File(Constant.RES_DIR + "name.csv"));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] nameArray = line.split(",");
                names.addAll(Arrays.asList(nameArray));
            }

        } catch (FileNotFoundException ex) {
        }
    }
}
