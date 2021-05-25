package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.chat.ChatMessage;
import net.courseproject.alex.veterinary.domain.chat.MessageStatus;
import net.courseproject.alex.veterinary.dto.request.ChatMessageRequest;
import net.courseproject.alex.veterinary.dto.response.ChatMessageResponse;
import net.courseproject.alex.veterinary.repository.ChatMessageRepository;
import net.courseproject.alex.veterinary.repository.UserRepository;
import net.courseproject.alex.veterinary.service.IChatMessageService;
import net.courseproject.alex.veterinary.service.IChatRoomService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {

    private final IChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Override
    public ChatMessageResponse save(ChatMessageRequest chatMessage) {
        ChatMessage message = new ChatMessage();
        message.setContent(chatMessage.getContent());
        //message.setDateTime(chatMessage.getDateTime());
        message.setSender(userRepository.findById(chatMessage.getSenderId()).orElseThrow());
        message.setRecipient(userRepository.findById(chatMessage.getRecipientId()).orElseThrow());
        message.setSenderName(chatMessage.getSenderName());
        message.setRecipientName(chatMessage.getRecipientName());
        message.setStatus(MessageStatus.RECEIVED);
        ChatMessage savedMessage = chatMessageRepository.save(message);
        return new ChatMessageResponse()
                .setId(savedMessage.getId())
                .setChatId(savedMessage.getChatId())
                .setContent(savedMessage.getContent())
                .setDateTime(savedMessage.getDateTime())
                .setSenderName(savedMessage.getSenderName())
                .setRecipientName(savedMessage.getRecipientName())
                .setStatus(savedMessage.getStatus().name())
                .setSenderId(savedMessage.getSender().getId())
                .setRecipientId(savedMessage.getRecipient().getId());

    }

    @Override
    public Long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessageResponse> findChatMessages(Long senderId, Long recipientId) {
        Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> chatMessages = chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
        if (chatMessages.size() > 0) {
            updateStatuses(senderId, recipientId, "DELEVERED");
        }
        return chatMessages.stream()
                .map(m -> new ChatMessageResponse()
                        .setId(m.getId())
                        .setChatId(m.getChatId())
                        .setContent(m.getContent())
                        .setDateTime(m.getDateTime())
                        .setSenderName(m.getSenderName())
                        .setRecipientName(m.getRecipientName())
                        .setStatus(m.getStatus().name())
                        .setSenderId(m.getSender().getId())
                        .setRecipientId(m.getRecipient().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public ChatMessageResponse findById(Long id) {
        return chatMessageRepository.findById(id)
                .map(m -> {
                    m.setStatus(MessageStatus.DELIVERED);
                    ChatMessage savedMessage = chatMessageRepository.save(m);
                    return new ChatMessageResponse()
                            .setId(savedMessage.getId())
                            .setChatId(savedMessage.getChatId())
                            .setContent(savedMessage.getContent())
                            .setDateTime(savedMessage.getDateTime())
                            .setSenderName(savedMessage.getSenderName())
                            .setRecipientName(savedMessage.getRecipientName())
                            .setStatus(savedMessage.getStatus().name())
                            .setSenderId(savedMessage.getSender().getId())
                            .setRecipientId(savedMessage.getRecipient().getId());
                })
                .orElseThrow();
    }

    @Override
    public void updateStatuses(Long senderId, Long recipientId, String status) {
        chatMessageRepository.saveAll(chatMessageRepository.findBySenderIdAndRecipientId(senderId, recipientId).stream()
                .peek(i -> i.setStatus(MessageStatus.valueOf(status)))
                .collect(Collectors.toList())
        );
    }
}
