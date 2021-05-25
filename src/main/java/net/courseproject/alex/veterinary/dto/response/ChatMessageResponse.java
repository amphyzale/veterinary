package net.courseproject.alex.veterinary.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ChatMessageResponse {
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private LocalDateTime dateTime;
    private String status;
}
