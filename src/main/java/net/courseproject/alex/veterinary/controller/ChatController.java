package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.chat.Greeting;
import net.courseproject.alex.veterinary.domain.chat.HelloMessage;
import net.courseproject.alex.veterinary.dto.request.ChatMessageRequest;
import net.courseproject.alex.veterinary.dto.response.ChatMessageResponse;
import net.courseproject.alex.veterinary.dto.response.ChatNotificationResponse;
import net.courseproject.alex.veterinary.service.IChatMessageService;
import net.courseproject.alex.veterinary.service.IChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IChatMessageService chatMessageService;
    private final IChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageRequest chatMessage) {
        Optional<String> chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessageResponse saved = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()),
                "/queue/messages",
                new ChatNotificationResponse()
                        .setId(saved.getId())
                        .setSenderId(saved.getSenderId())
                        .setSenderName(saved.getSenderName())
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable Long id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }

    @MessageMapping("/hello")
    @SendTo("/user/user/1")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting()
                .setContent("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
