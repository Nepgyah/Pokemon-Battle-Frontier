package battle.helpers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import move.Move;
import move.modifiers.*;
import pokemon.Pokemon;
import types.*;
import move.statistic_effects_enemy.*;
import move.statistic_effects_user.*;

public class battleMechanics {
    
    final static int MAX_STAT_CHANGE = 2;
    
    public static int calcDamage(int level, int attack, int defense, int power, double type_bonus)
    {
        /* Calculate damage WIP
         *  Formula ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100 ) - From wiki
         */
        int damage = (int) (((((2 * level / 5 + 2) * attack * power / defense) / 50) + 2) * type_bonus );
        return damage;
    }
    
    public static void addMoveEvent(
            ArrayList<TimerTask> eventQueue,
            JTextArea textArea,
            Pokemon user, 
            Move userMove, 
            Pokemon target, 
            Move targetMove, 
            JLabel targetHpLabel, 
            JProgressBar targetHpBar) {
        
        // Pre move check
        
        // Add event to describe the move
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                System.out.println("BM: Describing move");
                textArea.setText(user.getName() + " used " + userMove.getName() + "!");
            }
        });
        
        // Accuracy Check
        
        // Calculate the damage
        double typeMultiplier = getTypeMultiplier(userMove, target);

        int damage;
        if (userMove instanceof SetDamage)
        {
            damage = ((SetDamage)userMove).getDamage();
        }
        else if(userMove instanceof PhysicalAttack)
        {
            damage = calcDamage(user.getLevel(), user.getBattle_attack(), target.getBattle_defense(), userMove.getPower(), typeMultiplier);
        } 
        else if(userMove instanceof SpecialAttack)
        {
            damage = calcDamage(user.getLevel(), user.getBattle_special_attack(), target.getBattle_special_defense(), userMove.getPower(), typeMultiplier);
        }
        else
        {
            damage = 0;
        }
        
        // Add damage dealing event
        if (damage != 0) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                     // IMPORTANT - TARGET RECIEVES THE DAMAGE HERE
                    target.takeDamage(damage);

                    System.out.println("BM: Updating hp display");
                    targetHpLabel.setText(Integer.toString(target.getCurrent_hp()));
                    targetHpBar.setValue(target.getCurrent_hp());

                    if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 2)) {
                        targetHpBar.setForeground(Color.yellow);
                    }
                    if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 4)) {
                        targetHpBar.setForeground(Color.red);
                    }
                }
            });
        }
        // Add effect multiplier effect
        if (typeMultiplier < 1.0 && typeMultiplier > 0) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Describing damage multiplier");
                    textArea.setText("Its not very effective...");
                }
            });
        }
        if (typeMultiplier > 1.0) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Describing damage multiplier");
                    textArea.setText("Its super effective!");
                }
            });
        }
        
        // Add statistic change event
        if (userMove instanceof ApplyStatChange) {
            double statModChange = ( (ApplyStatChange) userMove).getApplyChance();
            double statModResult = Math.random();
            
            if (statModChange > statModResult) {
                // Note: two sets of modifiers (EX: userAttackPlusOne vs targetAttackPlusOne) requires two sepereate functions?
                applyStatEffectsToTarget(target, userMove, eventQueue, textArea);
                applyStatEffectsToUser(user, userMove, eventQueue, textArea);
            }
        }
        // Add status change effect
        
    }
    private static void applyStatEffectsToUser(Pokemon user, Move move, ArrayList<TimerTask> eventQueue, JTextArea textArea) {
		
        if (move instanceof UserAttackPlusOne || move instanceof UserAttackPlusTwo || move instanceof UserAttackMinusOne || move instanceof UserAttackMinusTwo)
        {
            if (user.getBattle_attack_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s attack cannot go any higher!");
                    }
                });
                return;
            }
            if (user.getBattle_attack_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s attack cannot go any lower!");
                    }
                });
                return;
            }
            if(move instanceof UserAttackPlusOne)
            {
                user.setBattle_attack_count(user.getBattle_attack_count() + 1);
                user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s attack rose!");
                    }
                });
            }
            if(move instanceof UserAttackPlusTwo)
            {                
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        user.setBattle_attack_count(user.getBattle_attack_count() + 2);
                        user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                        textArea.setText(user.getName() + "'s attack sharply!");
                    }
                });
            }
            if(move instanceof UserAttackMinusOne)
            {
                user.setBattle_attack_count(user.getBattle_attack_count() - 1);
                user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s attack fell!");
                    }
                });
            }
            if(move instanceof UserAttackMinusTwo)
            {
                user.setBattle_attack_count(user.getBattle_attack_count() - 2);
                user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s attack fell harsly!");
                    }
                });
            }
        }

        if (move instanceof UserDefensePlusOne || move instanceof UserDefensePlusTwo || move instanceof UserDefenseMinusOne || move instanceof UserDefenseMinusTwo)
        {
            if (user.getBattle_defense_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s defense cannot go any higher!");
                    }
                });
                return;
            }
            if (user.getBattle_defense_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s defense cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof UserDefensePlusOne)
            {
                user.setBattle_defense_count(user.getBattle_defense_count() + 1);
                user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s defense rose!");
                    }
                });
            }
            if (move instanceof UserDefensePlusTwo)
            {
                user.setBattle_defense_count(user.getBattle_defense_count() + 2);
                user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s defense rose sharply!");
                    }
                });
            }
            if (move instanceof UserDefenseMinusOne)
            {
                user.setBattle_defense_count(user.getBattle_defense_count() - 1);
                user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s defense fell!");
                    }
                });
            }
            if (move instanceof UserDefenseMinusTwo)
            {
                user.setBattle_defense_count(user.getBattle_defense_count() - 2);
                user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s defense fell sharply!");
                    }
                });
            }
        }

        if (move instanceof UserSpecialAttackPlusOne || move instanceof UserSpecialAttackPlusTwo || move instanceof UserSpecialAttackMinusOne || move instanceof UserSpecialAttackMinusTwo)
        {
            if (user.getBattle_special_attack_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special attack cannot go any higher!");
                    }
                });
                return;
            }
            if (user.getBattle_special_attack_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special attack cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof UserSpecialAttackPlusOne)
            {
                user.setBattle_special_attack_count(user.getBattle_special_attack_count() + 1);
                user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special attack rose!");
                    }
                });
            }
            if (move instanceof UserSpecialAttackPlusTwo)
            {
                user.setBattle_special_attack_count(user.getBattle_special_attack_count() + 2);
                user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special attack rose sharply!");
                    }
                });
            }
            if (move instanceof UserSpecialAttackMinusOne)
            {
                user.setBattle_special_attack_count(user.getBattle_special_attack_count() - 1);
                user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special attack fell!");
                    }
                });
            }
            if (move instanceof UserSpecialAttackMinusTwo)
            {
                user.setBattle_special_attack_count(user.getBattle_special_attack_count() - 2);
                user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special attack fell harsly!");
                    }
                });
            }
        }

        if (move instanceof UserSpecialDefensePlusOne || move instanceof UserSpecialDefensePlusTwo || move instanceof UserSpecialDefenseMinusOne || move instanceof UserSpecialDefenseMinusTwo)
        {
            if (user.getBattle_special_defense_count() <= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special defense cannot go any higher!");
                    }
                });                
                return;
            }
            if (user.getBattle_special_defense_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special defense cannot go any lower!");
                    }
                });  
                return;
            }
            if (move instanceof UserSpecialDefensePlusOne)
            {
                user.setBattle_special_defense_count(user.getBattle_special_defense_count() + 1);
                user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special defense rose!");
                    }
                });  
            }
            if (move instanceof UserSpecialDefensePlusTwo)
            {
                user.setBattle_special_defense_count(user.getBattle_special_defense_count() + 2);
                user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special defense rose sharply!");
                    }
                });  
            }
            if (move instanceof UserSpecialDefenseMinusOne)
            {
                user.setBattle_special_defense_count(user.getBattle_special_defense_count() - 1);
                user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special defense fell!");
                    }
                });  
            }
            if (move instanceof UserSpecialDefenseMinusTwo)
            {
                user.setBattle_special_defense_count(user.getBattle_special_defense_count() - 2);
                user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s special defense fell harsly!");
                    }
                });  
            }
        }

        if (move instanceof UserSpeedPlusOne || move instanceof UserSpeedPlusTwo || move instanceof UserSpeedMinusOne || move instanceof UserSpeedMinusTwo)
        {
            if (user.getBattle_speed_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed cannot go any higher!");
                    }
                });
                return;
            }
            if (user.getBattle_speed_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof UserSpeedPlusOne)
            {
                user.setBattle_speed_count(user.getBattle_speed_count() + 1);
                user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed rose!");
                    }
                });
            }
            if (move instanceof UserSpeedPlusTwo)
            {
                user.setBattle_speed_count(user.getBattle_speed_count() + 2);
                user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed rose sharply!");
                    }
                });
            }
            if (move instanceof UserSpeedMinusOne)
            {
                user.setBattle_speed_count(user.getBattle_speed_count() - 1);
                user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed fell");
                    }
                });
            }
            if (move instanceof UserSpeedMinusTwo)
            {
                user.setBattle_speed_count(user.getBattle_speed_count() - 2);
                user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed fell harshly!");
                    }
                });
            }
        }

        if (move instanceof UserAccuracyPlusOne || move instanceof UserAccuracyPlusTwo || move instanceof UserAccuracyMinusOne || move instanceof UserAccuracyMinusTwo )
        {
            if (user.getBattle_accuracy_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s accuracy cannot go any higher!");
                    }
                });
                return;
            }
            if (user.getBattle_accuracy_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s attack cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof UserAccuracyPlusOne)
            {
                user.setBattle_accuracy_count(user.getBattle_accuracy_count() + 1);
                user.setBattle_accuracy(user.getBattle_accuracy() + .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed rose!");
                    }
                });
            }
            if (move instanceof UserAccuracyPlusTwo)
            {
                user.setBattle_accuracy_count(user.getBattle_accuracy_count() + 2);
                user.setBattle_accuracy(user.getBattle_accuracy() + .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed rose sharply!");
                    }
                });
            }
            if (move instanceof UserAccuracyMinusOne)
            {
                user.setBattle_accuracy_count(user.getBattle_accuracy_count() - 1);
                user.setBattle_accuracy(user.getBattle_accuracy() - .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed fell!");
                    }
                });
            }
            if (move instanceof UserAccuracyMinusTwo)
            {
                user.setBattle_accuracy_count(user.getBattle_accuracy_count() - 2);
                user.setBattle_accuracy(user.getBattle_accuracy() - .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s speed fell sharply!");
                    }
                });
            }
        }

        if (move instanceof UserEvasionPlusOne || move instanceof UserEvasionPlusTwo || move instanceof UserEvasionMinusOne || move instanceof UserEvasionMinusTwo )
        {
            if (user.getBattle_evasion_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s evasion cannot go any higher!");
                    }
                });
                return;
            }
            if (user.getBattle_evasion_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s evasion cannot go any higher!");
                    }
                });
                return;
            }
            if (move instanceof UserEvasionPlusOne)
            {
                user.setBattle_evasion_count(user.getBattle_evasion_count() + 1);
                user.setBattle_evasion(user.getBattle_evasion() + .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s evasion rose!");
                    }
                });
            }
            if (move instanceof UserEvasionPlusTwo)
            {
                user.setBattle_evasion_count(user.getBattle_evasion_count() + 2);
                user.setBattle_evasion(user.getBattle_evasion() + .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s evasion rose sharply!");
                    }
                });
            }
            if (move instanceof UserEvasionMinusOne)
            {
                user.setBattle_evasion_count(user.getBattle_evasion_count() - 1);
                user.setBattle_evasion(user.getBattle_evasion() - .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s evasion fell!");
                    }
                });
            }
            if (move instanceof UserEvasionMinusTwo)
            {
                user.setBattle_evasion_count(user.getBattle_evasion_count() - 2);
                user.setBattle_evasion(user.getBattle_evasion() - .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + "'s evasion fell harshly!");
                    }
                });
            }
        }
    }
    
    private static void applyStatEffectsToTarget(Pokemon target, Move move, ArrayList<TimerTask> eventQueue, JTextArea textArea) {
        if (move instanceof TargetAttackPlusOne || move instanceof TargetAttackPlusTwo || move instanceof TargetAttackMinusOne || move instanceof TargetAttackMinusTwo) {
            if (target.getBattle_attack_count() >= MAX_STAT_CHANGE) {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s attack cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_attack_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s attack cannot go any lower!");
                    }
                });
                return;
            }
            if(move instanceof TargetAttackPlusOne)
            {
                target.setBattle_attack_count(target.getBattle_attack_count() + 1);
                target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s attack rose!");
                    }
                });
            }
            if(move instanceof TargetAttackPlusTwo)
            {
                target.setBattle_attack_count(target.getBattle_attack_count() + 2);
                target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s attack rose sharply!");
                    }
                });
            }
            if(move instanceof TargetAttackMinusOne)
            {
                target.setBattle_attack_count(target.getBattle_attack_count() - 1);
                target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s attack fell!");
                    }
                });
            }
            if(move instanceof TargetAttackMinusTwo)
            {
                target.setBattle_attack_count(target.getBattle_attack_count() - 2);
                target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s attack fell harshly!");
                    }
                });
            }
        }

        if (move instanceof TargetDefensePlusOne || move instanceof TargetDefensePlusTwo || move instanceof TargetDefenseMinusOne || move instanceof TargetDefenseMinusTwo) {
            if (target.getBattle_defense_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s defense cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_defense_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s defense cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof TargetDefensePlusOne)
            {
                target.setBattle_defense_count(target.getBattle_defense_count() + 1);
                target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s defense rose!");
                    }
                });
            }
            if (move instanceof TargetDefensePlusTwo)
            {
                target.setBattle_defense_count(target.getBattle_defense_count() + 2);
                target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s defense rose sharply!");
                    }
                });
            }
            if (move instanceof TargetDefenseMinusOne)
            {
                target.setBattle_defense_count(target.getBattle_defense_count() - 1);
                target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s defense fell!");
                    }
                });
            }
            if (move instanceof TargetDefenseMinusTwo)
            {
                target.setBattle_defense_count(target.getBattle_defense_count() - 2);
                target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s defense fell harshly!");
                    }
                });
            }
        }

        if (move instanceof TargetSpecialAttackPlusOne || move instanceof TargetSpecialAttackPlusTwo || move instanceof TargetSpecialAttackMinusOne || move instanceof TargetSpecialAttackMinusTwo) {
            if (target.getBattle_special_attack_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special attack cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_special_attack_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special attack cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof TargetSpecialAttackPlusOne)
            {
                target.setBattle_special_attack_count(target.getBattle_special_attack_count() + 1);
                target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special attack rose!");
                    }
                });
            }
            if (move instanceof TargetSpecialAttackPlusTwo)
            {
                target.setBattle_special_attack_count(target.getBattle_special_attack_count() + 2);
                target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special rose sharply!");
                    }
                });
            }
            if (move instanceof TargetSpecialAttackMinusOne)
            {
                target.setBattle_special_attack_count(target.getBattle_special_attack_count() - 1);
                target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special attack fell!");
                    }
                });
            }
            if (move instanceof TargetSpecialAttackMinusTwo)
            {
                target.setBattle_special_attack_count(target.getBattle_special_attack_count() - 2);
                target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special attack fell harshly!");
                    }
                });
            }
        }

        if (move instanceof TargetSpecialDefensePlusOne || move instanceof TargetSpecialDefensePlusTwo || move instanceof TargetSpecialDefenseMinusOne || move instanceof TargetSpecialDefenseMinusTwo)
        {
            if (target.getBattle_special_defense_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special defense cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_special_defense_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special defense cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof TargetSpecialDefensePlusOne)
            {
                target.setBattle_special_defense_count(target.getBattle_special_defense_count() + 1);
                target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special defense rose!");
                    }
                });
            }
            if (move instanceof TargetSpecialDefensePlusTwo)
            {
                target.setBattle_special_defense_count(target.getBattle_special_defense_count() + 2);
                target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special defense rose sharply!");
                    }
                });
            }
            if (move instanceof TargetSpecialDefenseMinusOne)
            {
                target.setBattle_special_defense_count(target.getBattle_special_defense_count() - 1);
                target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special defense fell!");
                    }
                });
            }
            if (move instanceof TargetSpecialDefenseMinusTwo)
            {
                target.setBattle_special_defense_count(target.getBattle_special_defense_count() - 2);
                target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s special defense fell harshly!");
                    }
                });
            }
        }

        if (move instanceof TargetSpeedPlusOne || move instanceof TargetSpeedPlusTwo || move instanceof TargetSpeedMinusOne || move instanceof TargetSpeedMinusTwo)
        {
            if (target.getBattle_speed_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s speed cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_speed_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s speed cannot go any lower!");
                    }
                });
                return;
            }

            if (move instanceof TargetSpeedPlusOne)
            {
                target.setBattle_speed_count(target.getBattle_speed_count() + 1);
                target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s speed rose!");
                    }
                });
            }
            if (move instanceof TargetSpeedPlusTwo)
            {
                target.setBattle_speed_count(target.getBattle_speed_count() + 2);
                target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s speed rose sharply!");
                    }
                });
            }
            if (move instanceof TargetSpeedMinusOne)
            {
                target.setBattle_speed_count(target.getBattle_speed_count() - 1);
                target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s speed fell!");
                    }
                });
            }
            if (move instanceof TargetSpeedMinusTwo)
            {
                target.setBattle_speed_count(target.getBattle_speed_count() - 2);
                target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s speed fell harshly!");
                    }
                });
            }
        }

        if (move instanceof TargetAccuracyPlusOne || move instanceof TargetAccuracyPlusTwo || move instanceof TargetAccuracyMinusOne || move instanceof TargetAccuracyMinusTwo )
        {
            if (target.getBattle_accuracy_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s accuracy cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_accuracy_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s accuracy cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof TargetAccuracyPlusOne)
            {
                target.setBattle_accuracy_count(target.getBattle_accuracy_count() + 1);
                target.setBattle_accuracy(target.getBattle_accuracy() + .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s accuracy rose!");
                    }
                });
            }
            if (move instanceof TargetAccuracyPlusTwo)
            {
                target.setBattle_accuracy_count(target.getBattle_accuracy_count() + 2);
                target.setBattle_accuracy(target.getBattle_accuracy() + .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s accuracy rose sharply!");
                    }
                });
            }
            if (move instanceof TargetAccuracyMinusOne)
            {
                target.setBattle_accuracy_count(target.getBattle_accuracy_count() - 1);
                target.setBattle_accuracy(target.getBattle_accuracy() - .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s accuracy fell!");
                    }
                });
            }
            if (move instanceof TargetAccuracyMinusTwo)
            {
                target.setBattle_accuracy_count(target.getBattle_accuracy_count() - 2);
                target.setBattle_accuracy(target.getBattle_accuracy() - .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s accuracy fell harshly!");
                    }
                });
            }
        }

        if (move instanceof TargetEvasionPlusOne || move instanceof TargetEvasionPlusTwo || move instanceof TargetEvasionMinusOne || move instanceof TargetEvasionMinusTwo )
        {
            // Check min/max change been reached
            if (target.getBattle_evasion_count() >= MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s evasion cannot go any higher!");
                    }
                });
                return;
            }
            if (target.getBattle_evasion_count() <= -MAX_STAT_CHANGE)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s evasion cannot go any lower!");
                    }
                });
                return;
            }
            if (move instanceof TargetEvasionPlusOne)
            {
                target.setBattle_evasion_count(target.getBattle_evasion_count() + 1);
                target.setBattle_evasion(target.getBattle_evasion() + .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s evasion rose!");
                    }
                });
            }
            if (move instanceof TargetEvasionPlusTwo)
            {
                target.setBattle_evasion_count(target.getBattle_evasion_count() + 2);
                target.setBattle_evasion(target.getBattle_evasion() + .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s evasion rose sharply!");
                    }
                });
            }
            if (move instanceof TargetEvasionMinusOne)
            {
                target.setBattle_evasion_count(target.getBattle_evasion_count() - 1);
                target.setBattle_evasion(target.getBattle_evasion() - .1);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s evasion fell!");
                    }
                });
            }
            if (move instanceof TargetEvasionMinusTwo)
            {
                target.setBattle_evasion_count(target.getBattle_evasion_count() - 2);
                target.setBattle_evasion(target.getBattle_evasion() - .2);
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(target.getName() + "'s evasion fell harshly!");
                    }
                });
            }
        }
    }
    private static double getTypeMultiplier(Move move, Pokemon target) {
        double type_bonus = 1.0;
        if (move instanceof Normal)
        {
                if(target instanceof Rock || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Ghost) type_bonus *= 0;
        }
        if (move instanceof Fire)
        {
                if(target instanceof Fire || target instanceof Water || target instanceof Rock || target instanceof Dragon) type_bonus *= .5;
                if(target instanceof Grass || target instanceof Ice || target instanceof Bug || target instanceof Steel) 	type_bonus *= 2.0;
        }
        if (move instanceof Water)
        {
                if(target instanceof Water || target instanceof Grass || target instanceof Dragon) type_bonus *= .5;
                if(target instanceof Fire || target instanceof Ground || target instanceof Rock) type_bonus *= 2.0;
        }
        if (move instanceof Electric)
        {
                if(target instanceof Electric || target instanceof Grass || target instanceof Dragon) type_bonus *= .5;
                if(target instanceof Water || target instanceof Flying) type_bonus *= 2.0;
                if(target instanceof Ground) type_bonus *= 0;
        }
        if (move instanceof Grass)
        {
                if(target instanceof Fire|| target instanceof Grass || target instanceof Poison || target instanceof Flying
                                || target instanceof Bug || target instanceof Dragon || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Water || target instanceof Ground || target instanceof Rock) type_bonus *= 2.0;
        }
        if (move instanceof Ice)
        {
                if(target instanceof Fire|| target instanceof Water || target instanceof Ice || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Grass || target instanceof Ground || target instanceof Flying || target instanceof Dragon) type_bonus *= 2.0;
        }
        if (move instanceof Fighting)
        {
                if(target instanceof Poison || target instanceof Flying || target instanceof Psychic || target instanceof Bug ) type_bonus *= .5;
                if(target instanceof Normal || target instanceof Ice || target instanceof Rock || target instanceof Dark || target instanceof Steel) type_bonus *= 2.0;
                if(target instanceof Ghost) type_bonus *= 0;
        }
        if (move instanceof Poison)
        {
                if(target instanceof Poison || target instanceof Ground || target instanceof Rock || target instanceof Ghost) type_bonus *= .5;
                if(target instanceof Grass) type_bonus *= 2.0;
                if(target instanceof Steel) type_bonus *= 0;
        }
        if (move instanceof Ground)
        {
                if(target instanceof Grass || target instanceof Bug) type_bonus *= .5;
                if(target instanceof Fire || target instanceof Electric || target instanceof Poison || target instanceof Rock || target instanceof Steel)  type_bonus *= 2.0;
                if(target instanceof Flying) type_bonus *= 0;
        }
        if (move instanceof Flying)
        {
                if(target instanceof Electric || target instanceof Rock) type_bonus *= .5;
                if(target instanceof Grass || target instanceof Fighting || target instanceof Bug) type_bonus *= 2.0;

        }
        if (move instanceof Psychic)
        {
                if(target instanceof Bug || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Fighting || target instanceof Poison) type_bonus *= 2.0;
                if(target instanceof Dark) type_bonus *= 0;
        }
        if (move instanceof Bug)
        {
                if(target instanceof Fire || target instanceof Fighting || target instanceof Poison || target instanceof Flying 
                                || target instanceof Ghost || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Psychic || target instanceof Grass || target instanceof Dark) type_bonus *= 2.0;
        }
        if (move instanceof Rock)
        {
                if(target instanceof Fighting || target instanceof Ground || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Fire || target instanceof Ice || target instanceof Flying || target instanceof Bug) type_bonus *= 2.0;
        }
        if (move instanceof Ghost)
        {
                if(target instanceof Dark || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Psychic || target instanceof Ghost) type_bonus *= 2.0;
                if(target instanceof Normal) type_bonus *= 0;
        }
        if (move instanceof Dragon)
        {
                if(target instanceof Steel) type_bonus *= .5;
                if(target instanceof Dragon) type_bonus *= 2.0;
        }
        if (move instanceof Dark)
        {
                if(target instanceof Fighting || target instanceof Dark || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Ghost || target instanceof Psychic) type_bonus *= 2.0;

        }
        if (move instanceof Steel)
        {
                if(target instanceof Fire || target instanceof Water || target instanceof Electric || target instanceof Steel) type_bonus *= .5;
                if(target instanceof Rock || target instanceof Ice) type_bonus *= 2.0;
        }
        return type_bonus;
    } 
}
