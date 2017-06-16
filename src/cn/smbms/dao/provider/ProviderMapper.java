package cn.smbms.dao.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

/**
 * 项目名称：SMBMS   
 * 类名称：UserMapper   
 * 类描述：   TODO
 * 创建人：Teacher 曾
 * 创建时间：2017-6-12 下午3:38:07     
 * @version V1.0
 */
public interface ProviderMapper {
	/**
	 * 查询所有信息
	 * @return 
	 */
	public List<Provider> selectProvider(Integer id);
	
}
