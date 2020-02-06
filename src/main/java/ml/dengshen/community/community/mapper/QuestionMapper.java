package ml.dengshen.community.community.mapper;

import ml.dengshen.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description,gmt_create, gmt_modify, creator, comment_count, view_count, like_count, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    void create(Question question);
//    SELECT * FROM question LIMIT 0,5
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}
