package springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.UserMapper;
import springboot.model.User;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public int insert(User record) {
		return usermapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return usermapper.insertSelective(record);
	}

	@Override
	public List<User> getUsers() {
		return usermapper.getUsers();
	}
}
