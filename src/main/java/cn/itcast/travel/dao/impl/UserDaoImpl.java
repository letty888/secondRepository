package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 注册功能中:根据用户输入的用户名在数据库中查找相同用户名的用户
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        User user = null;
        try {
            //定义sql
            String sql = "select * from tab_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {

        }
        return user;
    }


    /**
     * 将用户信息添加到数据库中
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
    }

    /**
     * 激活功能中的:根据code码在数据库中查找对应用户
     *
     * @param code
     * @return
     */
    @Override
    public User findUserByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (DataAccessException e) {

        }
        return user;
    }


    /**
     * 激活功能中的更改用户状态
     *
     * @param uid
     */
    @Override
    public void updateSatatus(int uid) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql, uid);
    }


    /**
     * 登录功能中的根据用户名和(密码)在数据库中查找对应用户:主要是根据用户名查
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //定义sql
            String sql = "select * from tab_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
            if (!password.equals(user.getPassword())) {
                user.setUsername("0");//标记为账号正确但密码错误
            }
        } catch (DataAccessException e) {
        }
        return user;
    }


    /**
     * 登录功能中的根据用户名和密码在数据库中查找对应用户:根据用户名和密码一起查
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUser(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
