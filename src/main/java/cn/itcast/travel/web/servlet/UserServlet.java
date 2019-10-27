package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    ResultInfo info = new ResultInfo();
    User user = new User();
    UserService userService = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //一  先判断用户输入的验证码
        //获取用户输入的验证码
        String client_check = request.getParameter("check");
        //获取系统验证码
        String server_checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        //判断用户输入了验证码
        if ("".equals(client_check) || "null".equals(client_check) || client_check == null) {
            //说明用户没有输入验证码
            info.setFlag(false);
            info.setErrorMsg("请输入验证码");
            writeValues(info, response);
            return;
        }
        //判断用户输入的验证码是否正确(忽略大小写进行判断)
        if (!client_check.equalsIgnoreCase(server_checkcode) || server_checkcode == null) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误,请重新输入验证码!");
            writeValues(info, response);
            return;
        }
        //验证码没有问题后,再进行接下来的操作

        // 二 获取用户在注册界面输入的信息
        Map<String, String[]> map = request.getParameterMap();
        //将map封装为user对象
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 三 调用userService层中的regist方法,获取resultinfo
        ResultInfo infor = userService.regist(user);
        writeValues(infor, response);
    }


    /**
     * 激活功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        //判断用户是否删除了链接中的UUID码
        if (code == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("激活失败,请联系邮箱管理员");
            return;
        }
        User u = userService.findUserByCode(code);
        if (u == null) {
            //说明用户自己更改链接中的UUID码
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("激活失败,请联系邮箱管理员");
        } else {
            //说明该用户可以正常激活
            userService.updateStatus(u.getUid());
            //页面重定向到登录界面
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }


    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //一  先判断用户输入的验证码
        //获取用户输入的验证码
        String client_check = request.getParameter("check");
        //获取系统验证码
        HttpSession session = request.getSession();
        String server_checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        //判断用户输入了验证码
        if ("".equals(client_check) || "null".equals(client_check) || client_check == null) {
            //说明用户没有输入验证码
            info.setFlag(false);
            info.setErrorMsg("请输入验证码");
            writeValues(info, response);
            return;
        }
        //判断用户输入的验证码是否正确(忽略大小写进行判断)
        if (!client_check.equalsIgnoreCase(server_checkcode) || server_checkcode == null) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误,请重新输入验证码!");
            writeValues(info, response);
            return;
        }
        //验证码没有问题后,再进行接下来的操作

        // 二 获取用户在注册界面输入的信息
        Map<String, String[]> map = request.getParameterMap();
        //将map封装为user对象
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 三 调用userService层中的regist方法,获取resultinfo
        ResultInfo infor = userService.login(user);
        writeValues(infor, response);
        //四 如果该用户可以成功登录的话,就将该用户的保存到session域中
        User u = userService.findNameByUser(user);
        request.getSession().setAttribute("user", u);
    }

    /**
     * 在session域中找对应的用户姓名
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = (User) request.getSession().getAttribute("user");
        writeValues(user,response);
    }


    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().invalidate();
        //重定向到login.html界面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
