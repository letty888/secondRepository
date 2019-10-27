package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();
    ResultInfo info = new ResultInfo();
    /**
     * 注册功能
     * @param user
     * @return
     */
    @Override
    public ResultInfo regist(User user) {
        User u = userDao.findUserByUsername(user.getUsername());
        if(u != null){
            //说明该用户名已经在数据库中存在
            info.setFlag(false);
            info.setErrorMsg("该用户名太受欢迎,请重新设置的您的用户名!");
        }else{
            //说明该用户名在数据库中不存在,可以注册
            info.setFlag(true);
            //调用userdao层中的addUser方法,将该用户的信息保存到数据库中
            user.setStatus("N");//激活状态初始化为N
            user.setCode(UuidUtil.getUuid());//UUID唯一码
            userDao.addUser(user);
            //给该用户发邮件
            String content = "<a href = 'user/active?code="+user.getCode()+"'>点我激活</a>";
            MailUtils.sendMail(user.getEmail(),content,"邮件激活");
        }
        return info;
    }


    /**
     * 激活功能中的:根据code码在数据库中查找对应用户
     * @param code
     * @return
     */
    @Override
    public User findUserByCode(String code) {
       User user = userDao.findUserByCode(code);
         return user;
    }


    /**
     * 激活功能中的更改用户状态
     * @param uid
     */
    @Override
    public void updateStatus(int uid) {
        userDao.updateSatatus(uid);
    }


    /**
     * 登录功能
     * @param user
     * @return
     */
    @Override
    public ResultInfo login(User user) {
        User u = userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(u == null){
            //说明用户输入的用户名错误
            info.setFlag(false);
            info.setErrorMsg("您输入的用户名有误,请重新输入!");
        }else{
            //说明用户输入的用户名是正确的
            if(u.getUsername() == "0"){
                //说明用户输入的用户名正确但密码错误
                info.setFlag(false);
                info.setErrorMsg("您输入的密码有误,请重新输入!");
                return info;
            }
            //说明用户输入的用户名和密码都是正确的,可以登录
            info.setFlag(true);

        }
        return info;
    }


    /**
     * 根据用户输入的用户名和密码在数据库中找对应的用户
     * @param user
     * @return
     */
    @Override
    public User findNameByUser(User user) {
        User u = userDao.findUserByUser(user.getUsername(), user.getPassword());
        return u;
    }


}
