package de.hsbi.fotostudio.modul;

/**
 *
 * @author Janis Wiegräbe
 */
public enum LagerStatus {
    INSTOCK("In Stock"),
    OUTOFSTOCK("Out of Stock"),
    LOWSTOCK("Low Stock");

    private final String text;

    LagerStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}