package top.ilovestudy.yinxiang.model.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.ilovestudy.yinxiang.model.PostCommentDto;
import top.ilovestudy.yinxiang.model.entites.PostComment;
import top.ilovestudy.yinxiang.utils.JsonUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class PostCommentMapperTest {

  private List<PostComment> postComments;

  @BeforeEach
  void setUp() {
    postComments = JsonUtils.readPostCommentListFromJsonFile("post_comment_list.json");
  }

  @Test
  void shouldConvertOnePostCommentToOnePostCommentDto() {
    PostComment oneSingleComment = postComments.get(0);

    PostCommentDto postCommentDto = PostCommentMapper.INSTANCE.postCommentToPostCommentDto(oneSingleComment);

    assertEquals(1, postCommentDto.getId().longValue());
    assertEquals(1, postCommentDto.getLikes().longValue());
    assertEquals(0, postCommentDto.getDisLikes().longValue());
    assertEquals("John Doe", postCommentDto.getUsername());
    assertNull(postCommentDto.getReplyTo());
    assertEquals("Dec 03, 2019 14:21PM", postCommentDto.getCommentTime());
    assertEquals("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem\n" +
        "            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat\n" +
        "            saepe enim sapiente iste iure! Quam voluptas earum impedit\n" +
        "            necessitatibus, nihil?", postCommentDto.getContent());
    assertNull(postCommentDto.getChildList());
  }

  @Test
  void shouldConvertPostCommentListToPostCommentDtoListAndGroupByRootPostCommentList() {
    List<PostCommentDto> postCommentDtoList = PostCommentMapper.INSTANCE.postCommentListToPostCommentDtoList(postComments);

    assertEquals(3, postCommentDtoList.size());
    assertNull(postCommentDtoList.get(0).getChildList());
    assertEquals(5, postCommentDtoList.get(1).getChildList().size());
    assertNull(postCommentDtoList.get(2).getChildList());
  }

  @Test
  void shouldSortedAllChildListByList() {
    List<PostCommentDto> postCommentDtoList = PostCommentMapper.INSTANCE.postCommentListToPostCommentDtoList(postComments);
    PostCommentDto postCommentDto = postCommentDtoList.get(1);

    assertEquals(2, postCommentDto.getId().longValue());
    List<PostCommentDto> childList = postCommentDto.getChildList();
    for (int i = 0; i < childList.size(); i++) {
      assertEquals(i + 3, childList.get(i).getId().intValue());
    }
  }
}