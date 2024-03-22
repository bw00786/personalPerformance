package repositories;

import models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>
{
    List<Message> findByUser(String User);
}
