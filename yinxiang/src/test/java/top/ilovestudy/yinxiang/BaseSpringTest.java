package top.ilovestudy.yinxiang;

import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import top.ilovestudy.yinxiang.config.EverNoteProperties;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseSpringTest {
  @MockBean
  protected UserStoreClient userStoreClient;

  @MockBean
  protected NoteStoreClient noteStoreClient;

  @MockBean
  protected ClientFactory clientFactory;

  @MockBean
  protected EverNoteProperties everNoteProperties;
}
