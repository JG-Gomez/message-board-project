package com.bridge.example.messageboard;

import com.bridge.example.messageboard.controller.MessageBoardController;
import com.bridge.example.messageboard.entity.MBMessage;
import com.bridge.example.messageboard.service.MessageBoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MessageBoardController.class)
public class MessageBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MessageBoardService messageBoardService;

    private MBMessage testMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testMessage = new MBMessage();
        testMessage.setId(13L);
        testMessage.setMessage("This is first controller test message");
        testMessage.setUserRating(5.0);
        testMessage.setCreatedAt(Instant.now());
    }

    @Test
    void shouldGetAllMessages() throws Exception {
        List<MBMessage> messages = List.of(testMessage);

        Mockito.when(messageBoardService.getAllItems()).thenReturn(messages);

        mockMvc.perform(get("/api/messageBoard/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(13L))
                .andExpect(jsonPath("$[0].message").value("This is first controller test message"))
                .andExpect(jsonPath("$[0].userRating").value(5.0))
                .andExpect(jsonPath("$[0].createdAt").exists());
    }

    @Test
    void shouldReturnMessageById() throws Exception {
        MBMessage messageReturn = new MBMessage();

        Mockito.when(messageBoardService.getMessageById(13L)).thenReturn(messageReturn);

        mockMvc.perform(get("/api/messageBoard/Id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(13L))
                .andExpect(jsonPath("$.message").value("This is second test"))
                .andExpect(jsonPath("$.userRating").value(3.8))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnCreatedMessage() throws Exception {
        MBMessage createdMessage = new MBMessage();

        Mockito.when(messageBoardService.createMessageBoardMessage(Mockito.any(MBMessage.class))).thenReturn(createdMessage);

        mockMvc.perform(post("/api/messageBoard/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdMessage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.message").value("This is message created test"))
                .andExpect(jsonPath("$.userRating").value(4.5))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldUpdateMessage() throws Exception {
        MBMessage messageUpdate = new MBMessage();

        Mockito.when(messageBoardService.updateMessageBoardMessage(Mockito.any(MBMessage.class), Mockito.eq(4L))).thenReturn(messageUpdate);

        mockMvc.perform(put("/api/messageBoard/Id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageUpdate)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.message").value("This is updating message test"))
                .andExpect(jsonPath("$.userRating").value(4.9))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldDeleteMessage() throws Exception {
        doNothing().when(messageBoardService).deleteMBMessageById(1L);

        mockMvc.perform(delete("/api/messageBoard/Id"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Message Deleted"));
    }
}
