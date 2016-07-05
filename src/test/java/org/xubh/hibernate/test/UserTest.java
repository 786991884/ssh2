package org.xubh.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.xubh.hibernate.model.User;
import org.xubh.hibernate.util.HibernateUtil;

public class UserTest {
	public static void main(String[] args) {
		User user = new User("meng", 18);
		/**
		 * 加载解析配置文件，无参数则找 hibernate.cfg.xml .创建session工厂
		 * */
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// 保存数据
		session.save(user);
		session.getTransaction().commit();
		// 关闭session
		session.close();
		// 关闭工厂
		sessionFactory.close();
	}
}
