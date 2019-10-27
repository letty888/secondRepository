package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 条件,分页查询功能中的查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    int findTotalCount(int cid, String rname);


    /**
     * 条件,分页查询功能中的查询具体信息
     * @param cid
     * @param rname
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findAllRouteList(int cid, String rname, int start, int pageSize);

    /**
     * 在tab_route表中,根据rid查出一条旅游线路
     * @param rid
     * @return
     */
    Route findOneRoute(int rid);
}
