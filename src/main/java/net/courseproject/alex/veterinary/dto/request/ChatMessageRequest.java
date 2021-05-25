package net.courseproject.alex.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //private LocalDateTime dateTime;
    private MessageStatus status;

    public enum MessageStatus {

        RECEIVED("RECEIVED"),
        DELIVERED("DELIVERED"),
        BROKEN("BROKEN");

        private final String value;

        MessageStatus(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static MessageStatus fromValue(String text) {
            for (MessageStatus b : MessageStatus.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return MessageStatus.BROKEN;
        }
    }
}
