
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
public class ConversationApplication {

    @SpringBootApplication
    public static class ConversationsApplication {

        public static void main(String[] args) {
            SpringApplication.run(ConversationsApplication.class, args);
        }

    }
}
