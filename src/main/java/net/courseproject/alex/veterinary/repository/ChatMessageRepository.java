package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.chat.ChatMessage;
import net.courseproject.alex.veterinary.domain.chat.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
