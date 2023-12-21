package battle.utils;

import item.Item;
import item.modifiers.*;
import item.curing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import move.Move;
import move.modifiers.*;
import pokemon.Pokemon;
import move.status_effect.*;
import trainer.Trainer;

/**
 * Collection of all relevant mechanics and logic for a pokemon battle.
 * Modularized to accommodate the different logic of the types of pokemon battle.
 */
public class Mechanics {
    
    final static int MAX_STAT_CHANGE = 2;
    
    public static void preMoveEffects(ArrayList<TimerTask> eventQueue, JTextArea textarea, Trainer trainer,
            Pokemon user, Item item, JLabel [] userLabels, JProgressBar userHPBar) {
        BattleEvents.addGenericEvent(eventQueue, textarea, "Checking Pre move effects for " + user.getName());
        BattleEvents.addGenericEvent(eventQueue, textarea, trainer.getName() + " used a " + item.getName());
        if (item instanceof ItemHealsHP) {
            if (item instanceof HealSetHP) BattleEvents.addHealingEvent(eventQueue, textarea, ((HealSetHP)item).getHPAmount() , user, userLabels[2], userHPBar);
        }
        if (item instanceof ItemHealStatus) {
            if (item instanceof CuresParalysis) {
                BattleEvents.addStatusEvent(eventQueue, textarea, null, user.getName(), userLabels);
                BattleEvents.addGenericEvent(eventQueue, textarea, user.getName() + " was cured of its paralysis!");
            }
            if (item instanceof CuresSleep) {
                BattleEvents.addStatusEvent(eventQueue, textarea, null, user.getName(), userLabels);
                BattleEvents.addGenericEvent(eventQueue, textarea, user.getName() + " woke up!");
            }
            if (item instanceof CuresBurn) {
                BattleEvents.addStatusEvent(eventQueue, textarea, null, user.getName(), userLabels);
                BattleEvents.addGenericEvent(eventQueue, textarea, user.getName() + " was cured of its burn!");
            }
            if (item instanceof CuresFrozen) {
                BattleEvents.addStatusEvent(eventQueue, textarea, null, user.getName(), userLabels);
                BattleEvents.addGenericEvent(eventQueue, textarea, user.getName() + " thawed out!");
            }
            if (item instanceof CuresPoison) {
                BattleEvents.addStatusEvent(eventQueue, textarea, null, user.getName(), userLabels);
                BattleEvents.addGenericEvent(eventQueue, textarea, user.getName() + " was cured of its poison");
            }
        }
    }
    
    /**
     * Returns true if the item is allowed to be used in the current situation
     * @param item item object with its interfaces
     * @param user pokemon that the item is intended to be used on
     * @return true if the item is allowed to be used
     */
    public static boolean canUseItem(Item item, Pokemon user ) {
        System.out.println("Testing item: " + item.getName());
        
        if(!(item instanceof ItemHealsHP && (user.getCurrent_hp() == user.getCurrent_max_hp()))) return true;
        
        // Pokemon status check
        if(user.getBattle_status() != null) {
            if(item instanceof CuresBurn && user.getBattle_status().equals("BRN")) return true;
            if(item instanceof CuresParalysis && user.getBattle_status().equals("PAR")) return true;
            if(item instanceof CuresPoison && user.getBattle_status().equals("PSN")) return true;
            if(item instanceof CuresSleep && user.getBattle_status().equals("SLP")) return true;
            if(item instanceof CuresFrozen && user.getBattle_status().equals("FRZ")) return true;
        }
        return false;
    }
    
    /**
     * Logic for events happening after moves have been made. Most values are referenced from generation 1.
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param user main pokemon receiving the effects
     * @param target (if applicable) secondary pokemon affected by user's results
     * @param userHP integer display of user pokemon's hp
     * @param targetHP integer display of target pokemon's hp
     * @param userHPBar visual display of user pokemon's hp
     * @param targetHPBar visual display of target pokemon's hp
     */
    public static void postMoveEffects(ArrayList<TimerTask> eventQueue, JTextArea textArea, 
            Pokemon user, Pokemon target, 
            JLabel [] userLabels, JLabel [] targetLabels,
            JProgressBar userHPBar, JProgressBar targetHPBar) {
        BattleEvents.addGenericEvent(eventQueue, textArea, "Checking Post move effects for " + user.getName());

        if (!(user.getBattle_status() == null)) {
            // Note: Gen 1 calculation (1/16)
            int damage = user.getCurrent_max_hp() * 1/16;
            if (user.getBattle_status().equals("PSN")) BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is hurt by its poison!");
            if (user.getBattle_status().equals("BRN")) BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is hurt by its burn!");
            BattleEvents.addDamageEvent(eventQueue, textArea, damage, user, userLabels[2], userHPBar);
        }
        if (user.isLeeched()) {
            BattleEvents.addDamageEvent(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, user, userLabels[2], userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " had its HP sapped!");
            BattleEvents.addHealingEvent(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, target, targetLabels[2], targetHPBar);
        }
        if (user.isHealingOverTime()) {
            BattleEvents.addHealingEvent(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, user, userLabels[2], userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " healed some of its HP!");
        }
        // Berry Check
        if (user.isHoldingItem()) {
            Item item = user.getItem();
            if (item instanceof TriggeredByHP) {
                if((((TriggeredByHP)item).GetHPActivePoint() * user.getCurrent_max_hp() > user.getCurrent_hp()) ){
                    double healAmount = 0;
                    if (item instanceof HealRatioHP) {
                        healAmount = ((HealRatioHP)user.getItem()).getHPRatio() * user.getCurrent_max_hp();   
                    }
                    if (item instanceof HealSetHP) {
                        healAmount = ((HealSetHP)user.getItem()).getHPAmount();   
                    }
                    BattleEvents.addHealingEvent(eventQueue, textArea, (int) healAmount, user, userLabels[2], userHPBar);
                    BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " healed some HP with its " + item.getName() + "!");
                    user.consumeItem();
                }
            }
            
            if (item instanceof TriggeredByStatus && user.getBattle_status() != null) {
                if(item instanceof CuresBurn && user.getBattle_status().equals("BRN")) {
                    user.setBattle_status(null);
                    BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " cured its burn with a " + item.getName());
                    BattleEvents.addStatusEvent(eventQueue, textArea, null, user.getName(), userLabels);
                }
                if(item instanceof CuresParalysis && user.getBattle_status().equals("PAR")) {
                    user.setBattle_status(null);
                    BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " cured its paralysis with a " + item.getName());
                    BattleEvents.addStatusEvent(eventQueue, textArea, null, user.getName(), userLabels);
                }
                if(item instanceof CuresPoison && user.getBattle_status().equals("PSN")) {
                    user.setBattle_status(null);
                    BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " cured its poison with a " + item.getName());
                    BattleEvents.addStatusEvent(eventQueue, textArea, null, user.getName(), userLabels);
                }
            }
        }
    }
    
    /**
     * Returns true if the trainer in question lost the battle.
     * @param trainer trainer in question
     * @return true if the trainer lost the battle
     */
    public static boolean didLose(Trainer trainer) {
        int count = 0;
        for (Pokemon pokemon : trainer.getParty()) {
            if (pokemon.isFainted()) count++;
        }
        if (count == trainer.getParty().size()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Calculates the value of an attack and several factors. 
     * Formula ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100 ) - Gen 1
     * Variables not included: STAB (Same Type Attack Bonus), Random numbering
     * @param level level of the pokemon attacking
     * @param attack attack stat of the pokemon attacking (attack or special attack)
     * @param defense defense stat of the pokemon receiving the attack
     * @param power power stat of the move being used
     * @param type_bonus multiplier based of the type of move vs the receiving pokemon's stat
     * @return true damage value of the move
     */
    public static int calcDamage(int level, int attack, int defense, int power, double type_bonus) {
        int damage = (int) (((((2 * level / 5 + 2) * attack * power / defense) / 50) + 2) * type_bonus );
        return damage;
    }
    
    /**
     * Returns true if the pokemon is able to use its move in the current turn.
     * Also determines if the user is able to break out of its current status and updates that change here.
     * @param user pokemon attempting to use a move
     * @param userLabels display labels correlating to the user
     * @param userHPBar visual representation of the health points of a pokemon
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event descriptions
     * @return true if the pokemon is able to use its move this turn
     */
    public static boolean canUseMove(Pokemon user, JLabel [] userLabels, JProgressBar userHPBar, ArrayList<TimerTask> eventQueue, JTextArea textArea) {
        if (user.isFlinched() == true) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    textArea.setText(user.getName() + " flinched and couldn't move!");
                    user.setFlinched(false);
                }
            });
            return false;
        }

        if (user.isConfused() == true) {
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is confused!");
            int chance_to_hurt = (int) (Math.random() * 2);
            if (chance_to_hurt != 1) return true;
            else {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + " hurt itself in confusion");
                        int damage = (int) (((((2 * user.getLevel() / 5 + 2) * user.getBattle_attack() * 40 / user.getBattle_defense()) / 50) + 2));
                        user.takeDamage(damage);
                        userLabels[2].setText(Integer.toString(user.getCurrent_hp()));
                        userHPBar.setValue(user.getCurrent_hp());

                        if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 2)) {
                            userHPBar.setForeground(Color.yellow);
                        }
                        if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 4)) {
                            userHPBar.setForeground(Color.red);
                        }
                    }
                });     
                return false;
            }
        }
        if (user.getBattle_status() == "PAR") {
            int chance_to_be_paralyzed = (int) (Math.random() * 4);
            if (chance_to_be_paralyzed != 1) return true;
            else {
                BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is paralyzed! It can't move!");
                return false;
            }
        }

        if (user.getBattle_status() == "FRZ") {
            int chance_to_thaw = (int) (Math.random() * 5);
            if (chance_to_thaw == 1) {
                user.setBattle_status(null);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + " thawed out!");
                        userLabels[4].setText("");
                    }
                });
                return true;
            }
            else {
                BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is frozen! It can't move!");
                return false;
            }
        }

        if (user.getBattle_status() == "SLP") {
            user.setSleep_turns(user.getSleep_turns() - 1);
            if (user.getSleep_turns() != 0) {
                BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is asleep!");
                return false;
            } else {
                user.setBattle_status(null);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + " woke up!");
                        userLabels[4].setText("");
                    }
                });
                return true;
            }
        }
        return true;
    }
    
    /**
     * Logic for when a pokemon uses a move
     * @param user pokemon conducting the move
     * @param target pokemon receiving the move (self, ally or enemy)
     * @param userMove move selected by the user
     * @param targetMove move selected by the target (helps determine success of move. EX: User move failed due to target using fly the previous turn)
     * @param userLabels display labels corresponding to the pokemon conducting the move
     * @param targetLabels display labels corresponding to the pokemon receiving the move
     * @param userHPBar visual representation of the health points of the pokemon conducting the move
     * @param targetHPBar visual representation of the health points of the pokemon receiving the move
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     */
    public static void useMove(
            Pokemon user, Pokemon target,
            Move userMove, Move targetMove,
            JLabel [] userLabels, JLabel [] targetLabels,
            JProgressBar userHPBar, JProgressBar targetHPBar,
            ArrayList<TimerTask> eventQueue,
            JTextArea textArea) {
        
        // Pre move check (Status, Confusion, Flinch etc)
        if (canUseMove(user, userLabels, userHPBar,  eventQueue, textArea) == false) return;
        double chance;
        double result;
           
        BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " used " + userMove.getName() + "!");
        
        if (userMove instanceof HealOverTime) {
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + (((HealOverTime)userMove)).getTurnDescription());
            user.setHealingOverTime(true);
        }
        
        // Accuracy check
        chance = (user.getBattle_accuracy() * userMove.getAccuracy() * target.getBattle_evasion() );
        result = Math.random();
        if (target.isInTwoTurn()) {
            if (((TwoTurn)targetMove).getTargetable() == false) result = 5;
        }
        if (result > chance) {
            BattleEvents.addGenericEvent(eventQueue, textArea, "The move missed!");
            return;
        }
        
        // Second half of twoTurn move
        if (userMove instanceof TwoTurn) {
            BattleEvents.addIconReturnEvent(eventQueue, user, userLabels[5]);
        }
        
        if (userMove instanceof ApplyLeech) {
            target.setLeeched(true);
            BattleEvents.addGenericEvent(eventQueue, textArea, target.getName() + " was seeded!");
            return;
        }
        
        // Damage
        double typeMultiplier = TypeModifier.getMultiplier(userMove, target);

        int damage = 0;
        if (userMove instanceof SetDamage) {
            damage = ((SetDamage)userMove).getDamage();
        } else if(userMove instanceof PhysicalAttack) {
            damage = calcDamage(user.getLevel(), user.getBattle_attack(), target.getBattle_defense(), userMove.getPower(), typeMultiplier);   
        } else if(userMove instanceof SpecialAttack){
            damage = calcDamage(user.getLevel(), user.getBattle_special_attack(), target.getBattle_special_defense(), userMove.getPower(), typeMultiplier);
        }
        
        if (typeMultiplier == 0) {
            BattleEvents.addGenericEvent(eventQueue, textArea, "The move had no effect");
            return;
        }
        
        if (userMove instanceof OneHitKO) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    target.setCurrent_hp(0);
                    targetLabels[2].setText(Integer.toString(target.getCurrent_hp()));
                    targetHPBar.setValue(target.getCurrent_hp());
                }});
            BattleEvents.addGenericEvent(eventQueue, textArea, "Its a One Hit KO!");
        }
        
        // Types of damage events
        if (damage != 0) {
            if (user.getBattle_status() == "BRN") damage = damage / 2;
            if (userMove instanceof MultiStrike) {
                int timeHit = (int) ((Math.random() * 3) + 2);
                for (int i = 0; i < timeHit; i++) {
                    target.takeDamage(damage);
                    BattleEvents.addDamageEvent(eventQueue, textArea, damage, target, targetLabels[2], targetHPBar);
                }
                BattleEvents.addGenericEvent(eventQueue, textArea, "It hit " + Integer.toString(timeHit) + " time(s)!");
            } else {
                target.takeDamage(damage);
                BattleEvents.addDamageEvent(eventQueue, textArea, damage, target, targetLabels[2], targetHPBar);
            }
        }
        
        if ((!(userMove instanceof OneHitKO) && typeMultiplier != 1) &&  damage != 0) {
            if(typeMultiplier > 1.0) BattleEvents.addGenericEvent(eventQueue, textArea, "Its super effective!");
            if(typeMultiplier < 1.0 && typeMultiplier > 0) BattleEvents.addGenericEvent(eventQueue, textArea, "Its not very effective...");
        }
       
        if(userMove instanceof Lifesteal) {
            int netHP = (int) (((Lifesteal)userMove).getLifestealRatio() * damage);
            BattleEvents.addHealingEvent(eventQueue, textArea, netHP, user, userLabels[2], userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, target.getName() + ((Lifesteal)userMove).getDescription());
        }
        
        if(userMove instanceof HealsHP) {
            int healAmount = (int) (((HealsHP)userMove).getHealRatio() * user.getCurrent_max_hp());
            BattleEvents.addHealingEvent(eventQueue, textArea, healAmount, user, userLabels[2], userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " healed some HP!");
        }
        
        if (userMove instanceof HasRecoil) {
            int recoilDamage = (int) (((HasRecoil)userMove).getRecoilRatio() * damage);
            BattleEvents.addDamageEvent(eventQueue, textArea, recoilDamage, user, userLabels[2], userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " took damage in recoil!");
        }
               
        if (userMove instanceof ApplyStatChange) {
            double statModChange = ( (ApplyStatChange) userMove).getApplyChance();
            double statModResult = Math.random();
            
            if (statModChange > statModResult) {
                StatisticChanges.applyToTarget(target, userMove, eventQueue, textArea);
                StatisticChanges.applyToUser(user, userMove, eventQueue, textArea);
            }
        }
 
        if(target.getCurrent_hp() <= 0) {
            target.setFainted(true);
            return;
        }

        // Events that happen as a result of the move
        if (userMove instanceof ApplyFlinch) {
            int chance_to_be_flinch = (int) (Math.random() * 10);
            if ( chance_to_be_flinch <= 3 ) {
                target.setFlinched(true);
            }
        }
        
        if (userMove instanceof Recharge) {
            user.setRecharging(true);
        }
        
        if (userMove instanceof ApplyParalyze || userMove instanceof ApplyPoison || 
                userMove instanceof ApplyBurn || userMove instanceof ApplySleep || userMove instanceof ApplyFrozen) {
            if (!(target.getBattle_status() == null)) {
                if (target.getBattle_status().equals("PAR")) BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already paralyzed!");
                if (target.getBattle_status().equals("PSN")) BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already poisoned!");
                if (target.getBattle_status().equals("BRN")) BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already burned!");
                if (target.getBattle_status().equals("SLP")) BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already asleep!");
                if (target.getBattle_status().equals("FRZ")) BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already frozen!");
            } else {
                result = Math.random();
                if (userMove instanceof ApplyParalyze) {
                    chance = ((ApplyParalyze) userMove).getParalyzeChance();
                    if(result < chance) {
                        target.setBattle_status("PAR");
                        BattleEvents.addStatusEvent(eventQueue, textArea, "PAR", target.getName(), targetLabels);
                    }
                }
                if (userMove instanceof ApplyPoison) {
                    chance = ((ApplyPoison) userMove).getPoisonChance();
                    if (result < chance) {
                        target.setBattle_status("PSN");
                        BattleEvents.addStatusEvent(eventQueue, textArea, "PSN", target.getName(), targetLabels);
                    }
                }
                if (userMove instanceof ApplyBurn) {
                    chance = ((ApplyBurn) userMove).getBurnChance();
                    if (result < chance) {
                        target.setBattle_status("BRN");
                        BattleEvents.addStatusEvent(eventQueue, textArea, "BRN", target.getName(), targetLabels);
                    }
                }
                if (userMove instanceof ApplySleep) {
                    chance = ((ApplySleep) userMove).getSleepChance();
                    if (result < chance) {
                        target.setBattle_status("SLP");
                        target.setSleep_turns((int) (Math.random() * 5) + 1);
                        BattleEvents.addStatusEvent(eventQueue, textArea, "SLP", target.getName(), targetLabels);
                    }
                }
                if (userMove instanceof ApplyFrozen) {
                    chance = ((ApplyFrozen) userMove).getFrozenChance();
                    if (result < chance) {
                        target.setBattle_status("FRZ");
                        BattleEvents.addStatusEvent(eventQueue, textArea, "FRZ", target.getName(), targetLabels);
                    }
                }
            }
        }
    }
}
