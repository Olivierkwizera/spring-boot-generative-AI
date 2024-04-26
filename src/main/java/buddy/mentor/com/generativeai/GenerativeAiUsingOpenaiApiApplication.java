package buddy.mentor.com.generativeai;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GenerativeAiUsingOpenaiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerativeAiUsingOpenaiApiApplication.class, args);
    }

    private static void one(Resource resource, ChatClient openAiChatClient) {
        var promptTemplate = new PromptTemplate(resource);
        var prompt = promptTemplate.create(Map.of("adjective", "funny", "topic", "cows"));
        var aiResponse = openAiChatClient.call(prompt);
        System.out.println("Response: " + aiResponse.getResults());
        var bop = new BeanOutputParser<ActorFilms>(ActorFilms.class);
        var formatString = bop.getFormat();
        System.out.println("Format:" + formatString);

    }

    private static void two(ChatClient openAiChatClient) {
        var bop = new BeanOutputParser<>(ActorFilms.class);
        var formatString = bop.getFormat();
        System.out.println("Format:" + formatString);
        var userMessage = """
                Generate the filmography for the actor {actor}.
                {format}
                """;
        var pt = new PromptTemplate(userMessage, Map.of("format", formatString, "actor", "Jeff Bridges"));
        var prompt = new Prompt(pt.createMessage());
        var generation = openAiChatClient.call(prompt).getResult();
        var actorFilmsResults = bop.parse(generation.getOutput().getContent());
        System.out.println("Actors: " + actorFilmsResults.toString());
    }

    @Bean
    ApplicationRunner applicationRunner(@Value("classpath:/docs/wikipedia-curling.md") Resource resource, ChatClient openAiChatClient) {
        
        return args -> {
            // one(resource, openAiChatClient);
            // two(openAiChatClient);

            var qaPrompt = """
                    Use the following pieces of context to answer to the question at the end. If you don't know the answer, just say you't know, don't try to make up an anwser.
                    {context}
                    Question: {question}
                    Helpful answer:
                    """;
            var pt = new PromptTemplate(qaPrompt);
            var q = "Which athletes won the gold medal in curling at the 2022 Winter olympics?";
            Prompt prompt = pt.create(Map.of("question", q, "context", resource));
            System.out.println("Output: " + openAiChatClient.call(prompt).getResult());
        };

    }


}

record ActorFilms(String actor, List<String> movies) {
    @Override
    public String actor() {
        return actor;
    }

    @Override
    public List<String> movies() {
        return movies;
    }
}
