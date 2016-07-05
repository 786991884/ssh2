package org.xubh.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xubh.hibernate.model.Event;

public class EventTest {
	public static void main(String[] args) {
		Event event = new Event(1L, "hellokitty", new Date());
//		Configuration configuration = new AnnotationConfiguration();
		Configuration configuration = new Configuration();
		/**
		 * 加载解析配置文件，无参数则找 hibernate.cfg.xml .创建session工厂
		 * */
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// 保存数据
		session.save(event);
		session.getTransaction().commit();
		// 关闭session
		session.close();
		// 关闭工厂
		sessionFactory.close();
	}

	private static SessionFactory sessionFactory = null;

	@BeforeClass
	public static void beforeClass() {
		Configuration configuration = new Configuration();
		sessionFactory = configuration.configure().buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());
	}

	@Test
	public void saveEvent() {
		Event event = new Event(6L, "hellokitty", new Date());
		/**
		 * session可以看做是Connection
		 * */
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(event);
		session.getTransaction().commit();
		session.close();
	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}
}
