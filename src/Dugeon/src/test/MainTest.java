package test;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.behaviour.BehaviourFactory;
import com.rmit.sea.gameengine.behaviour.PlayerBehaviourFactory;
import com.rmit.sea.gameengine.behaviour.UseItemBehaviour;
import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.charactermodel.monster.EasyCharacterFactory;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.command.AttackCommand;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.command.UseItemCommand;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.gameengine.charactermodel.player.LoadNewPlayer;
import com.rmit.sea.gameengine.charactermodel.player.PlayerLoader;
import com.rmit.sea.gameengine.item.Hammer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest extends TestCase {

    public void testIncreaseSkillPoint() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                Constant.SPRING_CONFIG);
        BeanFactory factory = context;
        GameEngine gameEngine = (GameEngine) factory.getBean("gameEngine");
        PlayerLoader loader = new LoadNewPlayer("Cuong","123");


        gameEngine.initNewGame(loader);

        List<GameCharacter> computers = new EasyCharacterFactory().createComputerCharacter(gameEngine.getGameMap(), 1, 1, 1);
        computers.get(0).setCoordinate(new Coordinate(2, 2));
        List<GameCharacter> chs = new ArrayList<GameCharacter>();
        Player player = gameEngine.getCharacterManager().getPlayer();
        chs.add(player);
        chs.add(computers.get(0));

        player.increaseSkillPoint();

        assertEquals(1,
                gameEngine.getCharacterManager().getPlayer().getEquipSkill().getSkillPoints());

    }

    public void testUseItemHP() {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                Constant.SPRING_CONFIG);
        BeanFactory factory = context;
        GameEngine gameEngine = (GameEngine) factory.getBean("gameEngine");
        PlayerLoader loader = new LoadNewPlayer("Cuong","123");


        gameEngine.initNewGame(loader);

        List<GameCharacter> computers = new EasyCharacterFactory().createComputerCharacter(gameEngine.getGameMap(), 1, 1, 1);
        computers.get(0).setCoordinate(new Coordinate(2, 2));
        List<GameCharacter> chs = new ArrayList<GameCharacter>();
        Player player = gameEngine.getCharacterManager().getPlayer();
        chs.add(player);
        chs.add(computers.get(0));

        player.increaseSkillPoint();
        player.receiveDamage(new Damage("Test", 12));
        System.out.println(player.getCharacterDetailInfo().getHp());
        HpPotion hp = new HpPotion(new Coordinate(1, 2), 2, 1);
        UseItemBehaviour behaviour = new UseItemBehaviour(hp, 1);
        Command hpCommand = new UseItemCommand(player, behaviour);
        assertEquals(188,
                gameEngine.getCharacterManager().getPlayer().getCharacterDetailInfo().getHp());
        hpCommand.execute();
        assertEquals(200,
                gameEngine.getCharacterManager().getPlayer().getCharacterDetailInfo().getHp());

    }

    public void testUseItemMP() {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                Constant.SPRING_CONFIG);
        BeanFactory factory = context;
        GameEngine gameEngine = (GameEngine) factory.getBean("gameEngine");
        PlayerLoader loader = new LoadNewPlayer("kevinse2","123");


        gameEngine.initNewGame(loader);

        List<GameCharacter> computers = new EasyCharacterFactory().createComputerCharacter(gameEngine.getGameMap(), 1, 1, 1);
        computers.get(0).setCoordinate(new Coordinate(2, 2));
        List<GameCharacter> chs = new ArrayList<GameCharacter>();
        Player player = gameEngine.getCharacterManager().getPlayer();
        player.setCoordinate(new Coordinate(1, 2));
        chs.add(player);
        chs.add(computers.get(0));

        // Equip a hammer to player
        player.equipItem(new Hammer(new Coordinate(1, 2), 2));

        //Current MP
        assertEquals(500,
                player.getCharacterDetailInfo().getMp());
        BehaviourFactory fac = new PlayerBehaviourFactory();

        //Prepare to attack
        AttackBehaviour be = fac.createAttackBehaviour(player, gameEngine.getGameMap().getMapPixels(), computers, computers.get(0).getCoordinate());
        AttackCommand cmd = new AttackCommand(be);
        cmd.execute();

        //Computer's hp is decreased
        assertTrue(500 > player.getCharacterDetailInfo().getMp());

        //Use MP
        MpPotion mpPotion = new MpPotion(new Coordinate(1, 2), 2, 1);
        UseItemBehaviour behaviour = new UseItemBehaviour(mpPotion, 1);
        Command hpCommand = new UseItemCommand(player, behaviour);
        hpCommand.execute();

        assertTrue(500
                == player.getCharacterDetailInfo().getMp());

    }
}