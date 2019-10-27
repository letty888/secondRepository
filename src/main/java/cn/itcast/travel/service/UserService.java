package cn.itcast.travel.service;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;

public interface UserService {

    /**
     * 注册功能
     * @param user
     * @return
     */
    ResultInfo regist(User user);


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
    void updateStatus(int uid);

    /**
     * 登录功能
     * @param user
     * @return
     */
    ResultInfo login(User user);

    /**
     * 根据用户输入的用户名和密码在数据库中找对应的用户
     * @param user
     * @return
     */
    User findNameByUser(User user);
}
