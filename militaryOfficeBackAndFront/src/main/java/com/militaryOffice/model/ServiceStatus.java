package com.militaryOffice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public enum ServiceStatus {
    ALTERNATIVE("Альтернативная"),
    CONTRACT("Контрактная"),
    SUBPOENA("По призыву");

    private final String value;
    private ServiceStatus(String value){
        this.value = value;
    }
}
