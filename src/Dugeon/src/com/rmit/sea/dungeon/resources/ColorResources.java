package com.rmit.sea.dungeon.resources;

import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.item.PowerUpHpPotion;
import com.rmit.sea.gameengine.item.PowerUpMpPotion;
import com.rmit.sea.gameengine.item.scroll.SkillScroll;
import com.rmit.sea.gameengine.charactermodel.monster.Monster;
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
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

public class ColorResources {

    private static final Map<String, Color> objectsColorsMap = new HashMap<String, Color>();

    static {
        objectsColorsMap.put(Wall.class.getName(), Color.LIGHT_GRAY);
        objectsColorsMap.put(UpDoor.class.getName(), Color.CYAN);
        objectsColorsMap.put(DownDoor.class.getName(), Color.CYAN);
        objectsColorsMap.put(Player.class.getName(), Color.WHITE);
        objectsColorsMap.put(Monster.class.getName() + Constant.EASY, Color.GREEN);
        objectsColorsMap.put(Monster.class.getName() + Constant.MEDIUM, Color.BLUE);
        objectsColorsMap.put(Monster.class.getName() + Constant.HARD, Color.RED);
        objectsColorsMap.put(Way.class.getName(), Color.LIGHT_GRAY);

        objectsColorsMap.put(MpPotion.class.getName(), Color.BLUE);
        objectsColorsMap.put(HpPotion.class.getName(), Color.RED);
        objectsColorsMap.put(PowerUpHpPotion.class.getName(), Color.PINK);
        objectsColorsMap.put(PowerUpMpPotion.class.getName(), new Color(128, 0, 128));
        objectsColorsMap.put(SkillScroll.class.getName(), Color.GRAY);
        objectsColorsMap.put(Gold.class.getName(), Color.YELLOW);
        objectsColorsMap.put(Sword.class.getName(), new Color(32, 178, 170));
        objectsColorsMap.put(Hammer.class.getName(),  new Color(32, 178, 170));
        objectsColorsMap.put(Mallet.class.getName(),  new Color(128,0,128));
        objectsColorsMap.put(Bow.class.getName(), new Color(32, 178, 170));
        objectsColorsMap.put(Arrow.class.getName(), new Color(165, 42, 42));
        objectsColorsMap.put(LeatherArmor.class.getName(), new Color(244, 164, 96));
        objectsColorsMap.put(MirrorShield.class.getName(), new Color(100, 149, 237));
        objectsColorsMap.put(ChainMail.class.getName(), Color.LIGHT_GRAY);
        objectsColorsMap.put(PotionStore.class.getName(), Color.WHITE);
        objectsColorsMap.put(EquipmentStore.class.getName(), Color.GRAY);
        objectsColorsMap.put(WallShield.class.getName(), Color.DARK_GRAY);
        objectsColorsMap.put(Katana.class.getName(), Color.LIGHT_GRAY);
        objectsColorsMap.put(HearingAids.class.getName(), Color.LIGHT_GRAY);

    }

    public static Color getColorFor(ViewablePixel p) {
        if (p.isOnSight()) {
            return getOnSightColor(p);
        }

        return Color.DARK_GRAY;
    }

    private static Color getOnSightColor(ViewablePixel p) {

        if (p instanceof Monster) {
            //ConsoleView.out("here"+Monster.class.getName() + Constant.EASY);
            Monster m = (Monster) p;
            return objectsColorsMap.get("com.rmit.sea.gameengine.charactermodel.monster.Monster" + m.getDifficulty());
        }

        return objectsColorsMap.get(p.getClass().getName());
    }
}
