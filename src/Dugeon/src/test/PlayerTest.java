package test;

import com.rmit.sea.controller.KeyMeaning;
import com.rmit.sea.dungeon.exception.NoCommandException;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.behaviour.BehaviourFactory;
import com.rmit.sea.gameengine.behaviour.MoveBehaviour;
import com.rmit.sea.gameengine.behaviour.PlayerBehaviourFactory;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.model.lineofsight.SightActivationDistance;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.charactermodel.monster.EasyCharacterFactory;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.charactermodel.player.PlayerDetailInfo;
import com.rmit.sea.gameengine.command.AttackCommand;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.command.MoveCommand;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.monster.AIAlgorithm;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.model.GameEngine;
import com.rmit.sea.gameengine.charactermodel.player.LoadNewPlayer;
import com.rmit.sea.gameengine.charactermodel.player.PlayerLoader;
import com.rmit.sea.gameengine.charactermodel.monster.EasyAIAlgorithm;
import com.rmit.sea.gameengine.item.Equipments;
import com.rmit.sea.gameengine.item.PlayerEquipment;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.SkillManager;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicAttackSkill;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import junit.framework.TestCase;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlayerTest extends TestCase {

    private Player playerUnderTest;

    public PlayerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        playerUnderTest = Mockito.mock(Player.class);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of behave method
     */
    public void testMoveBehaviour() throws NoCommandException {

        Coordinate playerCoor = new Coordinate(10, 20);
        Coordinate destination = new Coordinate(9, 20);

        PlayerLoader loader;
        Player realPlayer;
        GameEngine gameEngine;

        do {
            ApplicationContext context = new ClassPathXmlApplicationContext(Constant.SPRING_CONFIG);
            BeanFactory factory = context;
            gameEngine = (GameEngine) factory.getBean("gameEngine");

            loader = new LoadNewPlayer("Cuong", "123");
            gameEngine.initNewGame(loader);

            // Generate the map until the destination are walkable
        } while (!gameEngine.getGameMap().isWalkable(destination));


        realPlayer = loader.getPlayer(playerCoor);

        BehaviourFactory behaviourFactory = new PlayerBehaviourFactory();
        MoveBehaviour moveBehaviour = behaviourFactory.createMoveBehaviour(Direction.LEFT, 1);
        Command moveCommand = new MoveCommand(realPlayer, gameEngine.getGameMap(), gameEngine.getCharacterManager().getAllGameCharacters(), moveBehaviour);

        moveCommand.execute();

        assertEquals(destination, realPlayer.getCoordinate());
    }

    /**
     * Test of SetCommand method
     */
    public void testMoveCommand() throws NoCommandException {
        Command command = Mockito.mock(MoveCommand.class);
        List<Skill> availableSkills = new ArrayList<Skill>();

        availableSkills.add(
                new BasicAttackSkill());
        SkillManager skillManager = new SkillManager(availableSkills);
        Coordinate coo = Mockito.mock(Coordinate.class);
        ActivationDistance activationDistance = Mockito.mock(ActivationDistance.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        Equipments equipments = new PlayerEquipment();
        Damage damage = new Damage(Constant.PHYSICAL_ELEMENT, 5);
        PlayerDetailInfo playerDetailInfo = Mockito.mock(PlayerDetailInfo.class);
        playerUnderTest = new Player(skillManager, coo, 0, 1, "Cuong", "123", "",
                activationDistance, inventory, equipments, damage, playerDetailInfo);

        command.execute();

        command.execute();

        Mockito.verify(command, Mockito.times(2)).execute();
    }

    private Character getRandomKey(KeyMeaning meaning) {
        List<Character> keys = new ArrayList<Character>();
        keys.add(meaning.getKeyBackward());
        keys.add(meaning.getKeyForward());
        keys.add(meaning.getKeyLeft());
        keys.add(meaning.getKeyRight());
        Random r = new Random();
        int k = r.nextInt(4);
        return keys.get(k);
    }

    /**
     * Test of SetCommand method
     */
    public void testBeingAttackedCommand() throws NoCommandException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                Constant.SPRING_CONFIG);
        BeanFactory factory = context;
        GameEngine gameEngine = (GameEngine) factory.getBean("gameEngine");
        PlayerLoader loader = new LoadNewPlayer("Cuong", "123");

        Mockito.stub(playerUnderTest.getCoordinate()).toReturn(new Coordinate(1, 2));
        gameEngine.initNewGame(loader);
        gameEngine.getCharacterManager().setPlayer(playerUnderTest);

        List<GameCharacter> computers = new EasyCharacterFactory().createComputerCharacter(gameEngine.getGameMap(), 1, 1, 1);
        computers.get(0).setCoordinate(new Coordinate(2, 2));
        List<GameCharacter> chs = new ArrayList<GameCharacter>();
        chs.add(playerUnderTest);
        chs.add(computers.get(0));

        AIAlgorithm ai = new EasyAIAlgorithm();

        AttackCommand cmd =
                (AttackCommand) ai.getCommand(computers.get(0), playerUnderTest, gameEngine.getGameMap(),
                gameEngine.getGameMap().getMapPixels(), gameEngine.getCharacterManager().getAllGameCharacters());

        Damage damage = cmd.getAttackBehaviour().getDamage();

        cmd.execute();
        Mockito.verify(playerUnderTest).receiveDamage(damage);
    }
}
