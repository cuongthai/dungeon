/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.view;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author thailycuong1202
 */
public class GameInfo implements Observer{

    @Override
    public void update(Observable o, Object o1) {
        ConsoleView.out("Game Info update");
    }
    
}
