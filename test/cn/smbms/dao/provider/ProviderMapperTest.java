package cn.smbms.dao.provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
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
public class ProviderMapperTest {
	private final Logger logger=Logger.
			getLogger(ProviderMapperTest.class);
	SqlSession sqlSession=null;
	
	@Test
	public void test1(){
		int id=14;
		sqlSession=MybatisUtils.openSession();
		List<Provider> list=sqlSession.getMapper(ProviderMapper.class)
				.selectProvider(id);
		for (Provider provider : list) {
			List<Bill> bills=provider.getBills();
			for (Bill bill : bills) {
				logger.debug(provider.getProName()+"\t"+
						bill.getProductName());
			}
		}
	}
}
