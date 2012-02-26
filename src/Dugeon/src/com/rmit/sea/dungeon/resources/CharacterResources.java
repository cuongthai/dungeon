package com.rmit.sea.dungeon.resources;

import com.rmit.sea.gameengine.charactermodel.monster.Bat;
import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.item.PowerUpHpPotion;
import com.rmit.sea.gameengine.item.PowerUpMpPotion;
import com.rmit.sea.gameengine.item.scroll.SkillScroll;
import com.rmit.sea.gameengine.charactermodel.monster.Monster;
import com.rmit.sea.gameengine.charactermodel.monster.Orc;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.Arrow;
import com.rmit.sea.gameengine.item.Bow;
import com.rmit.sea.gameengine.item.ChainMail;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Hammer;
import com.rmit.sea.gameengine.item.HearingAids;
import com.rmit.sea.gameengine.item.Katana;
import com.rmit.sea.gameengine.item.LeatherArmor;
import com.rmit.sea.gameengine.item.Mallet;
import com.rmit.sea.gameengine.item.MirrorShield;
import com.rmit.sea.gameengine.item.Sword;
import com.rmit.sea.gameengine.item.WallShield;
import com.rmit.sea.gameengine.mapmodel.pixel.DownDoor;
import com.rmit.sea.gameengine.mapmodel.pixel.UpDoor;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Wall;
import com.rmit.sea.gameengine.mapmodel.pixel.Way;
import com.rmit.sea.gameengine.store.EquipmentStore;
import com.rmit.sea.gameengine.store.PotionStore;
import com.rmit.sea.view.ConsoleView;
import java.util.Map;
import java.util.HashMap;

public class CharacterResources {

    private static final Map<String, Character> objectsCharsMap = new HashMap<String, Character>();

    static {
        objectsCharsMap.put(Wall.class.getName(), '#');
        //objectsCharsMap.put(Wall.class.getName(), '#');
        objectsCharsMap.put(UpDoor.class.getName(), '\u2191');
        objectsCharsMap.put(DownDoor.class.getName(), '\u2193');
        objectsCharsMap.put(Player.class.getName(), '@');
        objectsCharsMap.put(Monster.class.getName(), 'M');
        objectsCharsMap.put(Orc.class.getName(), '\uFB77');
        objectsCharsMap.put(Bat.class.getName(), '\u262C');
        //objectsCharsMap.put(Way.class.getName(), '.');
        objectsCharsMap.put(Way.class.getName(), '_');
        objectsCharsMap.put(MpPotion.class.getName(), '\u26B1');
        objectsCharsMap.put(HpPotion.class.getName(), '\u26B1');
        objectsCharsMap.put(PowerUpMpPotion.class.getName(), '\u26B1');
        objectsCharsMap.put(PowerUpHpPotion.class.getName(), '\u26B1');
        objectsCharsMap.put(Gold.class.getName(), '$');
        objectsCharsMap.put(Sword.class.getName(), '\u2694');
        objectsCharsMap.put(Hammer.class.getName(), '\u2692');
        objectsCharsMap.put(Mallet.class.getName(), '\u2692');
        objectsCharsMap.put(Bow.class.getName(), '}');
        objectsCharsMap.put(Arrow.class.getName(), '\u27B6');
        objectsCharsMap.put(LeatherArmor.class.getName(), '\u271F');
        objectsCharsMap.put(MirrorShield.class.getName(), '\u25CD');
        objectsCharsMap.put(ChainMail.class.getName(), '\u271F');
        objectsCharsMap.put(WallShield.class.getName(), '#');
        objectsCharsMap.put(Katana.class.getName(), '\u2694');
        objectsCharsMap.put(HearingAids.class.getName(), '\u264C');

       // objectsCharsMap.put(SkillScroll.class.getName(), '۩');
        objectsCharsMap.put(SkillScroll.class.getName(), '%');
        objectsCharsMap.put(PotionStore.class.getName(), '\u2695');
        objectsCharsMap.put(EquipmentStore.class.getName(), '\u2696');

    }

    public static char getCharFor(ViewablePixel p) {

        if(p instanceof SkillScroll){
            ConsoleView.out("++++++++++++++++++++++++++++ " + p.getClass().getName());
            ConsoleView.out(      objectsCharsMap.get(p.getClass().getName())
);
        }
        return objectsCharsMap.get(p.getClass().getName());

    }
}
