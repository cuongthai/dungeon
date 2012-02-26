/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.playermodel.skills;

import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerJamSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordAttackSkill;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoanggia
 */
public class SkillFactory {
    public static Skill createSkill(String skillName) {
        try {
            Skill s;
            s = (Skill) Class.forName("com.rmit.sea.gameengine.playermodel.skills.skilllist."+skillName).newInstance();
            return s;
        } catch (InstantiationException ex) {
            Logger.getLogger(SkillFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SkillFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkillFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            return null;
        }
    }


}
