# Generative AI using Spring AI library
**The Spring AI** project aims to streamline the development of applications that incorporate artificial intelligence functionality without unnecessary complexity.

The project draws inspiration from notable Python projects, such as LangChain and LlamaIndex, but Spring AI is not a direct port of those projects. 
The project was founded with the belief that the next wave of Generative AI applications will not be only for Python developers but will be ubiquitous across many programming languages.

This project aims to provide an example of integrating OpenAI Chat. The following are the required steps:
1. Create an account at [OpenAI signup](https://auth.openai.com/) page and generate the token on [the API Keys page](https://platform.openai.com/api-keys),
2. Create a Spring boot project using [spring initializr](https://start.spring.io/), add Spring Web dependency and import the project in your prefered IDE,
3. Edit the pom.xml:
  - Add milestone and Snapshot Repositories:
  ```xml
    <repositories>
    <repository>
      <id>spring-milestones</id>
      <name>Spring Milestones</name>
      <url>https://repo.spring.io/milestone</url>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </repository>
    <repository>
      <id>spring-snapshots</id>
      <name>Spring Snapshots</name>
      <url>https://repo.spring.io/snapshot</url>
      <releases>
        <enabled>false</enabled>
      </releases>
    </repository>
  </repositories>
  ```
 - Add dependency Management:
  ```xml
   <dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-bom</artifactId>
            <version>0.8.1-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
  </dependencyManagement>
```
  - Enable Spring Boot auto-configuration for the OpenAI Chat Client, by adding the following dependency:
  ```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
</dependency>
```
4. Add a application.properties file, under the src/main/resources directory, to enable and configure the OpenAi Chat client:
```properties
  spring.ai.openai.api-key=YOUR_API_KEY
  spring.ai.openai.chat.options.model=gpt-3.5-turbo
  spring.ai.openai.chat.options.temperature=0.7
```
replace the api-key with your OpenAI credentials.

Clone the application, run it (will be available on port 8989) and test the API endpoints by using Postman or other client.

Go to **http:localhost/8989/question.html** to use the page.


For more info: https://www.buddy-mentor.com/
   

