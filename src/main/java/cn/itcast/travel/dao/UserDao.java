package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    /**
     * 注册功能中:根据用户输入的用户名在数据库中查找相同用户名的用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 将用户信息添加到数据库中
     * @param user
     */
    void addUser(User user);

    /**
     * 激活功能中的:根据code码在数据库中查找对应用户
     * @param code
     * @return
     */
    User findUserByCode(String code);

    /**
     * 激活功能中的更改用户状态
     * @param uid
     */
    void updateSatatus(int uid);

    /**
     * 登录功能中的根据用户名和(密码)在数据库中查找对应用户:主要是根据用户名查
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);


    /**
     * 登录功能中的根据用户名和密码在数据库中查找对应用户:根据用户名和密码一起查
     * @param username
     * @param password
     * @return
     */
    User findUserByUser(String username, String password);
}
