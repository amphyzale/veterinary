package net.courseproject.alex.veterinary.exception.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    private int status;
    private String message;
    private String dateTime;
    private String error;
}
