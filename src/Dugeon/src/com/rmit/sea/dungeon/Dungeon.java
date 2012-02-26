package com.rmit.sea.dungeon;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.view.GameFrame;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Dungeon {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                Constant.SPRING_CONFIG);
        BeanFactory factory = context;

        GameEngine gameEngine = (GameEngine) factory.getBean("gameEngine");
        GameFrame gameFrame = (GameFrame) factory.getBean("gameFrame");

        gameFrame.getWelcomePanel().setGameFrame(gameFrame);
        gameEngine.setGameFrame(gameFrame);

        // Start game
        gameEngine.createGame();
    }
}
