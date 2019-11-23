package top.ilovestudy.yinxiang.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
@ConfigurationProperties(prefix = "yinxiang")
public class EverNoteProperties {
  @NotBlank
  private String devToken;
  @NotBlank
  private String noteStoreUrl;
  private String[] filterNoteBooks;
  private String[] filterNotes;
}
