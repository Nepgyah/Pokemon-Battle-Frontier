package trainer;
import item.Item;
import pokemon.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;

/**
* Public class that represents the trainers of pokemon game.
* Trainers can have a party of pokemon, hold items and have personal 
* accolades associated with them
*/
public class Trainer implements Serializable {
    
    private static final long serialVersionUID = 1L;

    String name;
    String title;
    String hometown;
    ArrayList<Pokemon> party = new ArrayList<>();
    ArrayList<Item> bag = new ArrayList<>();
    protected Pokemon[] moveset = new Pokemon[6];
    
    public Trainer(String name) {
        super();
        this.name = name;
        this.title = "Rookie Trainer";
        this.hometown = "Battle Frontier";
    }

    /**
     * Adds a pokemon to the trainers party
     * @param pokemon pokemon to be added
     */
    public void addToParty(Pokemon pokemon) {
        party.add(pokemon);
    }

    /**
     * Console displays the trainers party in detail
     */
    public void displayPartyDetailed() {
        System.out.println("\n" + this.name + "'" + this.title + "'");
        System.out.println("Party Pokemon");
        for(Pokemon pokemon : party) {
            pokemon.displayInformation();
        }
        System.out.println();
    }

    /**
     * Console displays the trainers party with basic information
     */
    public void displayPartyBasic() {
        System.out.println("\n" + this.name + "'s Party");
        for(int i = 0; i < party.size(); i++) {
            System.out.print((i+1) + ". " + party.get(i).getName());
            if(party.get(i).isFainted()) System.out.println(" FNT");
            else System.out.println();
        }
        System.out.println();
    }

    /**
     * Adds an item to the trainers bag
     * @param item item to added
     */
    public void addToBag(Item item) {
        bag.add(item);
    }
    
    /**
     * Removes an item from a trainers bag
     * @param item item to be removed
     */
    public void removeFromBag(Item item) {
        int index = bag.indexOf(item);
        bag.remove(index);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pokemon> getParty() {
        return party;
    }

    public void setParty(ArrayList<Pokemon> party) {
        this.party = party;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
