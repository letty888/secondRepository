package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 在tab_route_img表中根据rid查找该条线路对应的图片
     * @param rid
     * @return
     */
    List<RouteImg> findRouteImgListByRid(int rid);
}
