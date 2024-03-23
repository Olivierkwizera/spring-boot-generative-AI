package buddy.mentor.com.generativeai.service;

import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OpenAIService {


    @Autowired
    OpenAiChatClient chatClient;

    public Map<String, String> generate(String message) {
        return Map.of("generation", chatClient.call(message));

    }

    public Flux<String> extractCompletions(String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.stream(prompt)
                .flatMap(chatResponse -> Flux.fromIterable(chatResponse.getResults())
                        .map(Generation::getOutput)
                        .filter(output -> output != null && output.getContent() != null) // Add null check here
                        .map(AssistantMessage::getContent)
                        .filter(Objects::nonNull)); // Add null check here
    }


}
