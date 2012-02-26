/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.model;

/**
 *
 * @author thailycuong1202
 */
public class TurnManager {

    private static Turn turn;

    public TurnManager(Turn firstTurn) {
        turn = firstTurn == null ? Turn.Human : firstTurn;
    }

    public Turn nextTurn() {
        turn = (turn == Turn.Human) ? Turn.Computer : Turn.Human;
        return turn;
    }

    public Turn getCurrentTurn() {
        return turn;
    }
}
