package pokemon;

import java.io.Serializable;
import java.util.ArrayList;

import move.Move;
import types.*;

public abstract class Pokemon implements Serializable{
	/**
	 *  Public abstract class that represets all aspects about a pokemon
	 *  This class will serve as the template for introducing new Pokemon (child relationship)
	 *  and their specific stats and moveset that makes them original even amongst the same species
	 *  
	 */
	private static final long serialVersionUID = 1L;
	// General Information
	protected int pokedex_number;
	protected String name;
	protected String nickname;
	
	protected Move[] moveset = new Move[4];
	protected ArrayList<Integer> learnable_moves = new ArrayList<>();
	
	// Base statistics
	protected int level;
	protected int base_max_hp;
	protected int base_attack;
	protected int base_defense;
	protected int base_special_attack;
	protected int base_special_defense;
	protected int base_speed;
	
	// IV Values -- randomized at pokemon creation
	protected ArrayList<Integer> iv_values = new ArrayList<>();
	
	// Stats adjusted to the current level of the pokemon
	protected int current_hp;
	protected int current_max_hp;
	protected int current_attack;
	protected int current_defense;
	protected int current_special_attack;
	protected int current_special_defense;
	protected int current_speed;
	
	/* Battle based statistics
	 * Since stats are changed during battle these stats will be copied from the current values before a battle
	 */
	protected int battle_attack;
	protected int battle_defense;
	protected int battle_special_attack;
	protected int battle_special_defense;
	protected int battle_speed;
	protected double battle_accuracy;
	protected double battle_evasion;
	
	/* Counter for statistic changes
	 * Max level change for statistic is set to 6
	 * Ex: Attack can not be lowered any further
	 */
	protected int battle_attack_count;
	protected int battle_defense_count;
	protected int battle_special_attack_count;
	protected int battle_special_defense_count;
	protected int battle_speed_count;
	protected int battle_accuracy_count;
	protected int battle_evasion_count;
	
	// Battle related attributes
	protected boolean fainted = false;
	protected int confused_turns = 0;				
	protected boolean confused;						
	protected boolean flinched;						
	protected boolean recharging;					
	protected boolean inTwoTurn;					
	protected int sleep_turns = 0;		
	protected boolean isLeeched;
	protected String battle_status = null;			// Used to display status in battle 
	
	/**
	 * Default constructor for the pokemon class
	 */
	public Pokemon()
	{
		super();
	}
	
	/**
	 * Copies a pokemon for the original trainer
	 * @return		Returns a original copied version of the pokemon
	 */
	public abstract Pokemon copy();
	
	/**
	 * Creates custom values when copying pokemon,
	 * it is this function that differentiates pokemon from
	 * the same species
	 */
	public void setIV_Values()
	{
		// Randomize the IV values (Between 0 and 31)
		for (int i = 0; i < 6; i++)
		{
			this.iv_values.add((int) (Math.random() * 31));
		}
	}
	
	/* =============================
	 * MOVE RELATED MEMBER FUNCTIONS
	 * =============================
	 */
	
	/**
	 * Assigns the pokemon 4 random moves from its learnable move list
	 * 
	 * @param movedex		List of all moves in the game
	 */
	public void assignRandomMoves(ArrayList<Move> movedex)
	{
		int num;
		
		/* Pick 4 random ints positions in the pokemons's learnable_movs list
		 * The integer positions map to the corresponding tm_no when using the movedex
		 * to grab the moves
		 */
		ArrayList<Integer> random_moves = new ArrayList<>();
		for (int i = 0; i < 4; i++)
		{
			// Random a number NOT already selected
			num = (int)(Math.random() * learnable_moves.size());
			while(random_moves.contains(learnable_moves.get(num)))
			{
				num = (int)(Math.random() * learnable_moves.size());
			}
			random_moves.add(learnable_moves.get(num));
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(Move move : movedex)
			{
				if(move.getTm_no() == random_moves.get(i))
				{
					learnMove(move.copy(), i);
				}
			}
		}
	}
	
	/**
	 * Fills in / Replaces slot with desired move
	 * 
	 * @param move		The move the pokemon will learn
	 * @param pos		The moveslot position it will be in
	 */
	public void learnMove(Move move, int pos)
	{
		moveset[pos] = move;
	}
	
	
	/* =============================
	 * DISPLAY RELATED MEMBER FUNCTIONS
	 * =============================
	 */
	
	/**
	 * Displays the current moves a pokemon learned and
	 * can use in battle
	 * 
	 */
	public void displayCurrentMoves()
	{
		System.out.println();
		for (int i = 0; i < 4; i++)
		{
			if (this.moveset[i] == null)
			{
				System.out.println((i+1) + ". -- ");
			}
			else
			{
				System.out.println((i + 1) + ". " + this.moveset[i].getCurrent_pp() + " / " + this.moveset[i].getMax_pp() + "\t" + this.moveset[i].getName());
			}
		}
	}
	
	public void displayLearnableMoves(ArrayList<Move> movedex)
	{
		int i = 0;
		System.out.println("Moves " + this.nickname + " can learn");
		for(int move_tm_no : learnable_moves)
		{
			for(Move move : movedex)
			{
				if(move.getTm_no() == move_tm_no)
				{
					i++;
					System.out.print(i + ". ");
					System.out.println(move.getName());
				}
			}
		}
	}
	
	private void displayTypes()
	{
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
	
	public void displayInformation()
	{
		System.out.print("\nDex No: " + this.pokedex_number + "\t" + this.name);
		if(this.nickname == null) System.out.println();
		else System.out.println(" '" + this.nickname + "' ");
		System.out.println("LVL: " + this.level +  "\t\tHP: " + this.current_hp + " / " + this.current_max_hp);

		displayTypes();
		
		System.out.println();
		System.out.println("Attack: " + this.current_attack);
		System.out.println("Defense: " + this.current_defense);
		System.out.println("Special Attack: " + this.current_special_attack);
		System.out.println("Special Defense: " + this.current_special_defense);
		System.out.println("Speed: " + this.current_speed);

		for(int i = 0; i < 4; i++)
		{
			if(moveset[i] == null) System.out.println("\t----");
			else moveset[i].displayMoveInformationBasic();
		}
	}
	
	/**
	 * Displays information of a Pokemon relevant to seeing it in a the pokedex
	 */
	public void displayPokedexInformation()
	{
		System.out.print(this.pokedex_number + "\t" + this.name + " \t");
		displayTypes();
		System.out.println();
	}
	
	/* =====================
	 * MISC MEMBER FUNCTIONS
	 * =====================
	 */
	
	public void setLevel(int level)
	{
		this.level = level;
		// update the current stats of the pokemon
		/* Note: using GEN 1 and GEN 2 formula
		 * Reference: https://bulbapedia.bulbagarden.net/wiki/Stat#Generations_I_and_II 
		 * Parts missing from later games
		 * 				- nature
		 * 				- ev values (bonus from raising a pokmeon through battle)
		 */
		current_hp = current_max_hp =  ( ( (base_max_hp + this.iv_values.get(0)) * 2 * this.level) / 100 ) + this.level + 10;
		current_attack = ( ( (base_attack + this.iv_values.get(1)) * 2 * this.level) / 100 ) + 5;
		current_defense = ( ( (base_defense + this.iv_values.get(2)) * 2 * this.level) / 100 ) + 5;
		current_special_attack = ( ( (base_special_attack + this.iv_values.get(3)) * 2 * this.level) / 100 ) + 5;
		current_special_defense = ( ( (base_special_defense + this.iv_values.get(4)) * 2 * this.level) / 100 ) + 5;
		current_speed = ( ( (base_speed + this.iv_values.get(5)) * 2 * this.level) / 100 ) + 5;;
		
		// update the battle stats
		resetBattleStats();
	}
	
	/**
	 * Reset the pokemon soft stats after a battle
	 */
	public void resetBattleStats()
	{
		this.battle_attack = current_attack;
		this.battle_special_attack = current_special_attack;
		this.battle_defense = current_defense;
		this.battle_special_defense = current_special_defense;
		this.battle_speed = current_speed;
		
		this.confused = false;
		this.flinched = false;
		
		this.battle_attack_count = 0;
		this.battle_defense_count = 0;
		this.battle_special_attack_count = 0;
		this.battle_special_defense_count = 0;
		this.battle_speed_count = 0;
		
		this.battle_accuracy = 1.0;
		this.battle_evasion = 1.0;
		this.battle_accuracy_count = 0;
		this.battle_evasion_count = 0;
	}
	
	public void giveNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public void takeDamage(int amount)
	{
		this.setCurrent_hp(this.getCurrent_hp() - amount);
	}

	public void healHP(int amount)
	{
		if (this.getCurrent_hp() + amount >= this.current_max_hp)
		{
			this.setCurrent_hp(current_max_hp);
		}
		else
		{
			this.setCurrent_hp(this.getCurrent_hp() + amount);
		}
	}
	
	public void setFainted()
	{
		this.fainted = true;
		this.current_hp = 0;
	}
	
	// Setters and Getters
	public int getPokedex_number() {
		return pokedex_number;
	}

	public void setPokedex_number(int pokedex_number) {
		this.pokedex_number = pokedex_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Move[] getMoveset() {
		return moveset;
	}

	public void setMoveset(Move[] moveset) {
		this.moveset = moveset;
	}

	public ArrayList<Integer> getLearnable_moves() {
		return learnable_moves;
	}

	public void setLearnable_moves(ArrayList<Integer> learnable_moves) {
		this.learnable_moves = learnable_moves;
	}

	public int getBase_max_hp() {
		return base_max_hp;
	}

	public void setBase_max_hp(int base_max_hp) {
		this.base_max_hp = base_max_hp;
	}

	public int getBase_attack() {
		return base_attack;
	}

	public void setBase_attack(int base_attack) {
		this.base_attack = base_attack;
	}

	public int getBase_defense() {
		return base_defense;
	}

	public void setBase_defense(int base_defense) {
		this.base_defense = base_defense;
	}

	public int getBase_special_attack() {
		return base_special_attack;
	}

	public void setBase_special_attack(int base_special_attack) {
		this.base_special_attack = base_special_attack;
	}

	public int getBase_special_defense() {
		return base_special_defense;
	}

	public void setBase_special_defense(int base_special_defense) {
		this.base_special_defense = base_special_defense;
	}

	public int getBase_speed() {
		return base_speed;
	}

	public void setBase_speed(int base_speed) {
		this.base_speed = base_speed;
	}

	public ArrayList<Integer> getIv_values() {
		return iv_values;
	}

	public void setIv_values(ArrayList<Integer> iv_values) {
		this.iv_values = iv_values;
	}

	public int getCurrent_hp() {
		return current_hp;
	}

	public void setCurrent_hp(int current_hp) {
		this.current_hp = current_hp;
	}

	public int getCurrent_max_hp() {
		return current_max_hp;
	}

	public void setCurrent_max_hp(int current_max_hp) {
		this.current_max_hp = current_max_hp;
	}

	public int getCurrent_attack() {
		return current_attack;
	}

	public void setCurrent_attack(int current_attack) {
		this.current_attack = current_attack;
	}

	public int getCurrent_defense() {
		return current_defense;
	}

	public void setCurrent_defense(int current_defense) {
		this.current_defense = current_defense;
	}

	public int getCurrent_special_attack() {
		return current_special_attack;
	}

	public void setCurrent_special_attack(int current_special_attack) {
		this.current_special_attack = current_special_attack;
	}

	public int getCurrent_special_defense() {
		return current_special_defense;
	}

	public void setCurrent_special_defense(int current_special_defense) {
		this.current_special_defense = current_special_defense;
	}

	public int getCurrent_speed() {
		return current_speed;
	}

	public void setCurrent_speed(int current_speed) {
		this.current_speed = current_speed;
	}

	public int getBattle_attack() {
		return battle_attack;
	}

	public void setBattle_attack(int battle_attack) {
		this.battle_attack = battle_attack;
	}

	public int getBattle_defense() {
		return battle_defense;
	}

	public void setBattle_defense(int battle_defense) {
		this.battle_defense = battle_defense;
	}

	public int getBattle_special_attack() {
		return battle_special_attack;
	}

	public void setBattle_special_attack(int battle_special_attack) {
		this.battle_special_attack = battle_special_attack;
	}

	public int getBattle_special_defense() {
		return battle_special_defense;
	}

	public void setBattle_special_defense(int battle_special_defense) {
		this.battle_special_defense = battle_special_defense;
	}

	public int getBattle_speed() {
		return battle_speed;
	}

	public void setBattle_speed(int battle_speed) {
		this.battle_speed = battle_speed;
	}

	public double getBattle_accuracy() {
		return battle_accuracy;
	}

	public void setBattle_accuracy(double battle_accuracy) {
		this.battle_accuracy = battle_accuracy;
	}

	public double getBattle_evasion() {
		return battle_evasion;
	}

	public void setBattle_evasion(double battle_evasion) {
		this.battle_evasion = battle_evasion;
	}

	public int getBattle_attack_count() {
		return battle_attack_count;
	}

	public void setBattle_attack_count(int battle_attack_count) {
		this.battle_attack_count = battle_attack_count;
	}

	public int getBattle_defense_count() {
		return battle_defense_count;
	}

	public void setBattle_defense_count(int battle_defense_count) {
		this.battle_defense_count = battle_defense_count;
	}

	public int getBattle_special_attack_count() {
		return battle_special_attack_count;
	}

	public void setBattle_special_attack_count(int battle_special_attack_count) {
		this.battle_special_attack_count = battle_special_attack_count;
	}

	public int getBattle_special_defense_count() {
		return battle_special_defense_count;
	}

	public void setBattle_special_defense_count(int battle_special_defense_count) {
		this.battle_special_defense_count = battle_special_defense_count;
	}

	public int getBattle_speed_count() {
		return battle_speed_count;
	}

	public void setBattle_speed_count(int battle_speed_count) {
		this.battle_speed_count = battle_speed_count;
	}

	public int getBattle_accuracy_count() {
		return battle_accuracy_count;
	}

	public void setBattle_accuracy_count(int battle_accuracy_count) {
		this.battle_accuracy_count = battle_accuracy_count;
	}

	public int getBattle_evasion_count() {
		return battle_evasion_count;
	}

	public void setBattle_evasion_count(int battle_evasion_count) {
		this.battle_evasion_count = battle_evasion_count;
	}

	public int getConfused_turns() {
		return confused_turns;
	}

	public void setConfused_turns(int confused_turns) {
		this.confused_turns = confused_turns;
	}

	public int getSleep_turns() {
		return sleep_turns;
	}

	public void setSleep_turns(int sleep_turns) {
		this.sleep_turns = sleep_turns;
	}

	public String getBattle_status() {
		return battle_status;
	}

	public void setBattle_status(String battle_status) {
		this.battle_status = battle_status;
	}

	public int getLevel() {
		return level;
	}

	public boolean isFainted() {
		return fainted;
	}

	public void setFainted(boolean fainted) {
		this.fainted = fainted;
	}

	public boolean isConfused() {
		return confused;
	}

	public void setConfused(boolean confused) {
		this.confused = confused;
	}

	public boolean isFlinched() {
		return flinched;
	}

	public void setFlinched(boolean flinched) {
		this.flinched = flinched;
	}

	public boolean isRecharging() {
		return recharging;
	}

	public void setRecharging(boolean recharging) {
		this.recharging = recharging;
	}

	public boolean isInTwoTurn() {
		return inTwoTurn;
	}
	
	public void setInTwoTurn(boolean inTwoTurn) {
		this.inTwoTurn = inTwoTurn;
	}

	public boolean isLeeched() {
		return isLeeched;
	}

	public void setLeeched(boolean isLeeched) {
		this.isLeeched = isLeeched;
	}
	
}
