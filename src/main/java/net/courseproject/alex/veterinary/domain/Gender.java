package net.courseproject.alex.veterinary.domain;

public enum Gender {
    ALE("male"),

    FEMALE("female"),

    IT("it");

    String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender fromValue(String text) {
        for (Gender b : Gender.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return Gender.IT;
    }
}
