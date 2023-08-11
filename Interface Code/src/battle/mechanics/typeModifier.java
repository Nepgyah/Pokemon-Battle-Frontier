package battle.mechanics;

import move.Move;
import pokemon.Pokemon;
import types.*;

public class typeModifier {
    
    public static double getMultiplier(Move move, Pokemon target) {
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
