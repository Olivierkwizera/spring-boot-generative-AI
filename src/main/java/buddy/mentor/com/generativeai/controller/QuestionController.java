package buddy.mentor.com.generativeai.controller;

import buddy.mentor.com.generativeai.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@Controller
public class QuestionController {
    @Autowired
    OpenAIService aiService;

    @PostMapping(value = "/ai/generate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<String> generateResponse(@RequestBody String message) {
        return aiService.extractCompletions(message);
    }

}
