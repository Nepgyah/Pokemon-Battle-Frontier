package battle.helpers;

import static battle.old.BattleMechanics.calcDamage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import move.Move;
import move.modifiers.PhysicalAttack;
import move.modifiers.SetDamage;
import move.modifiers.SpecialAttack;
import pokemon.Pokemon;
import types.Bug;
import types.Dark;
import types.Dragon;
import types.Electric;
import types.Fighting;
import types.Fire;
import types.Flying;
import types.Ghost;
import types.Grass;
import types.Ground;
import types.Ice;
import types.Normal;
import types.Poison;
import types.Psychic;
import types.Rock;
import types.Steel;
import types.Water;

public class battleMechanics {
    
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
        // Add event to describe the move
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                System.out.println("BM: Describing move");
                textArea.setText(user.getName() + " used " + userMove.getName() + "!");
            }
        });
        
        // Add event to update hp
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
        
        // Add event to describe the damage multiplier
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
