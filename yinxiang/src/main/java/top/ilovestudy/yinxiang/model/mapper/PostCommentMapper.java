package top.ilovestudy.yinxiang.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.ilovestudy.yinxiang.model.PostCommentDto;
import top.ilovestudy.yinxiang.model.entites.PostComment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static top.ilovestudy.yinxiang.utils.DateUtils.COMMEENT_DATE_TIME_FORMAT;

@Mapper
public interface PostCommentMapper {

  PostCommentMapper INSTANCE = Mappers.getMapper(PostCommentMapper.class);

  @Mapping(target = "childList", ignore = true)
  @Mapping(target = "commentTime", source = "commentTime", dateFormat = COMMEENT_DATE_TIME_FORMAT)
  @Mapping(target = "replyTo", source = "parentPostComment.username")
  PostCommentDto postCommentToPostCommentDto(PostComment postComment);

  List<PostCommentDto> postCommentToPostCommentDto(List<PostComment> postCommentList);

  default List<PostCommentDto> postCommentListToPostCommentDtoList(List<PostComment> postCommentList) {
    Map<Long, List<PostComment>> longAndPostCommentListMap = postCommentList.stream().collect(Collectors.groupingBy(PostComment::getBelongId));

    Map<Long, List<PostCommentDto>> longAndPostCommentDtoListMap = longAndPostCommentListMap.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey,
            longListEntry -> INSTANCE.postCommentToPostCommentDto(longListEntry.getValue())));

    List<PostCommentDto> rootPostCommentDtoList = longAndPostCommentDtoListMap.get(0L);
    if (rootPostCommentDtoList != null) {
      for (PostCommentDto rootPostCommentDto : rootPostCommentDtoList) {
        List<PostCommentDto> childPostCommentDtoList = longAndPostCommentDtoListMap.get(rootPostCommentDto.getId());
        rootPostCommentDto.setChildList(childPostCommentDtoList);
      }
    }

    return rootPostCommentDtoList;
  }
}
