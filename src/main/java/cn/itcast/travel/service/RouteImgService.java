package cn.itcast.travel.service;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgService {

    /**
     * 在tab_route_img表中根据rid查找该条线路对应的图片
     * @param rid
     * @return
     */
    List<RouteImg> findRouteImgList(int rid);
}
