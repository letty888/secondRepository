package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 在tab_route_img表中根据rid查找该条线路对应的图片
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findRouteImgListByRid(int rid) {
        List<RouteImg> list = null;
        try {
            String sql = "select * from tab_route_img where rid = ? ";
            list = template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list ;
    }
}
