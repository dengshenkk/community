package ml.dengshen.community.community.mapper;

import ml.dengshen.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, avatar_url, gmt_create, gmt_modify) values (#{name}, #{accountId}, #{token}, #{avatarUrl}, #{gmtCreate}, #{gmtModify})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}
