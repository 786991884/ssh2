package xubh.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
public class TestDao {

	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	public void test() {
//		List<Map> l = userDao.findBySql("select t.DESCRIPTION dddd from syresource t");
//		System.out.println(JSON.toJSONString(l));
//
//		List<Map> l2 = userDao.findBySql("select t.* from syresource t");
//		System.out.println(JSON.toJSONString(l2));
	}

}
