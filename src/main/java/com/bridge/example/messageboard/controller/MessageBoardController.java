package com.bridge.example.messageboard.controller;


import com.bridge.example.messageboard.entity.MBMessage;
import com.bridge.example.messageboard.service.MessageBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RestController
@RequestMapping("/api/messageBoard")
public class MessageBoardController {

    private final MessageBoardService messageBoardService;

    public MessageBoardController(MessageBoardService messageBoardService) {

        this.messageBoardService = messageBoardService;

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MBMessage>> getAllItems() {
        List<MBMessage> message = messageBoardService.getAllItems();
        return ResponseEntity.ok(message);
    }

    @GetMapping("{Id}")
    public ResponseEntity<MBMessage> getMessageById(@PathVariable Long Id) {
        MBMessage specificMessage = messageBoardService.getMessageById(Id);
        return ResponseEntity.ok(specificMessage);
    }

    @PostMapping("/message")
    public ResponseEntity<MBMessage> createMessage(@RequestBody MBMessage message) {
        MBMessage newMessage = messageBoardService.createMessageBoardMessage(message);
        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    }

    @PutMapping("{Id}")
    public ResponseEntity<MBMessage> updateMessage(@RequestBody MBMessage message, @PathVariable Long Id) {
        MBMessage updatedMessage = messageBoardService.updateMessageBoardMessage(message, Id);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long Id) {
        messageBoardService.deleteMBMessageById(Id);
        return ResponseEntity.ok("Message Deleted");
    }

}
