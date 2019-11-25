package top.ilovestudy.yinxiang.controller;

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
  public String index(ModelMap modelMap) {
    List<ArticleDto> articleDtoList = articleService.findSharedArticleDtoList();
    List<CategoryDto> categories = articleService.findCategoryDtoList();
    List<ArticleDto> popularArticleDtoList = articleService.getPopularArticles();
    List<LabelDto> cloudLabelDtoList = articleService.findCloudLabelList();
    List<ArchiveDto> archives = articleService.findArchives();
    modelMap.addAttribute("articles", articleDtoList);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("popularArticles", popularArticleDtoList);
    modelMap.addAttribute("cloudTags", cloudLabelDtoList);
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
    List<CategoryDto> categories = articleService.findCategoryDtoList();
    List<LabelDto> cloudLabelDtoList = articleService.findCloudLabelList();
    List<LabelDto> cloudLabelListByArticleId = articleService.findCloudLabelListByArticleId(id);
    List<ArchiveDto> archives = articleService.findArchives();
    modelMap.addAttribute("article", articleDtoById);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("articleLabels", cloudLabelListByArticleId);
    modelMap.addAttribute("cloudTags", cloudLabelDtoList);
    modelMap.addAttribute("archives", archives);
    return "single";
  }

  @RequestMapping("/about")
  public String about() {
    return "about";
  }
}
