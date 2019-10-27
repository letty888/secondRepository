package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据rid和uid和uid在tab_favorite表中查找对应的favorite对象
     * @param rid
     * @param
     * @return
     */
    @Override
    public Favorite findFavorite(int rid,int uid) {
        Favorite favorite=null;
        try {
            //定义sql
            String sql="select * from tab_favorite where rid = ? and uid = ? ";
            //执行sql
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid,uid );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }


    /**
     * 添加收藏
     * @param rid
     * @param date
     * @param uid
     */
    @Override
    public void addFavorite(int rid, Date date, int uid) {
        //定义sql
        String sql = "insert into tab_favorite values(rid,date,uid)";
        //执行sql
        template.update(sql,rid,date,uid);
    }


    /**
     *  根据rid在tab_favorite表中查找对应的收藏次数
     * @param rid
     * @return
     */
    @Override
    public int findcount(int rid) {
        int count = 0;
        try {
            //定义sql
            String sql = "select count(*) from tab_favorite where rid = ? ";
            //执行sql
             count = template.queryForObject(sql, Integer.class, rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return count;
    }
}
