package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 条件,分页查询功能中的查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        //定义sql模板
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List parameters = new ArrayList();

        if(cid != 0){
            sb.append(" and cid = ? ");
            parameters.add(cid);
        }

        if(rname != null && !"".equals(rname) && !"null".equals(rname)){
            sb.append(" and rname like ? ");
            parameters.add("%"+rname+"%");
        }
        Integer totalCount = template.queryForObject(sb.toString(), Integer.class, parameters.toArray());
        return totalCount;
    }


    /**
     * 条件,分页查询功能中的查询具体信息
     * @param cid
     * @param rname
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findAllRouteList(int cid, String rname, int start, int pageSize) {
        //定义sql模板
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List parameters = new ArrayList();
        if(cid != 0){
            sb.append(" and cid = ? ");
            parameters.add(cid);
        }

        if(rname != null && !"".equals(rname) && !"null".equals(rname)){
            sb.append(" and rname like ?");
            parameters.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");
        parameters.add(start);
        parameters.add(pageSize);
        List<Route> list = template.query(sb.toString(), new BeanPropertyRowMapper<>(Route.class), parameters.toArray());
        return list;
    }


    /**
     * 在tab_route表中,根据rid查出一条旅游线路
     * @param rid
     * @return
     */
    @Override
    public Route findOneRoute(int rid) {
        Route route = null;
        try {
            String sql = "select * from tab_route where rid = ?";
            route = template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return  route;
    }
}
