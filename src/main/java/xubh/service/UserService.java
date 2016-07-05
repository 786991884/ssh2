package xubh.service;

import java.util.List;

import xubh.model.User;
import xubh.util.PageBean;

public interface UserService {
	List<User> findAllUsers();

	User findAllById(int id);

	void update(User user);

	void deleteUserById(int id);

	void save(User user);

	PageBean getPageBean(int pageNum);
}
