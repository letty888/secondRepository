package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

public class RouteServiceImpl implements RouteService {

    RouteDao routeDao = new RouteDaoImpl();
    PageBean<Route> pb = new PageBean<>();

    /**
     * 分页,条件组合查询
     *
     * @param cid
     * @param rname
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> findRoute(int cid, String rname, int currentPage, int pageSize) {

        int totalCount = routeDao.findTotalCount(cid, rname);//条件查询的总记录数
        pb.setTotalCount(totalCount);

        pb.setCurrentPage(currentPage);//当前页面
        pb.setPageSize(pageSize);//每页显示的条数

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);//总页数

        int start = (currentPage-1)*pageSize;
        List<Route> list = routeDao.findAllRouteList(cid,rname,start,pageSize);
        pb.setList(list);//每页显示的具体信息

        return pb;
    }

    /**
     * 在tab_route表中,根据rid查出一条旅游线路
     * @param rid
     * @return
     */
    @Override
    public Route findOneRoute(int rid) {
       Route route =  routeDao.findOneRoute(rid);
        return route;
    }


}
