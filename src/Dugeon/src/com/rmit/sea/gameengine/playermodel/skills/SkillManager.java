/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills;

import com.rmit.sea.gameengine.playermodel.skills.skillinterface.ArrowClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.HammerClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thailycuong1202
 */
public class SkillManager implements Serializable{

    private Skill currentTrainSkill;
    private Skill currentEquipSkill;
    private List<Skill> availableSkills;
    private List<Skill> slotSkills;

    public SkillManager(List<Skill> availableSkills) {
        this.availableSkills = availableSkills;
        init();

    }

    public void equipSkill(Skill skill){
        currentEquipSkill=skill;
    }


    

    public void addSkill(Skill skill) {
        for(Skill s:availableSkills){
            if(s.getSkillName().equals(skill.getSkillName())){
               return;
            }
        }
        availableSkills.add(skill);
    }

    public Skill getCurrentTrainSkill() {
        return currentTrainSkill;
    }

    public Skill getCurrentEquipSkill() {
        return currentEquipSkill;
    }

    public void setAvailableSkills(List<Skill> availableSkills) {
        this.availableSkills = availableSkills;
        init();
    }

    public void trainSkill(Skill s){
        currentTrainSkill=s;
    }

    public List<Skill> getAvailableSkills() {
        return availableSkills;
    }

    private void init() {
          //this method is for testing
        //populateAvailableSkill();
        currentTrainSkill = availableSkills.get(0);
        currentEquipSkill = availableSkills.get(0);
    }

    public List<Skill> getSwordClassSkills(){
        List<Skill> skills=new ArrayList<Skill>();
        for(Skill skill:availableSkills){
            if(skill instanceof SwordClass){
                skills.add(skill);
            }
        }
        return skills;
    }
    public List<Skill> getGeneralClassSkills(){
        List<Skill> skills=new ArrayList<Skill>();
        for(Skill skill:availableSkills){
            if(skill instanceof GeneralClass){
                skills.add(skill);
            }
        }
        return skills;
    }
    public List<Skill> getHammerClassSkills(){
        List<Skill> skills=new ArrayList<Skill>();
        for(Skill skill:availableSkills){
            if(skill instanceof HammerClass){
                skills.add(skill);
            }
        }
        return skills;
    }
    public List<Skill> getArrowClassSkills(){
        List<Skill> skills=new ArrayList<Skill>();
        for(Skill skill:availableSkills){
            if(skill instanceof ArrowClass){
                skills.add(skill);
            }
        }
        return skills;
    }



}
