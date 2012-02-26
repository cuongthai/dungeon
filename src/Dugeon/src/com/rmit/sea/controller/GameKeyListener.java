/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.controller;

import com.rmit.sea.view.ConsoleView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author thailycuong1202
 */
public class GameKeyListener implements KeyListener {

    private final Object lock = new Object();
    private Character input;
    private KeyMeaning keyMeaning;

    public GameKeyListener() {
    }

    public void setKeyMeaning(KeyMeaning keyMeaning) {
        this.keyMeaning = keyMeaning;
    }

    public KeyMeaning getKeyMeaning(KeyMeaning keyMeaning) {
        return keyMeaning;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        synchronized (lock) {
            char keyChar = ke.getKeyChar();
            ConsoleView.out("User pressed " + keyChar);
            input = keyChar;
            lock.notifyAll();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public char getInput() {
        synchronized (lock) {
            if (input == null) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                }
            }

            Character temp = input;
            input = null;
            return temp;
        }
    }

    public KeyMeaning getKeyMeaning() {
        return keyMeaning;
    }

    public void setKeyMeaning(DefaultKeyMeaning defaultKeyMeaning) {
        keyMeaning = defaultKeyMeaning;
    }
}
