package battle.utils;

import item.modifiers.TriggeredByHP;
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
    
    /**
     * Console prints information of a pokemon during battle for debugging purposes
     * @param pokemon pokemon stats to display
     */
    public static void displayBattleStatsToConsole(Pokemon pokemon) {
        System.out.println("Turn report for: " + pokemon.getName() + " Recharging? -> " + pokemon.isRecharging());
        System.out.println("Attack: " + pokemon.getBattle_attack() + " (" + pokemon.getCurrent_attack()+ ")");
        System.out.println("Defense: " + pokemon.getBattle_defense() + " (" + pokemon.getCurrent_defense()+ ")");
        System.out.println("Special Attack: " + pokemon.getBattle_special_attack() + " (" + pokemon.getCurrent_special_attack()+ ")");
        System.out.println("Special Defense: " + pokemon.getBattle_special_defense() + " (" + pokemon.getCurrent_special_defense()+ ")");
        System.out.println("Speed: " + pokemon.getBattle_speed() + " (" + pokemon.getCurrent_speed()+ ")");
        System.out.println("Current Accuracy Modifier: " + pokemon.getBattle_accuracy());
        System.out.println("Curretn Evasion Modifier: " + pokemon.getBattle_evasion());
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
            JLabel userHP, JLabel targetHP,
            JProgressBar userHPBar, JProgressBar targetHPBar) {
        BattleEvents.addGenericEvent(eventQueue, textArea, "Checking Post move effects for " + user.getName());

        if (!(user.getBattle_status() == null)) {
            // Note: Gen 1 calculation (1/16)
            if (user.getBattle_status().equals("PSN")) {
                
                BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is hurt by its poison!");
                BattleEvents.addSelfDamageEffect(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, user, userHP, userHPBar);
            }
            if (user.getBattle_status().equals("BRN")) {
                BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is hurt by its burn!");
                BattleEvents.addSelfDamageEffect(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, user, userHP, userHPBar);
            }
        }
        
        if (user.isLeeched()) {
            BattleEvents.addSelfDamageEffect(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, user, userHP, userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " had its HP sapped!");
            BattleEvents.addHealingEvent(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, target, targetHP, targetHPBar);
        }
        
        if (user.isHealingOverTime()) {
            BattleEvents.addHealingEvent(eventQueue, textArea, user.getCurrent_max_hp() * 1/16, user, userHP, userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " healed some of its HP!");
        }
        
        // Berry Check
        if (user.isHoldingItem()) {
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " is holding a " + user.getItem().getName());
            if (user.getItem() instanceof TriggeredByHP) {
                BattleEvents.addGenericEvent(eventQueue, textArea, "The item can be triggered by hp");
                System.out.println("HP REQUIREMENT: " + ((TriggeredByHP)user.getItem()).GetHPActivePoint() * user.getCurrent_max_hp());
                System.out.println("CURRENT HP: " + user.getCurrent_hp());
                if(((TriggeredByHP)user.getItem()).GetHPActivePoint() * user.getCurrent_max_hp() > user.getCurrent_hp()) {
                    
                    BattleEvents.addGenericEvent(eventQueue, textArea, "HP meets the requirement to use!");
                }
            }
            if (user.getItem() instanceof HealsHP) {
                BattleEvents.addGenericEvent(eventQueue, textArea, "The item will heal the pokemon");
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
            if (pokemon.isFainted()) {
                count++;
            }
        }
        if (count == trainer.getParty().size()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Calculates the value of an attack and several factors. 
     * Formula ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100 ) - From wiki
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
                user.setBattle_status("");
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
                user.setBattle_status("");
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
     * Logic for a single move event in a pokemon battle.
     * @param user pokemon conducting the move
     * @param target pokemon receiving the move (could be self, ally or enemy)
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
        // Pre move check
         
        if (canUseMove(user, userLabels, userHPBar,  eventQueue, textArea) == false) return;
        double chance;
        double result;
           
        BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " used " + userMove.getName() + "!");
        
        if (userMove instanceof HealOverTime) {
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + ((HealOverTime) ((HealOverTime)userMove)).getTurnDescription());
            user.setHealingOverTime(true);
        }
        
        if (userMove instanceof TwoTurn) {
            BattleEvents.addIconReturnEvent(eventQueue, user, userLabels[5]);
        }
        
        // Accuracy Check - self created
        chance = (user.getBattle_accuracy() * userMove.getAccuracy() * target.getBattle_evasion() );
        result = Math.random();
        if (target.isInTwoTurn()) {
            if (((TwoTurn)targetMove).getTargetable() == false) result = 5;
        }
        if (result > chance) {
            BattleEvents.addGenericEvent(eventQueue, textArea, "The move missed!");
            return;
        }
        
        if (userMove instanceof ApplyLeech) {
            target.setLeeched(true);
            BattleEvents.addGenericEvent(eventQueue, textArea, target.getName() + " was seeded!");
            return;
        }
        
        // Calculate the damage
        double typeMultiplier = TypeModifier.getMultiplier(userMove, target);

        int damage;
        if (userMove instanceof SetDamage) {
            damage = ((SetDamage)userMove).getDamage();
        } else if(userMove instanceof PhysicalAttack) {
            damage = calcDamage(user.getLevel(), user.getBattle_attack(), target.getBattle_defense(), userMove.getPower(), typeMultiplier);   
        } else if(userMove instanceof SpecialAttack){
            damage = calcDamage(user.getLevel(), user.getBattle_special_attack(), target.getBattle_special_defense(), userMove.getPower(), typeMultiplier);
        } else {
            damage = 0;
        }
        
        if (typeMultiplier == 0) {
            BattleEvents.addGenericEvent(eventQueue, textArea, "The move had no effect");
            return;
        }
        
        if (userMove instanceof OneHitKO) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    // IMPORTANT - TARGET RECIEVES THE DAMAGE HERE
                    target.setCurrent_hp(0);

                    System.out.println("BM: Updating hp display");
                    targetLabels[2].setText(Integer.toString(target.getCurrent_hp()));
                    targetHPBar.setValue(target.getCurrent_hp());
                }
            });
            /* Set faint here so its set immediatly instead of delayed due to event queue */
            target.setFainted(true);
            BattleEvents.addGenericEvent(eventQueue, textArea, "Its a One Hit KO!");
            return;
        }
        
        // Types of damage events
        if (damage != 0) {        
            if (userMove instanceof MultiStrike) {
                int timeHit = (int) ((Math.random() * 3) + 2);
                for (int i = 0; i < timeHit; i++) {
                    BattleEvents.addDamageEvent(eventQueue, textArea, user.getBattle_status(), damage, target, targetLabels[2], targetHPBar);
                }
                BattleEvents.addGenericEvent(eventQueue, textArea, "It hit " + Integer.toString(timeHit) + " time(s)!");
            } else {
                BattleEvents.addDamageEvent(eventQueue, textArea, user.getBattle_status(), damage, target, targetLabels[2], targetHPBar);
            }
            if (typeMultiplier < 1.0 && typeMultiplier > 0) {
                BattleEvents.addGenericEvent(eventQueue, textArea, "Its not very effective...");
            }
            if (typeMultiplier > 1.0) {
                BattleEvents.addGenericEvent(eventQueue, textArea, "Its super effective!");
            }
        }
        
        if(userMove instanceof Lifesteal) {
            int netHP = (int) (((Lifesteal)userMove).getLifestealRatio() * damage);
            BattleEvents.addHealingEvent(eventQueue, textArea, netHP, user, userLabels[2], userHPBar);
            BattleEvents.addGenericEvent(eventQueue, textArea, target.getName() + ((Lifesteal)userMove).getDescription());
        }
        
        if(userMove instanceof HealsHP) {
            int healAmount = (int) (((HealsHP)userMove).getHealRatio() * user.getCurrent_max_hp());
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    user.healHP(healAmount);
                    userLabels[2].setText(Integer.toString(user.getCurrent_hp()));
                    userHPBar.setValue(user.getCurrent_hp());
                    if (user.getCurrent_hp() > (user.getCurrent_max_hp() / 2)) {
                        userHPBar.setForeground(Color.yellow);
                    }
                    if (user.getCurrent_hp() > (user.getCurrent_max_hp() / 2)) {
                        userHPBar.setForeground(Color.green);
                    }
                }
            });
            BattleEvents.addGenericEvent(eventQueue, textArea, user.getName() + " healed some HP!");
        }
        
        if (userMove instanceof HasRecoil) {
            int recoilDamage = (int) (((HasRecoil)userMove).getRecoilRatio() * damage);
            BattleEvents.addDamageEvent(eventQueue, textArea, user.getBattle_status(), recoilDamage, user, userLabels[2], userHPBar);
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

        if(target.getCurrent_hp() - damage <= 0) {
            target.setFainted(true);
            return;
        }
        
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
                if (target.getBattle_status().equals("PAR")) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already paralyzed!");
                }
                if (target.getBattle_status().equals("PSN")) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already poisoned!");
                }
                if (target.getBattle_status().equals("BRN")) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already burned!");
                }
                if (target.getBattle_status().equals("SLP")) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already asleep!");
                }
                if (target.getBattle_status().equals("FRZ")) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, "The target is already frozen!");
                }
                return;
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
