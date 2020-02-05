package ml.dengshen.community.community.mapper;

import ml.dengshen.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description,gmt_create, gmt_modify, creator, comment_count, view_count, like_count, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
