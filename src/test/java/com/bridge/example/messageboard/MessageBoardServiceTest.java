package com.bridge.example.messageboard;

import com.bridge.example.messageboard.entity.MBMessage;
import com.bridge.example.messageboard.repository.MessageBoardRepository;
import com.bridge.example.messageboard.service.MessageBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageBoardServiceTest {
    @Mock
    MessageBoardRepository messageBoardRepository;

    @InjectMocks
    MessageBoardService messageBoardService;

    private MBMessage testMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testMessage = new MBMessage();
        testMessage.setId(1L);
        testMessage.setMessage("This is a test");
    }

    @Test
    void getAllItems() {
        List<MBMessage> messages = List.of(testMessage);
        when(messageBoardRepository.findAll()).thenReturn(messages);

        List<MBMessage> result = messageBoardService.getAllItems();

        assertEquals(1, result.size());
//        assertThat(result).isEqualTo(1);
        assertEquals("This is a test", result.get(0).getMessage());
        verify (messageBoardRepository, times(1)).findAll();
    }

    @Test
    void getMessageById() {
        when(messageBoardRepository.findById(1L)).thenReturn(Optional.of(testMessage));

        MBMessage result = messageBoardService.getMessageById(1L);

        assertThat(result).isEqualTo(notNull());
        assertEquals("This is a test", result.getMessage());
    }

    @Test
    void createMessageBoardMessage() {
        when(messageBoardRepository.save(testMessage)).thenReturn(testMessage);

        MBMessage result = messageBoardService.createMessageBoardMessage(testMessage);

        assertEquals("This is a test", result.getMessage());
    }

    @Test
    void updateMessageBoardMessage() {
        MBMessage updatedMessage = new MBMessage();
        updatedMessage.setMessage("This is the updated test message");

        when(messageBoardRepository.findById(1L)).thenReturn(Optional.of(testMessage));
        when(messageBoardRepository.save(any(MBMessage.class))).thenReturn(testMessage);

        MBMessage result = messageBoardService.updateMessageBoardMessage(updatedMessage, 1L);

        assertThat(result).isEqualTo("This is the updated test message");
        verify(messageBoardRepository).save(testMessage);
    }
}
