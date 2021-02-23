package net.courseproject.alex.veterinary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuestDto {
    private String name;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;
    private String password;
}
