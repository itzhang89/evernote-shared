package top.ilovestudy.yinxiang.controller;

import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ilovestudy.yinxiang.model.ArchiveDto;
import top.ilovestudy.yinxiang.model.ArticleDto;
import top.ilovestudy.yinxiang.model.CategoryDto;
import top.ilovestudy.yinxiang.model.LabelDto;
import top.ilovestudy.yinxiang.services.ArticleService;

import java.util.List;

@Controller
public class BlogController {

  @Autowired
  private ArticleService articleService;

  @RequestMapping("/")
  public String root() {
    return "index";
  }

  @RequestMapping("/index")
  public String index(ModelMap modelMap) throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<ArticleDto> articleDtos = articleService.findSharedArticleDtoList();
    List<CategoryDto> categories = articleService.getCategoriesFromNotebooks();
    List<ArticleDto> popularArticleDtos = articleService.getPopularArticles();
    List<LabelDto> cloudLabelDtos = articleService.getCloudTags();
    List<ArchiveDto> archives = articleService.getArchives();
    modelMap.addAttribute("articles", articleDtos);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("popularArticles", popularArticleDtos);
    modelMap.addAttribute("cloudTags", cloudLabelDtos);
    modelMap.addAttribute("archives", archives);
    return "index";
  }

  @RequestMapping("/fashion")
  public String fashion() {
    return "fashion";
  }

  @RequestMapping("/travel")
  public String travel() {
    return "travel";
  }

  @RequestMapping("/contact")
  public String contact() {
    return "contact";
  }

  @RequestMapping("/single")
  public String single() {
    return "single";
  }

  @RequestMapping("/single/{id}")
  public String singleTemplate(@PathVariable String id, ModelMap modelMap) {
    ArticleDto articleDtoById = articleService.findArticleDtoById(id);
    modelMap.addAttribute("article", articleDtoById);
    return "single";
  }

  @RequestMapping("/about")
  public String about() {
    return "about";
  }
}
