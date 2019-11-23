package top.ilovestudy.yinxiang.model.mapper;

import com.evernote.edam.type.Notebook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.ilovestudy.yinxiang.model.CategoryDto;
import top.ilovestudy.yinxiang.model.entites.Category;


@Mapper
public interface CategoryMapper {
  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Mapping(source = "guid", target = "url")
  @Mapping(expression = "java(notebook.getSharedNotebooksSize())", target = "number")
  CategoryDto noteBookToCategoryDto(Notebook notebook);

  @Mapping(source = "id", target = "url")
  CategoryDto categoryToCategoryDto(Category category);
}
