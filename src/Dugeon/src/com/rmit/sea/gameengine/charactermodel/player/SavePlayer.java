/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.player;

import com.rmit.sea.gameengine.charactermodel.CharacterDetailInfo;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.item.Equipments;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.lineofsight.ActivationDistance;
import com.rmit.sea.gameengine.playermodel.skills.SkillManager;
import java.util.List;

/**
 *
 * @author gia
 */
public class SavePlayer {

    private Coordinate coordinate;
    private Damage damage;
    private ActivationDistance activationDistance;
    private String name;
    private int level;
    private SkillManager skillManager;
    private CharacterDetailInfo playerDetailInfo;
    private Inventory inventory;
    private Equipments equipments;
    private List<Coordinate> logCoordinate;
    private boolean onSight;
    private boolean isDiscovered;
}
