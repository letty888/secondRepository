package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {


    FavoriteService favoriteService = new FavoriteServiceImpl();
    ResultInfo info = new ResultInfo();

    /**
     * 判断该用户是否已经收藏了该条旅游线路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ridStr = request.getParameter("rid");
        int rid = Integer.parseInt(ridStr);
        User user = (User) request.getSession().getAttribute("user");
       /* String uidStr = request.getParameter("uid");
        int uid = Integer.parseInt(uidStr);*/
       /* int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }*/
        //根据rid和uid在tab_favorite表中查找对应的favorite对象
        boolean flag = favoriteService.isFavorite(rid, user.getUid());
        writeValues(flag, response);
    }

    /**
     * 添加收藏功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ridStr = request.getParameter("rid");
        int rid = Integer.parseInt(ridStr);


        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            info.setFlag(false);//标记为用户未登录
            writeValues(info, response);
            return;
        }
        //调用service层中的方法添加收藏
        favoriteService.addFavorite(rid, new Date(), user.getUid());
        info.setFlag(true);//标记为用户登登录了
        writeValues(info, response);


    }

    //显示收藏次数功能
    public void favoriteCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        int rid = Integer.parseInt(ridStr);
        int favorite_count = favoriteService.favoriteCount(rid);
        writeValues(favorite_count, response);
    }
}
