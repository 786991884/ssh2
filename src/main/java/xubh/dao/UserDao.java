package xubh.dao;

import java.util.List;

import xubh.model.User;
import xubh.util.PageBean;

public interface UserDao {
	List<User> findAllUsers();

	User findUserById(int id);

	void save(User user);

	void deleteUserById(int id);

	void update(User user);

	/**
	 * 
	 * @param pageNum
	 * @param hql
	 * @return
	 */
	PageBean getPageBean(int pageNum, String hql);

	/**
	 * 公共的查询分页信息的方法
	 * 
	 * @param pageNum
	 * @param hql
	 *            查询数据列表的HQL语句，如果在前面加上“select count(*) ”就变成了查询总数量的HQL语句了
	 * 
	 * @param parameters
	 *            参数列表，顺序与HQL中的'?'的顺序一一对应。
	 * @return
	 */
	PageBean getPageBean(int pageNum, String hql, Object[] parameters);

}
