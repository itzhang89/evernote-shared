package top.ilovestudy.yinxiang.services;

import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.Tag;
import com.evernote.thrift.TException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.ilovestudy.yinxiang.config.EverNoteProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.evernote.edam.limits.Constants.EDAM_USER_NOTES_MAX;

@Service
public class EverNoteWebService {

  private NoteStoreClient noteStore;

  private EverNoteProperties everNoteProperties;

  public EverNoteWebService(NoteStoreClient noteStore, EverNoteProperties everNoteProperties) {
    this.noteStore = noteStore;
    this.everNoteProperties = everNoteProperties;
  }

  List<Notebook> getNoteBooks() throws TException, EDAMUserException, EDAMSystemException {
    return noteStore.listNotebooks();
  }

  Notebook getNoteBookById(String guid) throws TException, EDAMUserException, EDAMSystemException, EDAMNotFoundException {
    return noteStore.getNotebook(guid);
  }


  List<Tag> getTags() throws TException, EDAMUserException, EDAMSystemException {
    return noteStore.listTags();
  }

  Tag getTagById(String guid) throws TException, EDAMUserException, EDAMSystemException, EDAMNotFoundException {
    return noteStore.getTag(guid);
  }

  List<Note> getAllNotes() throws TException, EDAMUserException, EDAMSystemException, EDAMNotFoundException {
    List<Note> allNotes = new ArrayList<>();

    List<Notebook> filteredNoteBooks = getFilteredNoteBooks();

    for (Notebook notebook : filteredNoteBooks) {
      NoteFilter filter = getNoteSortOrderByCreateNoteFilter(notebook);

      NoteList noteList = noteStore.findNotes(filter, 0, EDAM_USER_NOTES_MAX);
      List<Note> notes = getFilteredNotes(noteList);

      allNotes.addAll(notes);
    }
    return allNotes;
  }

  List<Note> getFilteredNotes(NoteList noteList) {
    return noteList.getNotes().stream().filter(note -> !isFiltered(note)).collect(Collectors.toList());
  }

  List<Notebook> getFilteredNoteBooks() throws EDAMUserException, EDAMSystemException, TException {
    return noteStore.listNotebooks().stream().filter(notebook -> !isFiltered(notebook)).collect(Collectors.toList());
  }

  private boolean isFiltered(Note note) {
    // todo for how to use the matches method rightly
    return Arrays.stream(everNoteProperties.getFilterNotes())
        .anyMatch(regx -> !StringUtils.isEmpty(regx) && note.getTitle().matches(regx));
  }

  private boolean isFiltered(Notebook notebook) {
    return Arrays.stream(everNoteProperties.getFilterNoteBooks()).anyMatch(regx -> !StringUtils.isEmpty(regx) && notebook.getName().matches(regx));
  }

  private NoteFilter getNoteSortOrderByCreateNoteFilter(Notebook notebook) {
    NoteFilter filter = new NoteFilter();
    filter.setNotebookGuid(notebook.getGuid());
    filter.setOrder(NoteSortOrder.CREATED.getValue());
    filter.setAscending(true);
    return filter;
  }

}
