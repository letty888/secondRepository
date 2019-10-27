package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {

    /**
     * 分页,条件组合查询
     * @param cid
     * @param rname
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Route> findRoute(int cid, String rname, int currentPage, int pageSize);

    /**
     * 根据rid查询旅游线路
     * @param rid
     * @return
     */
    Route findOneRoute(int rid);
}
