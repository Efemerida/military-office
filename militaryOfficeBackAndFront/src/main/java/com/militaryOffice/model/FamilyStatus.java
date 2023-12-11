package com.militaryOffice.model;

public enum FamilyStatus {

    SINGLE ("Холост"),
    MARRIED ("Женат/Замужем"),
    WIDOWER ("Вдова/Вдовец"),
    DIVORCED("В разводе");
    private final String value;

    FamilyStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FamilyStatus{" +
                "value='" + value + '\'' +
                '}';
    }

    public static FamilyStatus getByValue(String value){
        if(value.equals("Холост")) return SINGLE;
        if(value.equals("Женат/Замужем")) return MARRIED;
        if(value.equals("Вдова/Вдовец")) return WIDOWER;
        if(value.equals("В разводе")) return DIVORCED;
        throw new IllegalArgumentException();
    }

}