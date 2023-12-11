package com.militaryOffice.model;

public enum NotificationStatus {
    SUBPOENA("ПОВЕСТКА"),
    SUCCESSCHANGE("Успешная смена военкомата"),
    FAILCHANGE("Неуспешная смена военкомата"),
    SUCCESSDOCUMENT("Одобренный запрос на выдачу справки"),
    FAILDOCUMENT("Неодобренный запрос на выдачу справки");

    private final String value;

    NotificationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
