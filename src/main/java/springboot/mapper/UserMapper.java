package springboot.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import springboot.model.User;

@Repository
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
    
    List<User> getUsers();
}