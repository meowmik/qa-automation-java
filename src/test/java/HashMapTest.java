import com.tcs.edu.Message;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.service.CrudService;
import com.tcs.edu.service.CrudServiceImpl;
import com.tcs.edu.service.HashMapMessageRepository;
import com.tcs.edu.service.MessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    private MessageRepository messageRepository;
    private CrudService messageService;

    @AfterEach
    public void deleteData(){

    }

    @BeforeEach
    public void createData(){
         messageRepository = new HashMapMessageRepository();
         messageService = new CrudServiceImpl(messageRepository);
    }

    @Nested
    class actionsWithElement {
        @Test
        public void saveElement() {
            Message message = new Message(Severity.MAJOR, "message");
            UUID id = messageService.post(message);

            assertThat(messageRepository.findAll())
                    .contains(message)
                    .hasSize(1)
                    .filteredOnAssertions(one -> assertThat(one.getId()).isEqualTo(id)).contains(message);
        }

        @Test
        public void deleteOneElement() {
            Message message1 = new Message("message1");
            Message message2 = new Message(Severity.MAJOR, "message");

            UUID id = messageRepository.create(message1);
            messageRepository.create(message2);
            messageService.delete(id);

            assertThat(messageRepository.findAll())
                    .contains(message2)
                    .doesNotContain(message1);
        }

        @Test
        public void updateElement() {
            Message message = new Message(Severity.MAJOR, "message");
            Message message1 = new Message("message1");

            UUID id = messageRepository.create(message);
            message1.setId(id);
            messageService.update(message1);

            assertThat(messageRepository.findByPrimaryKey(id))
                    .isEqualTo(message1);
            assertThat(messageRepository.findByPrimaryKey(id).getId())
                    .isEqualTo(id);
        }
    }

    @Nested
    class getElement {

        @Test
        public void getAllElement() {
            Message message1 = new Message("message1");
            Message message2 = new Message(Severity.MAJOR, "message");
            Message message3 = new Message(Severity.MAJOR, "message");
            Message message4 = new Message(Severity.MAJOR, "message1");

            messageRepository.create(message1);
            messageRepository.create(message2);
            messageRepository.create(message3);

            assertThat(messageService.getAll())
                    .contains(message1)
                    .contains(message2)
                    .contains(message3)
                    .doesNotContain(message4);

        }

        @Test
        public void getElementById() {
            Message message1 = new Message("message1");
            Message message2 = new Message(Severity.MAJOR, "message");

            UUID id = messageRepository.create(message1);
            messageRepository.create(message2);

            assertThat(messageService.getById(id)).isEqualTo(message1);
        }

        @Test
        public void getNotExistentElementById() {
            Message message1 = new Message("message1");

            //сгенерить id
            UUID id = UUID.randomUUID();

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> messageService.getById(id));
            assertThat(thrown).hasMessage(String.format("Нет значения с id = %s", id));
        }



        @Test
        public void getElementsByLevel() {
            Message message1 = new Message("message1");
            Message message2 = new Message(Severity.MAJOR, "message");
            Message message3 = new Message(Severity.MAJOR, "message");

            messageRepository.create(message1);
            messageRepository.create(message2);
            messageRepository.create(message3);

            assertThat(messageService.getBySeverity(Severity.MAJOR))
                    .contains(message2)
                    .contains(message3)
                    .hasSize(2);
        }
    }
}
