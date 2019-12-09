package top.ilovestudy.yinxiang.config;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.thrift.TException;
import com.evernote.thrift.transport.TTransportException;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import static com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR;
import static com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR;

@SpringBootConfiguration
public class EverNoteConfig {

  @Bean
  public UserStoreClient userStoreClient(ClientFactory clientFactory) throws TTransportException {
    return clientFactory.createUserStoreClient();
  }

  @Bean
  public NoteStoreClient noteStoreClient(ClientFactory clientFactory, UserStoreClient userStoreClient) throws TException, EDAMSystemException, EDAMUserException {
    boolean versionOk = userStoreClient.checkVersion("Evernote EDAMDemo (Java)",
        EDAM_VERSION_MAJOR,
        EDAM_VERSION_MINOR);
    if (!versionOk) {
      System.err.println("Incompatible Evernote client protocol version");
      System.exit(1);
    }

    return clientFactory.createNoteStoreClient();
  }

  @Bean
  public ClientFactory clientFactory(EverNoteProperties everNoteProperties) {
    EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, everNoteProperties.getDevToken());
    evernoteAuth.setNoteStoreUrl(everNoteProperties.getNoteStoreUrl());
    return new ClientFactory(evernoteAuth);
  }

}
