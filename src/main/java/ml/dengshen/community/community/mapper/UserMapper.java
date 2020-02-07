package ml.dengshen.community.community.mapper;

import ml.dengshen.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, avatar_url, gmt_create, gmt_modify) values (#{name}, #{accountId}, #{token}, #{avatarUrl}, #{gmtCreate}, #{gmtModify})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modify = #{gmtModify}, avatar_url = #{avatarUrl} where account_id = #{accountId}")
    void update(User user);
}