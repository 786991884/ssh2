package xubh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xubh.dao.UserDao;
import xubh.model.User;
import xubh.service.UserService;
import xubh.util.PageBean;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public User findAllById(int id) {
		return userDao.findUserById(id);
	}

	public void update(User user) {
		userDao.update(user);

	}

	public void deleteUserById(int id) {
		userDao.deleteUserById(id);

	}

	public void save(User user) {
		userDao.save(user);

	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public PageBean getPageBean(int pageNum) {
		return userDao.getPageBean(pageNum, "from User u");
	}
}
