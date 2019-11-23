package top.ilovestudy.yinxiang.utils;

import com.evernote.edam.type.Note;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import top.ilovestudy.yinxiang.model.entites.Article;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonUtils {

  private volatile static ObjectMapper instance;

  public static List<Note> readNoteListFromJsonFile(String path) {
    try {
      return getInstance().readValue(
          new ClassPathResource(path).getFile(),
          new TypeReference<List<Note>>() {
          });
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return null;
  }

  public static List<Article> readObjectListFromJsonFile(String path) {
    try {
      return getInstance().readValue(
          new ClassPathResource(path).getFile(),
          new TypeReference<List<Article>>() {
          });
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return null;
  }

  public static <T> T readJsonFileToObject(String path, Class<T> type) {
    try {
      return getInstance().readValue(new ClassPathResource(path).getFile(), type);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return null;
  }

  public static ObjectMapper getInstance() {
    if (instance == null) {
      synchronized (ObjectMapper.class) {
        if (instance == null) {
          instance = new ObjectMapper();
          instance.registerModule(new JavaTimeModule());
        }
      }
    }
    return instance;
  }
}
