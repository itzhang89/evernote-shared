package top.ilovestudy.yinxiang.services;

import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ilovestudy.yinxiang.BaseSpringTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class EverNoteWebServiceTest extends BaseSpringTest {

  @Autowired
  EverNoteWebService everNoteWebService;

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