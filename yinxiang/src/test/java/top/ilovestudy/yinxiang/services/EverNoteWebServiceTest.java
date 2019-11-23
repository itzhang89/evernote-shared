package top.ilovestudy.yinxiang.services;

import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import top.ilovestudy.yinxiang.config.EverNoteProperties;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EverNoteWebServiceTest {

  @SpyBean
  NoteStoreClient noteStoreClient;

  @SpyBean
  EverNoteProperties everNoteProperties;

  @Autowired
  EverNoteWebService everNoteWebService;

  List<Note> notes = new ArrayList<>();

  @BeforeEach
  void setUp() {
    Note note1 = new Note();
    note1.setGuid("titleAa");
    note1.setGuid("使用反向代理进行内网穿透");
    Note note2 = new Note();
    note2.setGuid("titleBb");
    note2.setGuid("ngrok原理浅析  | Tony Bai");
    notes.add(note1);
    notes.add(note2);
  }

  @Test
  void shouldHaveEmptyNoteBooksWhenFilterNoteBookByEverNoteProperties() throws TException, EDAMUserException, EDAMSystemException {
    when(noteStoreClient.listNotebooks()).thenReturn(provideNoteBooks());
    when(everNoteProperties.getFilterNoteBooks()).thenReturn(new String[]{"PrivateNoteBookA", "PrivateNoteBookB"});

    List<Notebook> filteredNoteBooks = everNoteWebService.getFilteredNoteBooks();

    Assert.assertEquals(0, filteredNoteBooks.size());
  }

  @Test
  void shouldHaveAllNoteBooksWhenFilterNoteBookByEverNoteProperties() throws TException, EDAMUserException, EDAMSystemException {
    when(noteStoreClient.listNotebooks()).thenReturn(provideNoteBooks());
    when(everNoteProperties.getFilterNoteBooks()).thenReturn(new String[]{"PrivateNoteBookC", "PrivateNoteBookD"});

    List<Notebook> filteredNoteBooks = everNoteWebService.getFilteredNoteBooks();

    Assert.assertEquals(2, filteredNoteBooks.size());
  }

  private List<Notebook> provideNoteBooks() {
    List<Notebook> notebooks = new ArrayList<>();
    Notebook notebook1 = new Notebook();
    notebook1.setGuid("aaaaaaaaaaaaaa");
    notebook1.setName("PrivateNoteBookA");
    notebooks.add(notebook1);

    Notebook notebook2 = new Notebook();
    notebook2.setGuid("bbbbbbbbbbbbbb");
    notebook2.setName("PrivateNoteBookB");
    notebooks.add(notebook2);
    return notebooks;
  }
}