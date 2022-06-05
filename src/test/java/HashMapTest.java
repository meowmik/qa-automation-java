import com.tcs.edu.Message;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.service.CrudService;
import com.tcs.edu.service.CrudServiceImpl;
import com.tcs.edu.service.HashMapMessageRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
     private CrudService messageRepository = new CrudServiceImpl(new HashMapMessageRepository());

    @Test
    public void saveElement(){
        Message message = new Message(Severity.MAJOR, "message");
        UUID id = messageRepository.post(message);

        //assertEquals(id,message.getId());
        assertThat(messageRepository.getAll())
                .contains(message)
                .hasSize(1)
                .filteredOnAssertions(one -> assertThat(one.getId()).isEqualTo(id)).contains(message);
    }

    @Test
    public void getAllElement(){
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");
        Message message4 = new Message(Severity.MAJOR, "message1");

        messageRepository.post(message1);
        messageRepository.post(message2);
        messageRepository.post(message3);

        assertThat(messageRepository.getAll())
                .contains(message1)
                .contains(message2)
                .contains(message3)
                .doesNotContain(message4);

    }

    @Test
    public void deleteOneElement(){
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");

        UUID id = messageRepository.post(message1);
        messageRepository.post(message2);
        messageRepository.delete(id);
// Тут не знаю, как в один assert сделать
        assertThat(messageRepository.getAll())
                .contains(message2)
                .doesNotContain(message1);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> messageRepository.getById(id));
        assertThat(thrown).hasMessage(String.format("Нет значения с id = %s", id));
    }

    @Test
    public void getElementById(){
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");

        UUID id = messageRepository.post(message1);
        messageRepository.post(message2);

        assertThat(messageRepository.getById(id)).isEqualTo(message1);
    }

    @Test
    public void getElementsByLevel(){
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");

        messageRepository.post(message1);
        messageRepository.post(message2);
        messageRepository.post(message3);

        assertThat(messageRepository.getBySeverity(Severity.MAJOR))
                .doesNotContain(message1)
                .contains(message2)
                .contains(message3);
    }

    @Test
    public void updateElement(){
        Message message = new Message(Severity.MAJOR, "message");
        Message message1 = new Message(Severity.REGULAR, "message1");

        UUID id = messageRepository.post(message);
        message1.setId(id);
        messageRepository.update(message1);

        assertThat(messageRepository.getAll())
                .doesNotContain(message)
                .contains(message1);
    }
}
