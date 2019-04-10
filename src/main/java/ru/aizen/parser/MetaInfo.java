package ru.aizen.parser;

import ru.aizen.Language;

public class MetaInfo {
    private int multiverseID;
    private int oracleID;
    private String name;
    private Language language;

    public MetaInfo(int multiverseID, String name, Language language) {
        this.multiverseID = multiverseID;
        this.name = name;
        this.language = language;
    }

    public MetaInfo(int multiverseID, int oracleID, String name, Language language) {
        this(multiverseID, name, language);
        this.oracleID = oracleID;
    }

    public int getMultiverseID() {
        return multiverseID;
    }

    public int getOracleID() {
        return oracleID;
    }

    public String getName() {
        return name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setOracleID(int oracleID) {
        this.oracleID = oracleID;
    }

    @Override
    public String toString() {
        return "MetaInfo{" +
                "multiverseID=" + multiverseID +
                ", oracleID=" + oracleID +
                ", name='" + name + '\'' +
                ", language=" + language +
                '}';
    }
}