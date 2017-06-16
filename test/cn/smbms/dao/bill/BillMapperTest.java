package cn.smbms.dao.bill;

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
public class BillMapperTest {
	private final Logger logger=Logger.
			getLogger(BillMapperTest.class);
	SqlSession sqlSession=null;
	
	@Test
	public void test1(){
		String productName="油";
		try {
			sqlSession=MybatisUtils.openSession();
			List<Bill> list=sqlSession.getMapper(BillMapper.class)
					.selectBill(productName);
			for (Bill bill : list) {
				logger.debug(bill.getBillCode()+"\t"+
						bill.getProductName()+"\t"+
						bill.getProductUnit()+"\t"+
						bill.getProvider().getProCode()+"\t"+
						bill.getProvider().getProName()
						);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MybatisUtils.closeSession(sqlSession);
		}
	}
}
