package item;

import java.io.Serializable;

public abstract class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String description;
    
    public Item(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }
    
    public abstract Item copy();
    
    public String getName() {
        return name;
    }
}
