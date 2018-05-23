package springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import springboot.model.User;

@Service
public interface UserService {
    int insert(User record);

    int insertSelective(User record);
    
    List<User> getUsers();
}
