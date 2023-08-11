package battle.mechanics;

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
import battle.mechanics.*;
import move.status_effect.*;

public class battleMechanics {
    
    final static int MAX_STAT_CHANGE = 2;
    
    public static void displayBattleStatsToConsole(Pokemon pokemon)
    {
        System.out.println("Turn report for: " + pokemon.getName());
        System.out.println("Attack: " + pokemon.getBattle_attack() + " (" + pokemon.getCurrent_attack()+ ")");
        System.out.println("Defense: " + pokemon.getBattle_defense() + " (" + pokemon.getCurrent_defense()+ ")");
        System.out.println("Special Attack: " + pokemon.getBattle_special_attack() + " (" + pokemon.getCurrent_special_attack()+ ")");
        System.out.println("Special Defense: " + pokemon.getBattle_special_defense() + " (" + pokemon.getCurrent_special_defense()+ ")");
        System.out.println("Speed: " + pokemon.getBattle_speed() + " (" + pokemon.getCurrent_speed()+ ")");
        System.out.println("Current Accuracy Modifier: " + pokemon.getBattle_accuracy());
        System.out.println("Curretn Evasion Modifier: " + pokemon.getBattle_evasion());
    }
    public static int calcDamage(int level, int attack, int defense, int power, double type_bonus)
    {
        /* Calculate damage WIP
         *  Formula ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100 ) - From wiki
         */
        int damage = (int) (((((2 * level / 5 + 2) * attack * power / defense) / 50) + 2) * type_bonus );
        return damage;
    }
    
    public static boolean canUseMove(Pokemon user, JLabel [] userLabels, JProgressBar userHPBar, ArrayList<TimerTask> eventQueue, JTextArea textArea) 
//    public static boolean canUseMove(Pokemon user, ArrayList<TimerTask> eventQueue, JTextArea textArea, JLabel userHpLabel, JLabel userStatusLabel, JProgressBar userHpBar)
    {
        if (user.isFlinched() == true)
        {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    textArea.setText(user.getName() + " flinched and couldn't move!");
                    user.setFlinched(false);
                }
            });
            return false;
        }

        if (user.isConfused() == true)
        {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    textArea.setText(user.getName() + " is confused!");
                }
            });
            int chance_to_hurt = (int) (Math.random() * 2);
            if (chance_to_hurt != 1) return true;
            else
            {
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

        if (user.getBattle_status() == "PAR")
        {
            int chance_to_be_paralyzed = (int) (Math.random() * 4);
            if (chance_to_be_paralyzed != 1) return true;
            else
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + " is paralyzed! It can't move!");
                    }
                });
                return false;
            }
        }

        if (user.getBattle_status() == "FRZ")
        {
            int chance_to_thaw = (int) (Math.random() * 5);
            if (chance_to_thaw == 1)
            {
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
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText(user.getName() + " is frozen! It can't move!");
                    }
                });
                return false;
            }
        }

        if (user.getBattle_status() == "SLP")
        {
            user.setSleep_turns(user.getSleep_turns() - 1);
            if (user.getSleep_turns() != 0)
            {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(user.getName() + " sleep turns left: " + user.getSleep_turns());
                        textArea.setText(user.getName() + " is asleep!");
                    }
                });
                return false;
            }
            else
            {
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
    
    public static void useMove(
            Pokemon user, Pokemon target,
            Move userMove, Move targetMove,
            JLabel [] userLabels, JLabel [] targetLabels,
            JProgressBar userHPBar, JProgressBar targetHPBar,
            ArrayList<TimerTask> eventQueue,
            JTextArea textArea
            )
    {
        if (canUseMove(user, userLabels, userHPBar,  eventQueue, textArea) == false) return;
        double chance;
        double result;
        // Pre move check
        
        
       // Bring back icon on 2nd half two turn
        if (userMove instanceof TwoTurn) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Bringin back Icon");
                    userLabels[5].setIcon(user.getIcon());
                }
            });
        }
        
        // Add event to describe the move
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                System.out.println("BM: Describing move");
                textArea.setText(user.getName() + " used " + userMove.getName() + "!");
            }
        });
        
        // Accuracy Check
        chance = (user.getBattle_accuracy() * userMove.getAccuracy() * target.getBattle_evasion() );
        result = Math.random();
        if (target.isInTwoTurn())
        {
            if (((TwoTurn)targetMove).getTargetable() == false) result = 5;
        }

        if (result > chance) 
        {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    textArea.setText("The move missed!");
                }
            });
            return;
        }
        
        // Calculate the damage
        double typeMultiplier = typeModifier.getMultiplier(userMove, target);

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
        
        if (typeMultiplier == 0) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Describing damage multiplier");
                    textArea.setText("The move had no effect");
                }
            });
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
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    // IMPORTANT - TARGET RECIEVES THE DAMAGE HERE
                    System.out.println("BM: Updating hp display");
                    textArea.setText("Its a One Hit KO!");
                }
            });
            return;
        }
        
        // Add damage dealing event
        if (damage != 0) {
            
            if (userMove instanceof MultiStrike) {
                int timeHit = (int) ((Math.random() * 3) + 2);
                for (int i = 0; i < timeHit; i++) {
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            // IMPORTANT - TARGET RECIEVES THE DAMAGE HERE
                            if (user.getBattle_status() == "BRN")
                            {
                                target.takeDamage(damage / 2);
                            } else {
                                target.takeDamage(damage);
                            }

                            System.out.println("BM: Updating hp display");
                            targetLabels[2].setText(Integer.toString(target.getCurrent_hp()));
                            targetHPBar.setValue(target.getCurrent_hp());

                            if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 2)) {
                                targetHPBar.setForeground(Color.yellow);
                            }
                            if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 4)) {
                                targetHPBar.setForeground(Color.red);
                            }
                        }
                    });
                }
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("BM: Describing times hit");
                        textArea.setText("It hit " + Integer.toString(timeHit) + " time(s)!");
                    }
                });
            } else {
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        // IMPORTANT - TARGET RECIEVES THE DAMAGE HERE
                        if (user.getBattle_status() == "BRN")
                        {
                            target.takeDamage(damage / 2);
                        } else {
                            target.takeDamage(damage);
                        }

                        System.out.println("BM: Updating hp display");
                        targetLabels[2].setText(Integer.toString(target.getCurrent_hp()));
                        targetHPBar.setValue(target.getCurrent_hp());

                        if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 2)) {
                            targetHPBar.setForeground(Color.yellow);
                        }
                        if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 4)) {
                            targetHPBar.setForeground(Color.red);
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
        }
        
        if (userMove instanceof ApplyFlinch)
        {
            int chance_to_be_flinch = (int) (Math.random() * 10);
            if ( chance_to_be_flinch <= 3 )
            {
                target.setFlinched(true);
            }
        }
        
        if(userMove instanceof Lifesteal)
        {
            int netHP = (int) (((Lifesteal)userMove).getLifestealRatio() * damage);
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    user.healHP(netHP);

                    System.out.println("BM: Updating hp display for pure heal");
                    userLabels[2].setText(Integer.toString(user.getCurrent_hp()));
                    userHPBar.setValue(user.getCurrent_hp());

                    if (user.getCurrent_hp() > (user.getCurrent_max_hp() / 2)) {
                        userHPBar.setForeground(Color.YELLOW);
                    }
                    if (user.getCurrent_hp() > (user.getCurrent_max_hp() / 2)) {
                        userHPBar.setForeground(Color.GREEN);
                    }
                }
            });
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Describing lifesteal event");
                    textArea.setText(target.getName() + ((Lifesteal)userMove).getDescription());
                }
            });
        }
        
        if(userMove instanceof HealsHP)
        {
            int healAmount = (int) (((HealsHP)userMove).getHealRatio() * user.getCurrent_max_hp());
            System.out.println("Heal amount - " + Integer.toString(healAmount));
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    user.healHP(healAmount);

                    System.out.println("BM: Updating hp display for pure heal");
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
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Describing healing event");
                    textArea.setText(user.getName() + " healed some HP!");
                }
            });
        }
        
        // Add recoil event
        if (userMove instanceof HasRecoil) {
            int recoilDamage = (int) (((HasRecoil)userMove).getRecoilRatio() * damage);
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    user.takeDamage(recoilDamage);

                    System.out.println("BM: Updating hp display for recoil");
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
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("BM: Describing recoil");
                    textArea.setText(user.getName() + " took damage in recoil!");
                }
            });
        }
        
        // Add statistic change event
        if (userMove instanceof ApplyStatChange) {
            double statModChange = ( (ApplyStatChange) userMove).getApplyChance();
            double statModResult = Math.random();
            
            if (statModChange > statModResult) {
                // Note: two sets of modifiers (EX: userAttackPlusOne vs targetAttackPlusOne) requires two sepereate functions?
                statisticChanges.applyToTarget(target, userMove, eventQueue, textArea);
                statisticChanges.applyToUser(user, userMove, eventQueue, textArea);
            }
        }
        
//        if(target.getCurrent_hp() <= 0)
//        {
//            target.setFainted();
//            return;
//        }
        
        // Add status change effect
        if (userMove instanceof ApplyParalyze || userMove instanceof ApplyPoison || 
                userMove instanceof ApplyBurn || userMove instanceof ApplySleep || userMove instanceof ApplyFrozen) {
            if (!(target.getBattle_status() == null))
            {
                if (target.getBattle_status().equals("PAR")) {
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("BM: Status already on");
                            textArea.setText("The target is already paralyzed");
                        }
                    });
                }
                if (target.getBattle_status().equals("PSN")) {
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("BM: Status already on");
                            textArea.setText("The target is already poisoned");
                        }
                    });
                }
                if (target.getBattle_status().equals("BRN")) {
                        eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("BM: Status already on");
                            textArea.setText("The target is already burned");
                        }
                    });
                }
                if (target.getBattle_status().equals("SLP")) {
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("BM: Status already on");
                            textArea.setText("The target is already asleep");
                        }
                    });
                }
                if (target.getBattle_status().equals("FRZ")) {
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("BM: Status already on");
                            textArea.setText("The target is already frozen");
                        }
                    });
                }
                return;
            } else {
                result = Math.random();
                if (userMove instanceof ApplyParalyze)
                {
                    chance = ((ApplyParalyze) userMove).getParalyzeChance();
                    if(result < chance)
                    {
                        target.setBattle_status("PAR");
                        eventQueue.add(new TimerTask() {
                            @Override
                            public void run() {
                                targetLabels[4].setText(target.getBattle_status());
                                targetLabels[4].setBackground(Color.yellow);
                                textArea.setText("Enemy " + target.getName() + " was paralyzed! ");
                            }
                        });
                    }
                }
                if (userMove instanceof ApplyPoison)
                {
                    chance = ((ApplyPoison) userMove).getPoisonChance();
                    if (result < chance)
                    {
                        target.setBattle_status("PSN");
                        eventQueue.add(new TimerTask() {
                            @Override
                            public void run() {
                                targetLabels[4].setText(target.getBattle_status());
                                targetLabels[4].setBackground(Color.MAGENTA);
                                textArea.setText("Enemy " + target.getName() + " was poisoned! ");
                            }
                        });
                    }
                }
                if (userMove instanceof ApplyBurn)
                {
                    chance = ((ApplyBurn) userMove).getBurnChance();
                    if (result < chance)
                    {
                        target.setBattle_status("BRN");
                        eventQueue.add(new TimerTask() {
                            @Override
                            public void run() {
                                targetLabels[4].setText(target.getBattle_status());
                                targetLabels[4].setBackground(Color.RED);
                                textArea.setText("Enemy " + target.getName() + " was burned! ");
                            }
                        });
                    }
                }
                if (userMove instanceof ApplySleep)
                {
                    chance = ((ApplySleep) userMove).getSleepChance();
                    if (result < chance)
                    {
                        target.setBattle_status("SLP");
                        target.setSleep_turns((int) (Math.random() * 5) + 1);
                        eventQueue.add(new TimerTask() {
                            @Override
                            public void run() {
                                targetLabels[4].setText(target.getBattle_status());
                                targetLabels[4].setBackground(Color.GRAY);
                                textArea.setText("Enemy " + target.getName() + " was put to sleep! ");
                            }
                        });
                    }
                }
                if (userMove instanceof ApplyFrozen)
                {
                    chance = ((ApplyFrozen) userMove).getFrozenChance();
                    if (result < chance)
                    {
                        target.setBattle_status("FRZ");
                        eventQueue.add(new TimerTask() {
                            @Override
                            public void run() {
                                targetLabels[4].setText(target.getBattle_status());
                                targetLabels[4].setBackground(Color.CYAN);
                                textArea.setText("Enemy " + target.getName() + " was frozen solid! ");
                            }
                        });
                    }
                }
            }
        }
    }
}
