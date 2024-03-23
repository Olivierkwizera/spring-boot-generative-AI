package buddy.mentor.com.generativeai.controller;

import buddy.mentor.com.generativeai.dto.bookDetails;
import buddy.mentor.com.generativeai.service.OpenAIService;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OpenAIController {
    @Autowired
    OpenAIService aiService;

    @GetMapping("/ai/generate")
    public String getGeneration(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return aiService.generate(message).get("generation");
    }

    @GetMapping("/ai/generateStream")
    public Flux<String> getStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return aiService.extractCompletions(message);
    }


}
