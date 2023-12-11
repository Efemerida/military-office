package com.militaryOffice.model;

public enum UserRequestStatus {

    SUCCESS("Одобрено"),
    FAILED("Отказано"),
    PROCESSING("В процессе");

    private final String value;

    UserRequestStatus(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
