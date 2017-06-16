package cn.smbms.utils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 项目名称：SMBMS   
 * 类名称：MybatisUtils   
 * 类描述：   TODO
 * 创建人：Teacher 曾
 * 创建时间：2017-6-16 下午8:28:06     
 * @version V1.0
 */
public class MybatisUtils {
	private static SqlSessionFactory sqlSessionFactory;
	static{
		String str="mybatis-config.xml";
		InputStream is;
		try {
			is=Resources.getResourceAsStream(str);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession(){
		return sqlSessionFactory.openSession();
	}
	public static void closeSession(SqlSession sqlSession){
		if (sqlSession!=null) {
			sqlSession.close();
		}
	}
}
