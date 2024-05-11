package de.hsbi.fotostudio.modul;

/**
 *
 * @author Janis Wiegräbe
 */
public class Kategorie {
    
    private int id;
    private String name;

    public Kategorie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Kategorie() {
    }

    @Override
    public int hashCode() {
        return 31 * id + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategorie other = (Kategorie) obj;
        return this.id == other.id;
    }

    // GETTER && SETTER
    
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
