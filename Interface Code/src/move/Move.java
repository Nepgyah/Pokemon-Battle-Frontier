package move;

import java.io.Serializable;

import move.modifiers.PhysicalAttack;
import move.modifiers.SpecialAttack;
import move.modifiers.StatusMove;
import types.*;

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
	
        /**
         * Public constructor for the move.
         * @param tm_no individual number for the move. Works similar to pokedex number
         * @param name name of the move
         * @param max_pp maximum power points the move has
         * @param power the power or strength of a move
         * @param accuracy how often the move is able to land
         */
	public Move(int tm_no, String name, int max_pp, int power, double accuracy) {
            super();
            this.tm_no = tm_no;
            this.name = name;
            this.current_pp = max_pp;
            this.max_pp = max_pp;
            this.power = power;
            this.accuracy = accuracy;
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
            displayTypes();
            System.out.print("\t" + this.current_pp + " / " + this.max_pp + "\t");
            System.out.print(this.name + " ");
            System.out.println();
	}
	
        /**
         * Console displays the type of the move.
         */
	private void displayTypes() {
            if(this instanceof Bug) System.out.print("Bug ");
            if(this instanceof Dark) System.out.print("Dark ");
            if(this instanceof Dragon) System.out.print("Dragon ");
            if(this instanceof Electric) System.out.print("Electric ");
            if(this instanceof Fighting) System.out.print("Fighting ");
            if(this instanceof Fire) System.out.print("Fire ");
            if(this instanceof Flying) System.out.print("Flying ");
            if(this instanceof Ghost) System.out.print("Ghost ");
            if(this instanceof Grass) System.out.print("Grass ");
            if(this instanceof Ground) System.out.print("Ground ");
            if(this instanceof Ice) System.out.print("Ice ");
            if(this instanceof Normal) System.out.print("Normal ");
            if(this instanceof Poison) System.out.print("Poison ");
            if(this instanceof Psychic) System.out.print("Psychic ");
            if(this instanceof Rock) System.out.print("Rock ");
            if(this instanceof Steel) System.out.print("Steel ");
            if(this instanceof Water) System.out.print("Water ");
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

	public String getType() {
		return type;
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
	
}

