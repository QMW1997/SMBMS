package cn.smbms.dao.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.smbms.pojo.User;
import cn.smbms.utils.MybatisUtils;



/**
 * 项目名称：SMBMS   
 * 类名称：UserMapperTest   
 * 类描述：   TODO
 * 创建人：Teacher 曾
 * 创建时间：2017-6-12 下午2:22:15     
 * @version V1.0
 */
public class UserMapperTest {
	private final Logger logger=Logger.
			getLogger(UserMapperTest.class);
	SqlSession sqlSession=null;
	@Test
	public void test1(){
		/*三步曲
		读取全局配置文件，创建sqlsessionfactory，
		创建sqlsession*/
		String resource="mybatis-config.xml";
		//读取配置文件
		SqlSessionFactory sqlSessionFactory=null;
		SqlSession sqlSession=null;
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			//创建sqlsessionfactory
			sqlSessionFactory=new 
					SqlSessionFactoryBuilder().build(is);
			//创建sqlsession
			sqlSession=sqlSessionFactory.openSession();
			//执行查询结果
			int count=sqlSession.selectOne
					("cn.smbms.dao.user.UserMapper.count");
			logger.debug("查询总结果为："+count);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public void test2(){
		try {
			//获取sqlsession
			sqlSession=MybatisUtils.openSession();
			//执行查询结果
			int count=sqlSession.selectOne
					("cn.smbms.dao.user.UserMapper.count");
			logger.debug("查询总结果为："+count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
		
	}
	
	@Test
	public void test3(){
		try {
			sqlSession=MybatisUtils.openSession();
			List<User> list=sqlSession.getMapper
					(UserMapper.class).selectList();
			for (User user : list) {
				logger.debug("员工姓名："+user.getUserName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}
	
	@Test
	public void test4(){
		String userName="赵";
		//创建sqlsession，执行sql，关闭sqlsession
		try {
			sqlSession=MybatisUtils.openSession();
			List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectByName(userName);
			for (User user : list) {
				logger.debug("用户名是："+user.getUserName()
						+"地址是："+user.getAddress());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}
	
	@Test
	public void test5(){
		User user=new User();
		user.setUserName("赵");
		user.setUserRole(2);
		try {
			sqlSession=MybatisUtils.openSession();
			List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUsers(user);
			for (User user2 : list) {
				logger.debug("用户名是："+user2.getUserName()
						+"地址是："+user2.getAddress()+"角色名称是："+
						user2.getUserRoleName()+user2.getCreationDate());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}
	
	@Test
	public void test6(){
		Map<String, String> userMap=new HashMap<String, String>();
		userMap.put("uName", "海");
		userMap.put("uRole", "3");
		try {
			sqlSession=MybatisUtils.openSession();
			List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectByUserMap(userMap);
			for (User user2 : list) {
				logger.debug("用户名是："+user2.getUserName()
						+"地址是："+user2.getAddress());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}

	@Test
	public void test7(){
		User user=new User();
		user.setUserName("哈哈");
		user.setUserPassword("haha");
		int count=0;
		try {
			sqlSession=MybatisUtils.openSession();
			count=sqlSession.getMapper(UserMapper.class)
					.addUser(user);
			/*sqlSession.commit();*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count=0;
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
		logger.debug("插入"+count+"行");
	}

	@Test
	public void test8(){
		String pwd="2213";
		int id=20;
		int count=0;
		try {
			sqlSession=MybatisUtils.openSession();
			count=sqlSession.getMapper(UserMapper.class)
					.modifyPwd(id,pwd);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count=0;
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
		logger.debug("修改"+count+"行");
	}

	@Test
	public void test9(){
		User user=new User();
		/*user.setUserName("赵");*/
		/*user.setUserRole(2);*/
		try {
			sqlSession=MybatisUtils.openSession();
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUserAll(user);*/
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUser2(user);*/
			List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUser3(user);
			for (User user2 : list) {
				logger.debug("用户名是："+user2.getUserName()
						+"地址是："+user2.getAddress()+user2.getCreationDate()
						+"角色名称:"+user2.getUserRoleName()
						);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}

	@Test
	public void test11(){
		User user=new User();
		user.setId(29);
		int count=0;
		/*user.setUserCode("hehe");*/
		user.setUserName("呵呵1");
		user.setUserPassword("123");
		user.setGender(1);
		user.setBirthday(new Date());
		try {
			sqlSession=MybatisUtils.openSession();
			count=sqlSession.getMapper(UserMapper.class)
					.modify1(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count=0;
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
		logger.debug("修改"+count+"行");
	}

	@Test
	public void test10(){
		Integer[] ints={1};
		List<Integer> list1=new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		try {
			sqlSession=MybatisUtils.openSession();
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUserAll(user);*/
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUser2(user);*/
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.select_foreach_array(list1);*/
			/*for (User user2 : list) {
				logger.debug("用户名是："+user2.getUserName()
						+"角色id是："+user2.getUserRole()
						);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}

	@Test
	public void test12(){
		Map<String, Object> maps=new HashMap<String, Object>();
		List<Integer> list1=new ArrayList<Integer>();
		Integer[] ints={2,3};
		list1.add(1);
		list1.add(2);
		maps.put("userName", "赵");
		maps.put("gender", 1);
		maps.put("map1", ints);
		try {
			sqlSession=MybatisUtils.openSession();
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUserAll(user);*/
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.selectUser2(user);*/
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.select_foreach_map(maps);*/
			/*List<User> list=sqlSession.getMapper(UserMapper.class)
					.select_foreach_array(ints);*/
		/*	for (User user2 : list) {
				logger.debug("用户名是："+user2.getUserName()
						+"角色id是："+user2.getUserRole()+
						"性别是"+user2.getGender()
						);
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}

	@Test
	public void test13(){
		User user=new User();
		user.setUserName("");
		user.setGender(null);
		user.setAddress("昌平区");
		user.setUserRole(1);
		sqlSession=MybatisUtils.openSession();
		List<User> list=sqlSession.getMapper(UserMapper.class)
		.select_choose(user);
		for (User user2 : list) {
			logger.debug("用户名是："+user2.getUserName()+
					"地址是："+user2.getAddress()
					+"角色id是："+user2.getUserRole()+
					"性别是"+user2.getGender()
					);
		}
	}

	@Test
	public void test14(){
		Integer cur=0;
		Integer size=5;
		User user=new User();
		user.setUserName("");
		/*user.setGender(null);
		user.setAddress("昌平区");*/
		/*user.setUserRole(1);*/
		try {
			sqlSession=MybatisUtils.openSession();
			List<User> list=sqlSession.getMapper(UserMapper.class)
			.select_limit(user.getUserName(), 
					user.getUserRole(),cur, size);
			for (User user2 : list) {
				logger.debug("id是====："+user2.getId()+
						"用户名是："+user2.getUserName()+
						"地址是："+user2.getAddress()
						+"角色id是："+user2.getUserRole()+
						"性别是"+user2.getGender()
						);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}

	@Test
	public void fun() {
		Integer cur = 0;
		Integer size = 2;
		User user = new User();
		user.setUserName("式");
		try {
			sqlSession = MybatisUtils.openSession();
			List<User> list = sqlSession.getMapper(UserMapper.class)
					.selectUsers(user.getUserName(), cur, size);
			for (User user2 : list) {
				logger.debug("id是====：" + user2.getId() + "用户名是："
						+ user2.getUserName() + "地址是：" + user2.getAddress()
						+ "角色id是：" + user2.getUserRole() + "性别是"
						+ user2.getGender());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MybatisUtils.closeSession(sqlSession);
		}

	}

}
