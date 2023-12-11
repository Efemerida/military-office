package com.militaryOffice.model;

public enum MilitaryStatus {

        RESERVE ("В запасе"),
        ACCOUNTING ("Офицер запаса"),
        SERVICE ("На службе");

        private final String value;

        MilitaryStatus(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    public static MilitaryStatus getByValue(String value){
        if(value.equals("В запасе")) return RESERVE;
        if(value.equals("Офицер запаса")) return ACCOUNTING;
        if(value.equals("На службе")) return SERVICE;
        throw new IllegalArgumentException();
    }

        @Override
        public String toString() {
            return "Status{" +
                    "value='" + value + '\'' +
                    '}';
        }

}
