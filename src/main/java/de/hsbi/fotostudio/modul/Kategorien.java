package de.hsbi.fotostudio.modul;

/**
 *
 * @author Janis Wiegräbe
 */
public enum Kategorien{
    ALLES("Alles"),
    INNERHAUS("Innerhaus"),
    AUSSERHAUS("Außerhaus"),
    EQUIPMENT("Equipment");

    private final String text;

    Kategorien(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
