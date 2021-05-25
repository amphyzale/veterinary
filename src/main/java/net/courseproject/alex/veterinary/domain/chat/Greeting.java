package net.courseproject.alex.veterinary.domain.chat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Greeting {
    private String content;
}
