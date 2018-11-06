package my.app.servlet;

import my.app.entities.Detail;
import my.app.entities.User;
import my.app.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        System.out.println(code);
        LoginService service = new LoginService();
        String res = service.sendCode(code);//CAS认证

        User user = service.check();//判断openID是否存在
        Detail detail = service.checkDetail();
        System.out.println(user);
        System.out.println(detail);

        PrintWriter pw = resp.getWriter();
        pw.write(service.getUserReturn());
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
