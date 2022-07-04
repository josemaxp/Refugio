package model;

/**
 * Class for vaccine
 * @author josem
 */
public class Vaccine {
    int id;
    String name;

    public Vaccine( String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
