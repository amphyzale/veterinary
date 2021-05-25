package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.domain.chat.ChatRoom;
import net.courseproject.alex.veterinary.repository.ChatRoomRepository;
import net.courseproject.alex.veterinary.repository.UserRepository;
import net.courseproject.alex.veterinary.service.IChatRoomService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements IChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        User sender = userRepository.findById(senderId).orElseThrow();
        User recipient = userRepository.findById(recipientId).orElseThrow();
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    var chatId = String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = new ChatRoom();
                    senderRecipient.setChatId(chatId);
                    senderRecipient.setSender(sender);
                    senderRecipient.setRecipient(recipient);

                    ChatRoom recipientSender = new ChatRoom();
                    recipientSender.setChatId(chatId);
                    recipientSender.setSender(recipient);
                    recipientSender.setRecipient(sender);

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
