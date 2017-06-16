package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

/**
 * 项目名称：SMBMS   
 * 类名称：UserMapper   
 * 类描述：   TODO
 * 创建人：Teacher 曾
 * 创建时间：2017-6-12 下午3:38:07     
 * @version V1.0
 */
public interface UserMapper {
	/**
	 * 查询所有用户信息
	 */
	public List<User> selectList();
	
	/**
	 * 模糊查询所有用户
	 */
	public List<User> selectByName(String userName);
	
	/**
	 * 多条件查询
	 */
	public List<User> selectByUser(User user);
	
	/**
	 * 多条件查询map
	 */
	public List<User> selectByUserMap(Map<String, String> userMap);
	
	/**
	 * 多条件查询不用resultmap
	 */
	public List<User> selectUsers(User user);
	
	/**
	 * 插入一个用户
	 */
	public int addUser(User user);
	
	/**
	 * 修改一个用户
	 */
	public int modifyPwd(@Param("userPassword")Integer id,
			@Param("id")String userPassword);
	
	/**
	 * 查询，使用resultmap，对应association
	 */
	public List<User> selectUserAll(User user);
	
	/**
	 * 动态sql的if使用
	 */
	public List<User> selectUser2(User user);
	
	/**
	 * 动态sql的if+TRIM的使用
	 */
	public List<User> selectUser3(User user);
	
	/**
	 * 动态sql的if+trim的修改操作
	 */
	public int modify1(User user);
	
	/**
	 * 动态sql的foreach的数组
	 */
	public List<User> select_foreach_array(Integer[] roleId);
	
	
	public List<User> select_foreach_map(Map<String, Object> maps1);

	public List<User> select_choose(User user);
	
	public List<User> select_limit(@Param("userName") String userName,
			@Param("userRole") Integer userRole,
			@Param("currentPage") Integer cur,
			@Param("pageSize") Integer size);
	
	
	public List<User> selectUsers(@Param("userName") String userName,
	@Param("currentPage") Integer cur,
	@Param("pageSize") Integer size
	);
}
