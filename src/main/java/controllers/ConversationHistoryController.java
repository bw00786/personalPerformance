package controllers;

import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.MessageRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/api/conversation-history")
public class ConversationHistoryController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ExecutorService executorService; // Inject an ExecutorService

    @GetMapping
    public ResponseEntity<List<Message>> getConversationHistory() {
        try {
            List<Message> messages;
            if (isRequestForSpecificUser()) { // Check for specific user request
                messages = messageRepository.findByUser(getUsernameFromRequest());
            } else {
                messages = messageRepository.findAll(); // Retrieve all messages
            }
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            // Handle exception appropriately, log error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    private boolean isRequestForSpecificUser() {
        // Implement logic to check for specific user request based on header or parameter
        return false; // Replace with your logic
    }

    private String getUsernameFromRequest() {
        // Implement logic to retrieve username from header or parameter
        return ""; // Replace with your logic
    }

    // Additional methods for multi-threading (optional)

    public CompletableFuture<ResponseEntity<List<Message>>> getConversationHistoryAsync() {
        return CompletableFuture.supplyAsync(() -> getConversationHistory(), executorService);
    }

    // You can implement other methods for specific use cases with multi-threading
}
