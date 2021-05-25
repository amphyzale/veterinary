package net.courseproject.alex.veterinary.service;

import net.courseproject.alex.veterinary.dto.request.ChatMessageRequest;
import net.courseproject.alex.veterinary.dto.response.ChatMessageResponse;

import java.util.List;

public interface IChatMessageService {
    ChatMessageResponse save(ChatMessageRequest chatMessage);
    Long countNewMessages(Long senderId, Long recipientId);
    List<ChatMessageResponse> findChatMessages(Long senderId, Long recipientId);
    ChatMessageResponse findById(Long id);
    void updateStatuses(Long senderId, Long recipientId, String status);
}
