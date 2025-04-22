package com.bridge.example.messageboard.service;

import com.bridge.example.messageboard.entity.MBMessage;
import com.bridge.example.messageboard.repository.MessageBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service

public class MessageBoardService {

    private final MessageBoardRepository messageBoardRepository;

    public List<MBMessage> getAllItems() {
        return messageBoardRepository.findAll();
    }

    public MBMessage getMessageById(Long Id) {
        return messageBoardRepository.findById(Id).orElse(null);
    }

    public MBMessage createMessageBoardMessage(MBMessage message) {
        return messageBoardRepository.save(message);
    }

    public MBMessage updateMessageBoardMessage(MBMessage updatedMessage, Long Id) {
        MBMessage existingMessage = messageBoardRepository.findById(Id).orElse(null);
        assert existingMessage != null;
        existingMessage.setMessage(updatedMessage.getMessage());
        return messageBoardRepository.save(existingMessage);
    }

    public void deleteMBMessageById(Long Id) {
        messageBoardRepository.deleteById(Id);
    }

}
