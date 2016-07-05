package xubh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xubh.dao.UserDao;
import xubh.model.User;
import xubh.util.PageBean;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> findAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = this.sessionFactory.getCurrentSession().createQuery("from User").list();
		return users;
	}

	public User findUserById(int id) {
		return (User) this.sessionFactory.getCurrentSession().get(User.class, id);
	}

	public void save(User user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	public void deleteUserById(int id) {
		this.sessionFactory.getCurrentSession().delete(findUserById(id));

	}

	public void update(User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}

	public PageBean getPageBean(final int pageNum, final String hql) {
		final int pageSize = 2;

		// 查询本页的数据列表
		Query qlist = this.sessionFactory.getCurrentSession().createQuery(hql);
		qlist.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		@SuppressWarnings("rawtypes")
		List list = qlist.list();// 执行查询
		// 查询总记录数
		Query countQuery = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) " + hql);
		Long count = (Long) countQuery.uniqueResult(); // 执行查询
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}

	public PageBean getPageBean(int pageNum, String hql, Object[] parameters) {
		int pageSize = 2;
		// 查询本页的数据列表
		Query listQuery = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (parameters != null && parameters.length > 0) { // 设置参数
			for (int i = 0; i < parameters.length; i++) {
				listQuery.setParameter(i, parameters[i]);
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		@SuppressWarnings("rawtypes")
		List list = listQuery.list(); // 执行查询

		// 查询总记录数
		Query countQuery = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) " + hql);
		if (parameters != null && parameters.length > 0) { // 设置参数
			for (int i = 0; i < parameters.length; i++) {
				countQuery.setParameter(i, parameters[i]);
			}
		}
		Long count = (Long) countQuery.uniqueResult(); // 执行查询

		return new PageBean(pageNum, pageSize, list, count.intValue());
	}

}
