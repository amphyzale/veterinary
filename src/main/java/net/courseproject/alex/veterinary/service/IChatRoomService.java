package net.courseproject.alex.veterinary.service;

import java.util.Optional;

public interface IChatRoomService {
    Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist);
}
