package org.xubh.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xubh.hibernate.model.Apple;
import org.xubh.hibernate.util.HibernateUtil;

public class CoreApiTest {
	private static SessionFactory sessionFactory = null;
	
	@BeforeClass
	public static void beforeClass(){
		sessionFactory=HibernateUtil.getSessionFactory();
	}

	@Test
	public void saveApple(){
		Apple apple = new Apple(1,"xing",11);
		/**
		 * openSession():使用session工厂创建一个新的session
		 *
		 * getCurrentSession():从上下文中获取一个session，获取不到，创建一个，使用它，不需要session.close();
		 * 		该方法可用于界定事物边界
		 * 	上下文：<property name="current_session_context_class">参数</property>指定的范围
		 * 	参数：jta | thread | managed | custom.Class
		 * 	thread:当前线程中(常用)
		 * 	managed:application(手工管理事物的时候)
		 * 	custom.Class:自定义的Class来管理session
		 * 	取值jta:(java transaction api)
    * 它一般用于管理分布式事物，当然也可以管理非分布式事物，但没有必要。
    * 当需要往多个数据库服务器中插入数据时，使用数据库的Connection无法保证其中一个操作没有完成，其他数据库操作也回滚，
    * 这个时候我们需要jta来管理事物，它可以记录各个数据库的操作，在必要的时候回 * 滚所有数据库服务器的操作，它需要特定的驱动XA driver；
    * 而这种事物管理的能力通常由我们的中间件（应用服务器）来管理，比如Jboss，Tomcat的话还需要借助像Spring这样的第三方类库来实现
		 *
		 **/
		Session session = sessionFactory.openSession();
		//Session session3 = sessionFactory.openSession();
		//System.out.println("session == session3:"+(session == session3));//false

		//Session session = sessionFactory.getCurrentSession();
		//Session session2 = sessionFactory.getCurrentSession();
		//System.out.println("session == session2:"+(session == session2));//true
		session.beginTransaction();
		session.save(apple);
		session.getTransaction().commit();
		/**
		 * commit之后，session关闭了
		 */
		//Session session4 = sessionFactory.getCurrentSession();
		//System.out.println("session == session4:"+(session == session2));//false
		session.close();
	}

	@AfterClass
	public static void afterClass(){
		sessionFactory.close();
	}

}
