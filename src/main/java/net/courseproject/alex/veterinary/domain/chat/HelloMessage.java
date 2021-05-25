package net.courseproject.alex.veterinary.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class HelloMessage {
    private String name;
}
