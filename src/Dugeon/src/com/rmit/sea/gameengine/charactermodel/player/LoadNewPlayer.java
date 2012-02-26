package com.rmit.sea.gameengine.charactermodel.player;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.item.Arrow;
import com.rmit.sea.gameengine.model.lineofsight.SightActivationDistance;
import com.rmit.sea.gameengine.item.HasInventory;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.item.Bow;
import com.rmit.sea.gameengine.item.ChainMail;
import com.rmit.sea.gameengine.item.Equipments;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Hammer;
import com.rmit.sea.gameengine.item.HearingAids;
import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.item.Katana;
import com.rmit.sea.gameengine.item.LeatherArmor;
import com.rmit.sea.gameengine.item.Mallet;
import com.rmit.sea.gameengine.item.MirrorShield;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.item.PlayerEquipment;
import com.rmit.sea.gameengine.item.PowerUpHpPotion;
import com.rmit.sea.gameengine.item.PowerUpMpPotion;
import com.rmit.sea.gameengine.item.Sword;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.Communicator;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.SkillManager;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicShootingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BlessingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.CatastropheSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.CureSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.DashSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.DigAHoleSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.EarthquakeSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.EquivalentExchangeSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.ExplosiveArrowSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerBattingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerJamSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.JumperSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.LeapSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.ReverseDestinySkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SoulThrustingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordDanceSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordRainSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.KnightThrustSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.PiercingArrowSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.PraySkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.RecoilShootSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.ShockwaveSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordRushSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.UnsheathSkill;
import java.util.ArrayList;
import java.util.List;

public class LoadNewPlayer implements PlayerLoader {

    private static final String SUPER_PLAYER_NAME = "kevinse2";
    private static final String WEAK_PLAYER_NAME = "binmaster";
    private String name;
    private String password;
    private boolean isSuccess;
    private Player player;

    public LoadNewPlayer(String name, String password) {
        this.name = name;
        this.password = password;
        this.isSuccess = true;
        Communicator com = new Communicator();
        if (com.isExisted(name)) {
            isSuccess = false;
        }
    }

    @Override
    public Player getPlayer(Coordinate coordinate) {
        if (name.equals(Constant.SUPER_PLAYER_NAME)) {
            return getSuperUser(coordinate);
        } else if (name.equals(Constant.WEAK_PLAYER_NAME)) {
            return getWeakPlayer(coordinate);
        }

        return getNormalPlayer(coordinate);
    }

    private Player getNormalPlayer(Coordinate coordinate) {
        List<Skill> availableSkills = new ArrayList<Skill>();
        availableSkills.add(new BasicAttackSkill());
        
        SkillManager skillManager = new SkillManager(availableSkills);

        


        ActivationDistance activationDistance = new SightActivationDistance(Constant.PLAYER_ACTIVATION_DISTANCE);
        Inventory inventory = new HasInventory(new ArrayList<Item>());
        inventory.addItem(new Gold(1));
        inventory.addItem(new Arrow(1));

        Equipments equipments = new PlayerEquipment();
        Damage damage = new Damage(Constant.PHYSICAL_ELEMENT, 5);
        PlayerDetailInfo playerDetailInfo = new PlayerDetailInfo(Constant.MAX_PLAYER_HP, Constant.MAX_PLAYER_MP);




        Player p = new Player(skillManager, coordinate, 0, 1, name, password, "",
                activationDistance, inventory, equipments, damage, playerDetailInfo);
        
        
        return p;
    }

    private Player getSuperUser(Coordinate coordinate) {
        List<Skill> availableSkills = new ArrayList<Skill>();
        availableSkills.add(new EarthquakeSkill());
        availableSkills.add(new BasicShootingSkill());
        availableSkills.add(new ExplosiveArrowSkill());
        availableSkills.add(new CatastropheSkill());
        availableSkills.add(new HammerAttackSkill());
        availableSkills.add(new HammerBattingSkill());
        availableSkills.add(new HammerJamSkill());
        availableSkills.add(new LeapSkill());
        availableSkills.add(new ReverseDestinySkill());
        availableSkills.add(new SoulThrustingSkill());
        availableSkills.add(new SwordAttackSkill());
        availableSkills.add(new SwordRainSkill());
        availableSkills.add(new KnightThrustSkill());
        availableSkills.add(new UnsheathSkill());
        availableSkills.add(new SwordDanceSkill());
        availableSkills.add(new SwordRushSkill());
        availableSkills.add(new DigAHoleSkill());
        availableSkills.add(new PiercingArrowSkill());
        availableSkills.add(new CureSkill());
        availableSkills.add(new BasicAttackSkill());
        availableSkills.add(new EquivalentExchangeSkill());
        availableSkills.add(new PraySkill());
        availableSkills.add(new BlessingSkill());
        availableSkills.add(new RecoilShootSkill());
        availableSkills.add(new JumperSkill());
        availableSkills.add(new DashSkill());
        availableSkills.add(new ShockwaveSkill());


        SkillManager skillManager = new SkillManager(availableSkills);

        ActivationDistance activationDistance = new SightActivationDistance(
                Constant.PLAYER_ACTIVATION_DISTANCE);
        List<Item> items = new ArrayList<Item>();

        Inventory inventory = new HasInventory(items);
        inventory.addItem(new Gold(1));
        inventory.addItem(new Arrow(1));
        inventory.addItem(new HpPotion(coordinate, 100, 1));
        inventory.addItem(new MpPotion(coordinate, 100, 1));
        inventory.addItem(new PowerUpHpPotion(coordinate, 100, 1));
        inventory.addItem(new PowerUpMpPotion(coordinate, 100, 1));
        inventory.addItem(new Bow(coordinate, 15));
        inventory.addItem(new Sword(coordinate, 15));
        inventory.addItem(new Katana(coordinate, 15));
        inventory.addItem(new Hammer(coordinate, 15));
        inventory.addItem(new Mallet(coordinate, 15));

        inventory.addItem(new Arrow(coordinate, 100));
        inventory.addItem(new MirrorShield(coordinate, 100));
        inventory.addItem(new ChainMail(coordinate, 100));
        inventory.addItem(new LeatherArmor(coordinate, 100));
        inventory.addItem(new Gold(50000));
        inventory.addItem(new HearingAids(coordinate, 50));
        Equipments equipments = new PlayerEquipment();
        Damage damage = new Damage(Constant.PHYSICAL_ELEMENT, 10);

        PlayerDetailInfo playerDetailInfo = new PlayerDetailInfo(
                Constant.MAX_PLAYER_HP * 2, Constant.MAX_PLAYER_MP * 5);


        return new Player(skillManager, coordinate, 10, 2, name, password, "the Lecturer",
                activationDistance, inventory, equipments, damage, playerDetailInfo);
    }

    private Player getWeakPlayer(Coordinate coordinate) {
        List<Skill> availableSkills = new ArrayList<Skill>();
        availableSkills.add(new LeapSkill());
        availableSkills.get(0).setSkillPoints(10000);
        SkillManager skillManager = new SkillManager(availableSkills);
        ActivationDistance activationDistance = new SightActivationDistance(Constant.PLAYER_ACTIVATION_DISTANCE / 2);
        Inventory inventory = new HasInventory(new ArrayList<Item>());
        inventory.addItem(new Gold(1));
        inventory.addItem(new Arrow(1));
        Equipments equipments = new PlayerEquipment();
        Damage damage = new Damage(Constant.PHYSICAL_ELEMENT, 3);
        PlayerDetailInfo playerDetailInfo = new PlayerDetailInfo(
                Constant.MAX_PLAYER_HP / 2, Constant.MAX_PLAYER_MP * 10);

        return new Player(skillManager, coordinate, 0, 1, name, password, "the Bin Master",
                activationDistance, inventory, equipments, damage, playerDetailInfo);
    }

    @Override
    public boolean isSuccess() {

        return isSuccess;
    }
}
