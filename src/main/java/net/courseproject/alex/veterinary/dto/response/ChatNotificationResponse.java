package net.courseproject.alex.veterinary.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatNotificationResponse {
    private Long id;
    private Long senderId;
    private String senderName;
}
