package com.rmit.sea.gameengine.charactermodel;

import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.playermodel.skills.Skill;

public interface GameCharacter extends ViewablePixel {

    public ActivationDistance getActivationDistance();

    public Skill getEquipSkill();

    public CharacterDetailInfo getCharacterDetailInfo();

    public Inventory getInventory();

    public boolean getItem(Item item);

    public String getName();

    boolean useMp(int amount);

    boolean isSkillUsable(Skill skill);

    

}
