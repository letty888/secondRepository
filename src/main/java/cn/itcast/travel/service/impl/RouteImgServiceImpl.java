package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.service.RouteImgService;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteImgServiceImpl implements RouteImgService {

    RouteImgDao routeImgDao = new RouteImgDaoImpl();

    /**
     * 在tab_route_img表中根据rid查找该条线路对应的图片
     *
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findRouteImgList(int rid) {
        List<RouteImg> list = routeImgDao.findRouteImgListByRid(rid);
        return list;
    }
}
