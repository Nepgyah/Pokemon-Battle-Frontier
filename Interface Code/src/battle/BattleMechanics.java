package battle;


import move.Move;
import move.modifiers.*;
import move.statistic_effects_enemy.*;
import move.statistic_effects_user.*;
import move.status_effect.*;
import pokemon.Pokemon;
import types.*;

/**
 * Collection of methods needed to run any type of pokemon battle
 * 
 */
public class BattleMechanics {

    final static int MAX_STAT_CHANGE = 2;

    public static int calcDamage(int level, int attack, int defense, int power, double type_bonus)
    {
            /* Calculate damage WIP
             *  Formula ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100 ) - From wiki
             */
            int damage = (int) (((((2 * level / 5 + 2) * attack * power / defense) / 50) + 2) * type_bonus );
            return damage;
    }

    public static void postMoveStatusEffects(Pokemon pokemon)
    {
            // Update condition of left_pokemon (Poison / Burn Damage)
            if (pokemon.getBattle_status() == "PSN")
                    {
                            System.out.println(pokemon.getName() + " is hurt by its poison!");
                            pokemon.takeDamage(pokemon.getCurrent_max_hp() * 1/16);
                    }
            if (pokemon.getBattle_status() == "BRN")
                    {
                            System.out.println(pokemon.getName() + " is hurt by its burn!");
                            pokemon.takeDamage(pokemon.getCurrent_max_hp() * 1/16);
                    }
    }

    public static boolean canUseMove(Pokemon user)
    {
            if (user.isFlinched() == true)
            {
                    System.out.println(user.getName() + " flinched and couldn't move!");
                    user.setFlinched(false);
                    return false;
            }

            if (user.isConfused() == true)
            {
                    int chance_to_hurt = (int) (Math.random() * 2);
                    if (chance_to_hurt != 1) return true;
                    else
                    {
                            System.out.println(user.getName() + " hurt itself in confusion");
                            int damage = (int) (((((2 * user.getLevel() / 5 + 2) * user.getBattle_attack() * 40 / user.getBattle_defense()) / 50) + 2));
                            user.takeDamage(damage);
                            return false;
                    }
            }

            if (user.getBattle_status() == "PAR")
            {
                    int chance_to_be_paralyzed = (int) (Math.random() * 4);
                    if (chance_to_be_paralyzed != 1) return true;
                    else
                    {
                            System.out.println(user.getName() + " is paralyzed! It can't move!");
                            return false;
                    }
            }

            if (user.getBattle_status() == "FRZ")
            {
                    int chance_to_thaw = (int) (Math.random() * 5);
                    if (chance_to_thaw == 1)
                    {
                            System.out.println(user.getName() + " thawed out!");
                            return true;
                    }
                    else
                            System.out.println(user.getName() + " is frozen! It can't move!");
                            return false;
            }

            if (user.getBattle_status() == "SLP")
            {
                    user.setSleep_turns(user.getSleep_turns() - 1);
                    if (user.getSleep_turns() != 0)
                    {
                            System.out.println(user.getName() + " is asleep!");
                            return false;
                    }
                    else
                    {
                            System.out.println(user.getName() + " woke up!");
                            return true;
                    }
            }
            return true;
    }

    /**
     * Takes the pokemon through the process of attacking its target
     * 
     * @param user			The pokemon using the move
     * @param target		The pokemon receiving the attack (If the move is targetSelf, the target is 
     * 						simply itself)
     * @param userMove		The move the pokemon will use
     * @param targetMove	The targets current move, used in cases for two turn moves
     * 						(Example: A pokemon that uses dig is untargetable in the first half unless its targeted
     * 						by Earthquake)
     */
    public static void useMove(Pokemon user, Pokemon target, Move userMove, Move targetMove) 
    {
//            System.out.println("useMove method print");
            /*
             * TODO: Mechanics to add
             * 		- Leeched
             * 		- Wrapped
             */

            if (canUseMove(user) == false) return;
//            System.out.println(user.getNickname() + " used " + userMove.getName() + "!");

            userMove.setCurrent_pp(userMove.getCurrent_pp() - 1);

            /* Check for move success, i.e the move hit or missed ( this is not the offical formula for accuracy )
            * chance = battle_accuracy(0.0 - 1.0) * move accuracy *(0.0 - 1.0) * target evasion(0.0 - 1.0)
            * result = random double between 0.0 and 1.0
            * if result falls under the chance, the move hits
            */
            double chance = (user.getBattle_accuracy() * userMove.getAccuracy() * target.getBattle_evasion() );
            double result = Math.random();

            if (target.isInTwoTurn())
            {
                if (((TwoTurn)targetMove).getTargetable() == false) result = 5;
            }

            if (result > chance) 
            {
                    System.out.println("The move missed!");
                    return;
            }

            double type_multiplier = getTypeMultiplier(userMove, target);
            if(type_multiplier == 0)
            {
                    System.out.println("The move had no effect.");
                    return;
            }
            if(type_multiplier < 1.0 && type_multiplier > 0)
            {
                    System.out.println("Its not very effective...");
            }
            if(type_multiplier > 1.0)
            {
                    System.out.println("Its super effective!");
            }

            if (userMove instanceof OneHitKO)
            {
                    System.out.println("Its a One Hit KO!");
                    target.setCurrent_hp(0);
                    return;
            }

            int damage;
            if (userMove instanceof SetDamage)
            {
                damage = ((SetDamage)userMove).getDamage();
            }
            else if(userMove instanceof PhysicalAttack)
            {
                    damage = calcDamage(user.getLevel(), user.getBattle_attack(), target.getBattle_defense(), userMove.getPower(), type_multiplier);
            } 
            else if(userMove instanceof SpecialAttack)
            {
                    damage = calcDamage(user.getLevel(), user.getBattle_special_attack(), target.getBattle_special_defense(), userMove.getPower(), type_multiplier);
            }
            else
            {
                    damage = 0;
            }

            if(userMove instanceof MultiStrike)
            {
                    int timeHit = (int) ((Math.random() * 3) + 2);
                    System.out.println("It hit " + timeHit + " time(s)");
                    damage *= timeHit;
            }

            if (user.getBattle_status() == "BRN")
            {
                    damage /= 2;
            }

            target.takeDamage(damage);

            if (userMove instanceof HasRecoil)
            {
                    int recoilDamage = (int) (((HasRecoil)userMove).getRecoilRatio() * damage);
                    user.takeDamage(recoilDamage);
                    System.out.println(user.getName() + " took damage in recoil!");
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
                    user.healHP(netHP);
                    System.out.println(target.getName() + ((Lifesteal)userMove).getDescription());
            }

            if(userMove instanceof HealsHP)
            {
                    int healAmount = (int) (((HealsHP)userMove).getHealRatio() * user.getCurrent_max_hp());
                    user.healHP(healAmount);
                    System.out.println(user.getName() + " healed some HP!");
            }

            if(userMove instanceof Recharge)
            {
                    user.setRecharging(true);
            }

            if (userMove instanceof ApplyStatChange)
            {
                    double statModChance = ( (ApplyStatChange) userMove).getApplyChance();
                    double statModResult = Math.random();

                    if(statModChance > statModResult)
                    {
                            applyStatEffectsToTarget(target, userMove);
                            applyStatEffectsToUser(user, userMove);
                    }
            }

            if(target.getCurrent_hp() <= 0)
            {
                    target.setFainted();
                    return;
            }

            if (userMove instanceof ApplyParalyze || userMove instanceof ApplyPoison || userMove instanceof ApplyBurn
                            || userMove instanceof ApplySleep || userMove instanceof ApplyFrozen)
            {
                    if (!(target.getBattle_status() == null))
                    {
                            String status = "The target is already ";
                            if (target.getBattle_status().equals("PAR")) status += "paralyzed!";
                            if (target.getBattle_status().equals("PSN")) status += "poisoned!";
                            if (target.getBattle_status().equals("BRN")) status += "burned!";
                            if (target.getBattle_status().equals("SLP")) status += "asleep!";
                            if (target.getBattle_status().equals("FRZ")) status += "frozen!";
                            System.out.println(status);
                            return;
                    }
                    else
                    {
                            result = Math.random();
                            if (userMove instanceof ApplyParalyze)
                            {
                                    chance = ((ApplyParalyze) userMove).getParalyzeChance();
                                    if(result < chance)
                                    {
                                            target.setBattle_status("PAR");
                                            System.out.println("Enemy " + target.getName() + " was paralyzed! ");
                                    }
                            }
                            if (userMove instanceof ApplyPoison)
                            {
                                    chance = ((ApplyPoison) userMove).getPoisonChance();
                                    if (result < chance)
                                    {
                                            target.setBattle_status("PSN");
                                            System.out.println("Enemy " + target.getName() + " was poisoned! ");
                                    }
                            }
                            if (userMove instanceof ApplyBurn)
                            {
                                    chance = ((ApplyBurn) userMove).getBurnChance();
                                    if (result < chance)
                                    {
                                            target.setBattle_status("BRN");
                                            System.out.println("Enemy " + target.getName() + " was burned! ");
                                    }
                            }
                            if (userMove instanceof ApplySleep)
                            {
                                    chance = ((ApplySleep) userMove).getSleepChance();
                                    if (result < chance)
                                    {
                                            target.setBattle_status("SLP");
                                            System.out.println("Enemy " + target.getName() + " was put to sleep! ");
                                    }
                            }
                            if (userMove instanceof ApplyFrozen)
                            {
                                    chance = ((ApplyFrozen) userMove).getFrozenChance();
                                    if (result < chance)
                                    {
                                            target.setBattle_status("FRZ");
                                            System.out.println("Enemy " + target.getName() + " was frozen solid! ");
                                    }
                            }
                    }
            }
    }

    public static void displayBattleStatsToConsole(Pokemon pokemon)
    {
            System.out.println("Turn report for: " + pokemon.getName());
            System.out.println("Statistics = in-battle stats (default stats)");
            System.out.println("Attack: " + pokemon.getBattle_attack() + " (" + pokemon.getCurrent_attack()+ ")");
            System.out.println("Defense: " + pokemon.getBattle_defense() + " (" + pokemon.getCurrent_defense()+ ")");
            System.out.println("Special Attack: " + pokemon.getBattle_special_attack() + " (" + pokemon.getCurrent_special_attack()+ ")");
            System.out.println("Special Defense: " + pokemon.getBattle_special_defense() + " (" + pokemon.getCurrent_special_defense()+ ")");
            System.out.println("Speed: " + pokemon.getBattle_speed() + " (" + pokemon.getCurrent_speed()+ ")");
            System.out.println("Current Accuracy Modifier: " + pokemon.getBattle_accuracy());
            System.out.println("Curretn Evasion Modifier: " + pokemon.getBattle_evasion());
    }
    /**
     * Applys any statistic effects the move brings to the user
     * 
     * @param user		The pokemon using the move and receiving its effects
     * @param move		The move that has the statistic effects (Contains the interfaces to determine
     * 					what statistics will change)
     */
    private static void applyStatEffectsToUser(Pokemon user, Move move) {

            if (move instanceof UserAttackPlusOne || move instanceof UserAttackPlusTwo || move instanceof UserAttackMinusOne || move instanceof UserAttackMinusTwo)
            {
                    if (user.getBattle_attack_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s attack cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_attack_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s attack cannot go any lower!");
                            return;
                    }
                    if(move instanceof UserAttackPlusOne)
                    {
                            user.setBattle_attack_count(user.getBattle_attack_count() + 1);
                            user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s attack rose!");
                    }
                    if(move instanceof UserAttackPlusTwo)
                    {
                            user.setBattle_attack_count(user.getBattle_attack_count() + 2);
                            user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s attack rose sharply");
                    }
                    if(move instanceof UserAttackMinusOne)
                    {
                            user.setBattle_attack_count(user.getBattle_attack_count() - 1);
                            user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s attack fell");
                    }
                    if(move instanceof UserAttackMinusTwo)
                    {
                            user.setBattle_attack_count(user.getBattle_attack_count() - 2);
                            user.setBattle_attack(((user.getBase_attack() + user.getIv_values().get(1)) * 2 * (user.getLevel() + user.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s attack rose fell harshly");
                    }
            }

            if (move instanceof UserDefensePlusOne || move instanceof UserDefensePlusTwo || move instanceof UserDefenseMinusOne || move instanceof UserDefenseMinusTwo)
            {
                    if (user.getBattle_defense_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s defense cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_defense_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s defense cannot go any lower!");
                            return;
                    }
                    if (move instanceof UserDefensePlusOne)
                    {
                            user.setBattle_defense_count(user.getBattle_defense_count() + 1);
                            user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s defense rose!");
                    }
                    if (move instanceof UserDefensePlusTwo)
                    {
                            user.setBattle_defense_count(user.getBattle_defense_count() + 2);
                            user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s defense rose sharply!");
                    }
                    if (move instanceof UserDefenseMinusOne)
                    {
                            user.setBattle_defense_count(user.getBattle_defense_count() - 1);
                            user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s defense fell!");
                    }
                    if (move instanceof UserDefenseMinusTwo)
                    {
                            user.setBattle_defense_count(user.getBattle_defense_count() - 2);
                            user.setBattle_defense(((user.getBase_defense() + user.getIv_values().get(2)) * 2 * (user.getLevel() + user.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s defense fell sharply!");
                    }
            }

            if (move instanceof UserSpecialAttackPlusOne || move instanceof UserSpecialAttackPlusTwo || move instanceof UserSpecialAttackMinusOne || move instanceof UserSpecialAttackMinusTwo)
            {
                    if (user.getBattle_special_attack_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s special attack cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_special_attack_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s special attack cannot go any lower!");
                            return;
                    }
                    if (move instanceof UserSpecialAttackPlusOne)
                    {
                            user.setBattle_special_attack_count(user.getBattle_special_attack_count() + 1);
                            user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special attack rose!");
                    }
                    if (move instanceof UserSpecialAttackPlusTwo)
                    {
                            user.setBattle_special_attack_count(user.getBattle_special_attack_count() + 2);
                            user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special attack rose sharply!");
                    }
                    if (move instanceof UserSpecialAttackMinusOne)
                    {
                            user.setBattle_special_attack_count(user.getBattle_special_attack_count() - 1);
                            user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special attack fell!");
                    }
                    if (move instanceof UserSpecialAttackMinusTwo)
                    {
                            user.setBattle_special_attack_count(user.getBattle_special_attack_count() - 2);
                            user.setBattle_special_attack(((user.getBase_special_attack() + user.getIv_values().get(3)) * 2 * (user.getLevel() + user.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special attack fell sharply!");
                    }
            }

            if (move instanceof UserSpecialDefensePlusOne || move instanceof UserSpecialDefensePlusTwo || move instanceof UserSpecialDefenseMinusOne || move instanceof UserSpecialDefenseMinusTwo)
            {
                    if (user.getBattle_special_defense_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s special defense cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_special_defense_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s special defense cannot go any lower!");
                            return;
                    }
                    if (move instanceof UserSpecialDefensePlusOne)
                    {
                            user.setBattle_special_defense_count(user.getBattle_special_defense_count() + 1);
                            user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special defense rose!");
                    }
                    if (move instanceof UserSpecialDefensePlusTwo)
                    {
                            user.setBattle_special_defense_count(user.getBattle_special_defense_count() + 2);
                            user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special defense rose sharply!");
                    }
                    if (move instanceof UserSpecialDefenseMinusOne)
                    {
                            user.setBattle_special_defense_count(user.getBattle_special_defense_count() - 1);
                            user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special defense fell!");
                    }
                    if (move instanceof UserSpecialDefenseMinusTwo)
                    {
                            user.setBattle_special_defense_count(user.getBattle_special_defense_count() - 2);
                            user.setBattle_special_defense(((user.getBase_special_defense() + user.getIv_values().get(4)) * 2 * (user.getLevel() + user.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s special defense fell harsly!");
                    }
            }

            if (move instanceof UserSpeedPlusOne || move instanceof UserSpeedPlusTwo || move instanceof UserSpeedMinusOne || move instanceof UserSpeedMinusTwo)
            {
                    if (user.getBattle_speed_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s speed cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_speed_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s speed defense cannot go any lower!");
                            return;
                    }
                    if (move instanceof UserSpeedPlusOne)
                    {
                            user.setBattle_speed_count(user.getBattle_speed_count() + 1);
                            user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s speed rose!");
                    }
                    if (move instanceof UserSpeedPlusTwo)
                    {
                            user.setBattle_speed_count(user.getBattle_speed_count() + 2);
                            user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s speed rose sharply!");
                    }
                    if (move instanceof UserSpeedMinusOne)
                    {
                            user.setBattle_speed_count(user.getBattle_speed_count() - 1);
                            user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s speed fell!");
                    }
                    if (move instanceof UserSpeedMinusTwo)
                    {
                            user.setBattle_speed_count(user.getBattle_speed_count() - 2);
                            user.setBattle_speed(((user.getBase_speed() + user.getIv_values().get(5)) * 2 * (user.getLevel() + user.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(user.getName() + "'s speed fell harsly!");
                    }
            }

            if (move instanceof UserAccuracyPlusOne || move instanceof UserAccuracyPlusTwo || move instanceof UserAccuracyMinusOne || move instanceof UserAccuracyMinusTwo )
            {
                    if (user.getBattle_accuracy_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s accuracy cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_accuracy_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s speed accuracy cannot go any lower!");
                            return;
                    }
                    if (move instanceof UserAccuracyPlusOne)
                    {
                            user.setBattle_accuracy_count(user.getBattle_accuracy_count() + 1);
                            user.setBattle_accuracy(user.getBattle_accuracy() + .1);
                            System.out.println(user.getName() + "'s speed rose!");
                    }
                    if (move instanceof UserAccuracyPlusTwo)
                    {
                            user.setBattle_accuracy_count(user.getBattle_accuracy_count() + 2);
                            user.setBattle_accuracy(user.getBattle_accuracy() + .2);
                            System.out.println(user.getName() + "'s speed rose sharply!");
                    }
                    if (move instanceof UserAccuracyMinusOne)
                    {
                            user.setBattle_accuracy_count(user.getBattle_accuracy_count() - 1);
                            user.setBattle_accuracy(user.getBattle_accuracy() - .1);
                            System.out.println(user.getName() + "'s speed fell!");
                    }
                    if (move instanceof UserAccuracyMinusTwo)
                    {
                            user.setBattle_accuracy_count(user.getBattle_accuracy_count() - 2);
                            user.setBattle_accuracy(user.getBattle_accuracy() - .2);
                            System.out.println(user.getName() + "'s speed fell harsly!");
                    }
            }

            if (move instanceof UserEvasionPlusOne || move instanceof UserEvasionPlusTwo || move instanceof UserEvasionMinusOne || move instanceof UserEvasionMinusTwo )
            {
                    if (user.getBattle_evasion_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s evasion cannot go any higher!");
                            return;
                    }
                    if (user.getBattle_evasion_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(user.getName() + "'s evasion cannot go any lower!");
                            return;
                    }
                    if (move instanceof UserEvasionPlusOne)
                    {
                            user.setBattle_evasion_count(user.getBattle_evasion_count() + 1);
                            user.setBattle_evasion(user.getBattle_evasion() + .1);
                            System.out.println(user.getName() + "'s evasion rose!");
                    }
                    if (move instanceof UserEvasionPlusTwo)
                    {
                            user.setBattle_evasion_count(user.getBattle_evasion_count() + 2);
                            user.setBattle_evasion(user.getBattle_evasion() + .2);
                            System.out.println(user.getName() + "'s evasion rose sharply!");
                    }
                    if (move instanceof UserEvasionMinusOne)
                    {
                            user.setBattle_evasion_count(user.getBattle_evasion_count() - 1);
                            user.setBattle_evasion(user.getBattle_evasion() - .1);
                            System.out.println(user.getName() + "'s evasion fell!");
                    }
                    if (move instanceof UserEvasionMinusTwo)
                    {
                            user.setBattle_evasion_count(user.getBattle_evasion_count() - 2);
                            user.setBattle_evasion(user.getBattle_evasion() - .2);
                            System.out.println(user.getName() + "'s evasion fell harsly!");
                    }
            }
    }

    private static void applyStatEffectsToTarget(Pokemon target, Move move) 
    {

            if (move instanceof TargetAttackPlusOne || move instanceof TargetAttackPlusTwo || move instanceof TargetAttackMinusOne || move instanceof TargetAttackMinusTwo)
            {
                    if (target.getBattle_attack_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s attack cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_attack_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s attack cannot go any lower!");
                            return;
                    }
                    if(move instanceof TargetAttackPlusOne)
                    {
                            target.setBattle_attack_count(target.getBattle_attack_count() + 1);
                            target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s attack rose!");
                    }
                    if(move instanceof TargetAttackPlusTwo)
                    {
                            target.setBattle_attack_count(target.getBattle_attack_count() + 2);
                            target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s attack rose sharply");
                    }
                    if(move instanceof TargetAttackMinusOne)
                    {
                            target.setBattle_attack_count(target.getBattle_attack_count() - 1);
                            target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s attack fell");
                    }
                    if(move instanceof TargetAttackMinusTwo)
                    {
                            target.setBattle_attack_count(target.getBattle_attack_count() - 2);
                            target.setBattle_attack(((target.getBase_attack() + target.getIv_values().get(1)) * 2 * (target.getLevel() + target.getBattle_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s attack rose fell harshly");
                    }
            }

            if (move instanceof TargetDefensePlusOne || move instanceof TargetDefensePlusTwo || move instanceof TargetDefenseMinusOne || move instanceof TargetDefenseMinusTwo)
            {
                    if (target.getBattle_defense_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s defense cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_defense_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s defense cannot go any lower!");
                            return;
                    }
                    if (move instanceof TargetDefensePlusOne)
                    {
                            target.setBattle_defense_count(target.getBattle_defense_count() + 1);
                            target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s defense rose!");
                    }
                    if (move instanceof TargetDefensePlusTwo)
                    {
                            target.setBattle_defense_count(target.getBattle_defense_count() + 2);
                            target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s defense rose sharply!");
                    }
                    if (move instanceof TargetDefenseMinusOne)
                    {
                            target.setBattle_defense_count(target.getBattle_defense_count() - 1);
                            target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s defense fell!");
                    }
                    if (move instanceof TargetDefenseMinusTwo)
                    {
                            target.setBattle_defense_count(target.getBattle_defense_count() - 2);
                            target.setBattle_defense(((target.getBase_defense() + target.getIv_values().get(2)) * 2 * (target.getLevel() + target.getBattle_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s defense fell sharply!");
                    }
            }

            if (move instanceof TargetSpecialAttackPlusOne || move instanceof TargetSpecialAttackPlusTwo || move instanceof TargetSpecialAttackMinusOne || move instanceof TargetSpecialAttackMinusTwo)
            {
                    if (target.getBattle_special_attack_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s special attack cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_special_attack_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s special attack cannot go any lower!");
                            return;
                    }
                    if (move instanceof TargetSpecialAttackPlusOne)
                    {
                            target.setBattle_special_attack_count(target.getBattle_special_attack_count() + 1);
                            target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special attack rose!");
                    }
                    if (move instanceof TargetSpecialAttackPlusTwo)
                    {
                            target.setBattle_special_attack_count(target.getBattle_special_attack_count() + 2);
                            target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special attack rose sharply!");
                    }
                    if (move instanceof TargetSpecialAttackMinusOne)
                    {
                            target.setBattle_special_attack_count(target.getBattle_special_attack_count() - 1);
                            target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special attack fell!");
                    }
                    if (move instanceof TargetSpecialAttackMinusTwo)
                    {
                            target.setBattle_special_attack_count(target.getBattle_special_attack_count() - 2);
                            target.setBattle_special_attack(((target.getBase_special_attack() + target.getIv_values().get(3)) * 2 * (target.getLevel() + target.getBattle_special_attack_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special attack fell sharply!");
                    }
            }

            if (move instanceof TargetSpecialDefensePlusOne || move instanceof TargetSpecialDefensePlusTwo || move instanceof TargetSpecialDefenseMinusOne || move instanceof TargetSpecialDefenseMinusTwo)
            {
                    if (target.getBattle_special_defense_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s special defense cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_special_defense_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s special defense cannot go any lower!");
                            return;
                    }
                    if (move instanceof TargetSpecialDefensePlusOne)
                    {
                            target.setBattle_special_defense_count(target.getBattle_special_defense_count() + 1);
                            target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special defense rose!");
                    }
                    if (move instanceof TargetSpecialDefensePlusTwo)
                    {
                            target.setBattle_special_defense_count(target.getBattle_special_defense_count() + 2);
                            target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special defense rose sharply!");
                    }
                    if (move instanceof TargetSpecialDefenseMinusOne)
                    {
                            target.setBattle_special_defense_count(target.getBattle_special_defense_count() - 1);
                            target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special defense fell!");
                    }
                    if (move instanceof TargetSpecialDefenseMinusTwo)
                    {
                            target.setBattle_special_defense_count(target.getBattle_special_defense_count() - 2);
                            target.setBattle_special_defense(((target.getBase_special_defense() + target.getIv_values().get(4)) * 2 * (target.getLevel() + target.getBattle_special_defense_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s special defense fell harsly!");
                    }
            }

            if (move instanceof TargetSpeedPlusOne || move instanceof TargetSpeedPlusTwo || move instanceof TargetSpeedMinusOne || move instanceof TargetSpeedMinusTwo)
            {
                    if (target.getBattle_speed_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s speed cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_speed_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s speed defense cannot go any lower!");
                            return;
                    }

                    if (move instanceof TargetSpeedPlusOne)
                    {
                            target.setBattle_speed_count(target.getBattle_speed_count() + 1);
                            target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s speed rose!");
                    }
                    if (move instanceof TargetSpeedPlusTwo)
                    {
                            target.setBattle_speed_count(target.getBattle_speed_count() + 2);
                            target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s speed rose sharply!");
                    }
                    if (move instanceof TargetSpeedMinusOne)
                    {
                            target.setBattle_speed_count(target.getBattle_speed_count() - 1);
                            target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s speed fell!");
                    }
                    if (move instanceof TargetSpeedMinusTwo)
                    {
                            target.setBattle_speed_count(target.getBattle_speed_count() - 2);
                            target.setBattle_speed(((target.getBase_speed() + target.getIv_values().get(5)) * 2 * (target.getLevel() + target.getBattle_speed_count()) / 100 ) + 5);
                            System.out.println(target.getName() + "'s speed fell harsly!");
                    }
            }

            if (move instanceof TargetAccuracyPlusOne || move instanceof TargetAccuracyPlusTwo || move instanceof TargetAccuracyMinusOne || move instanceof TargetAccuracyMinusTwo )
            {
                    // Check min/max change been reached
                    if (target.getBattle_accuracy_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s accuracy cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_accuracy_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s speed accuracy cannot go any lower!");
                            return;
                    }
                    if (move instanceof TargetAccuracyPlusOne)
                    {
                            target.setBattle_accuracy_count(target.getBattle_accuracy_count() + 1);
                            target.setBattle_accuracy(target.getBattle_accuracy() + .1);
                            System.out.println(target.getName() + "'s speed rose!");
                    }
                    if (move instanceof TargetAccuracyPlusTwo)
                    {
                            target.setBattle_accuracy_count(target.getBattle_accuracy_count() + 2);
                            target.setBattle_accuracy(target.getBattle_accuracy() + .2);
                            System.out.println(target.getName() + "'s speed rose sharply!");
                    }
                    if (move instanceof TargetAccuracyMinusOne)
                    {
                            target.setBattle_accuracy_count(target.getBattle_accuracy_count() - 1);
                            target.setBattle_accuracy(target.getBattle_accuracy() - .1);
                            System.out.println(target.getName() + "'s speed fell!");
                    }
                    if (move instanceof TargetAccuracyMinusTwo)
                    {
                            target.setBattle_accuracy_count(target.getBattle_accuracy_count() - 2);
                            target.setBattle_accuracy(target.getBattle_accuracy() - .2);
                            System.out.println(target.getName() + "'s speed fell harsly!");
                    }
            }

            if (move instanceof TargetEvasionPlusOne || move instanceof TargetEvasionPlusTwo || move instanceof TargetEvasionMinusOne || move instanceof TargetEvasionMinusTwo )
            {
                    // Check min/max change been reached
                    if (target.getBattle_evasion_count() >= MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s evasion cannot go any higher!");
                            return;
                    }
                    if (target.getBattle_evasion_count() <= -MAX_STAT_CHANGE)
                    {
                            System.out.println(target.getName() + "'s evasion cannot go any lower!");
                            return;
                    }
                    if (move instanceof TargetEvasionPlusOne)
                    {
                            target.setBattle_evasion_count(target.getBattle_evasion_count() + 1);
                            target.setBattle_evasion(target.getBattle_evasion() + .1);
                            System.out.println(target.getName() + "'s evasion rose!");
                    }
                    if (move instanceof TargetEvasionPlusTwo)
                    {
                            target.setBattle_evasion_count(target.getBattle_evasion_count() + 2);
                            target.setBattle_evasion(target.getBattle_evasion() + .2);
                            System.out.println(target.getName() + "'s evasion rose sharply!");
                    }
                    if (move instanceof TargetEvasionMinusOne)
                    {
                            target.setBattle_evasion_count(target.getBattle_evasion_count() - 1);
                            target.setBattle_evasion(target.getBattle_evasion() - .1);
                            System.out.println(target.getName() + "'s evasion fell!");
                    }
                    if (move instanceof TargetEvasionMinusTwo)
                    {
                            target.setBattle_evasion_count(target.getBattle_evasion_count() - 2);
                            target.setBattle_evasion(target.getBattle_evasion() - .2);
                            System.out.println(target.getName() + "'s evasion fell harsly!");
                    }
            }
    }

    /**
     * Determines the type bonus multiplier that will be used 
     * when calculating the damage done to the move's target
     * @param move		The move and its type (in the form of an interface)
     * @param target	The pokemon receiving the move and its type (in the form of an interface)
     * @return			Returns a double serving as the damage multiplier
     */	
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
