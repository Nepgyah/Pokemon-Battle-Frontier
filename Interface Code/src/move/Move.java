package move;

import java.awt.Color;
import java.io.Serializable;

import move.modifiers.PhysicalAttack;
import move.modifiers.SpecialAttack;
import move.modifiers.StatusMove;
import types.*;
import utilities.PokeColors;

/**
* Public abstract class that represents the moves a pokemon will be able to use.
* Moves are both usable in battle as well as contests with different purposes and effects.
*/
public abstract class Move implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int tm_no;
	protected String name;
	protected String type;
	protected int current_pp;
	protected int max_pp;
	protected int power;
	protected double accuracy;
        protected String description;
        
        /**
         * Public constructor for the move.
         * @param tm_no individual number for the move. Works similar to pokedex number
         * @param name name of the move
         * @param max_pp maximum power points the move has
         * @param power the power or strength of a move
         * @param accuracy how often the move is able to land
         */
	public Move(int tm_no, String name, int max_pp, int power, double accuracy, String description) {
            super();
            this.tm_no = tm_no;
            this.name = name;
            this.current_pp = max_pp;
            this.max_pp = max_pp;
            this.power = power;
            this.accuracy = accuracy;
            this.description = description;
	}

        /**
         * Creates a copy of the move to be used by a pokemon.
         * @return copy of the move for the pokemon
         */
	public abstract Move copy();
	
        /**
         * Console prints the basic information of a move
         */
	public void displayMoveInformationBasic() {
            System.out.print("\t" + this.current_pp + " / " + this.max_pp + "\t");
            System.out.println(this.name + " ");
	}
	
        /**
         * Console displays the tm number and name of the move.
         */
	public void displayMovedexInformation() {
            System.out.println(this.getTm_no() + "\t" + this.getName());
	}
	
        /**
         * Console displays the details of a move.
         */
	public void displayMoveInfoDetailed() {	
            if(this instanceof PhysicalAttack) System.out.print("'Attack' ");
            if(this instanceof SpecialAttack) System.out.print("'Sp Atk' ");
            if(this instanceof StatusMove) System.out.print("'Status' ");
            System.out.print("\t" + this.current_pp + " / " + this.max_pp + "\t");
            System.out.print(this.name + " ");
            System.out.println();
	}
	
        /**
         * Console displays the type of the move.
         */
	public String getType() {
            if(this instanceof Bug) return ("Bug");
            if(this instanceof Dark) return("Dark");
            if(this instanceof Dragon) return("Dragon");
            if(this instanceof Electric) return("Electric");
            if(this instanceof Fighting) return("Fighting");
            if(this instanceof Fire) return("Fire");
            if(this instanceof Flying) return("Flying");
            if(this instanceof Ghost) return("Ghost");
            if(this instanceof Grass) return("Grass");
            if(this instanceof Ground) return("Ground");
            if(this instanceof Ice) return("Ice");
            if(this instanceof Normal) return("Normal");
            if(this instanceof Poison) return("Poison");
            if(this instanceof Psychic) return("Psychic");
            if(this instanceof Rock) return("Rock");
            if(this instanceof Steel) return("Steel");
            if(this instanceof Water) return("Water");
            return "";
	}
        
        public Color getColor() {
            if(this instanceof Bug) return PokeColors.bug;
            if(this instanceof Dark) return PokeColors.dark;
            if(this instanceof Dragon) return PokeColors.dragon;
            if(this instanceof Electric) return PokeColors.electric;
            if(this instanceof Fighting) return PokeColors.fighting;
            if(this instanceof Fire) return PokeColors.fire;
            if(this instanceof Flying) return PokeColors.flying;
            if(this instanceof Ghost) return PokeColors.ghost;
            if(this instanceof Grass) return PokeColors.grass;
            if(this instanceof Ground) return PokeColors.ground;
            if(this instanceof Ice) return PokeColors.ice;
            if(this instanceof Normal) return PokeColors.normal;
            if(this instanceof Poison) return PokeColors.poison;
            if(this instanceof Psychic) return PokeColors.psychic;
            if(this instanceof Rock) return PokeColors.rock;
            if(this instanceof Steel) return PokeColors.steel;
            if(this instanceof Water) return PokeColors.water;
            return Color.black;
        }
        public String getCategory() {
            if(this instanceof PhysicalAttack) return "Physical";
            if(this instanceof SpecialAttack) return "Special";
            if(this instanceof StatusMove) return "Status";
            return "N/A";
        }
	
	public int getTm_no() {
		return tm_no;
	}

	public void setTm_no(int tm_no) {
		this.tm_no = tm_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setType(String type) {
		this.type = type;
	}

	public int getCurrent_pp() {
		return current_pp;
	}

	public void setCurrent_pp(int current_pp) {
		this.current_pp = current_pp;
	}

	public int getMax_pp() {
		return max_pp;
	}

	public void setMax_pp(int max_pp) {
		this.max_pp = max_pp;
	}

	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	
        public String getDescription() {
            return this.description;
        }
}

