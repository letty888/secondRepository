package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.RouteImgService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.service.impl.RouteImgServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import cn.itcast.travel.service.impl.SellerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    RouteService routeService = new RouteServiceImpl();
    SellerService sellerService = new SellerServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    RouteImgService routeImgService = new RouteImgServiceImpl();


    /**
     * 在数据库中查找所有旅游线路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");//旅游类别
        String rname = request.getParameter("rname");//用户在条件输入框中输入的搜索条件
        // rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        String currentPageStr = request.getParameter("currentPage");//当前页码
        String pageSizeStr = request.getParameter("pageSize");//每页显示的信息条数

        int cid = 0;//前台没有传递cid的话,说明用户直接在搜索框中进行的条件查询,则页面应该展示所有类别下的符合该条件的结果
        if (cidStr != null && !"".equals(cidStr) && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }


        //如果前端页面没传currentPage,则当前页面默认为1
        int currentPage = 0;
        if (currentPageStr != null && !"".equals(currentPageStr) && !"null".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        //如果前端页面没传pageSizeStr,则每页显示的条数默认为5条
        int pageSize = 0;
        if (pageSizeStr != null && !"".equals(pageSizeStr) && !"null".equals(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //调用service层
        PageBean<Route> pb = routeService.findRoute(cid, rname, currentPage, pageSize);
        writeValues(pb, response);


    }

    /**
     * 查找单个旅游线路的详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOneRouteDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        int rid = Integer.parseInt(ridStr);

        //在tab_route表中,根据rid查出一条旅游线路
        Route route = routeService.findOneRoute(rid);

        //在tab_seller表中根据sid查出此条旅游线路对应的商家
        Seller seller = sellerService.findSeller(route.getSid());
        route.setSeller(seller);

        //在tab_category表中,根据cid查出旅游类别
       Category category =  categoryService.findCategory(route.getCid());
       route.setCategory(category);

       //在tab_route_img表中根据rid查找该条线路对应的图片
       List<RouteImg> list = routeImgService.findRouteImgList(rid);
       route.setRouteImgList(list);



       writeValues(route,response);
    }
}
