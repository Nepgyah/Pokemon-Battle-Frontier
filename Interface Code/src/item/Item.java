package item;

import java.io.Serializable;

public abstract class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;
    protected String name;
    
    public Item(String name) {
        super();
        this.name = name;
    }
    
    public abstract Item copy();
    
}
