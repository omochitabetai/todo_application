package controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UserConfirm
 */
@WebServlet("/userCreateConfirm")
public class UserCreateConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // 該当のIDのメッセージ1件のみをデータベースから取得
            User u = em.find(User.class, request.getParameter("user_id"));

            em.close();

            if(u.getUser_id().equals(null)) {

            }
            else {
                request.getSession().setAttribute("flush", "このユーザIDは既に使用されています");
                response.sendRedirect(request.getContextPath() + "/signup");
            }

            String user_id = request.getParameter("user_id");
            String user_password = request.getParameter("user_password");

            try {
                MessageDigest sha3_256 = MessageDigest.getInstance("SHA3-256");
                String user_password_enc = new String(sha3_256.digest(user_password.getBytes()));

                if(u.getUser_password().equals(user_password_enc)) {
                    request.getSession().setAttribute("user", u);
                    request.setAttribute("_token", request.getSession().getId());

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/todo_application/index.jsp");
                    rd.forward(request, response);
                }
                else {
                    request.getSession().setAttribute("flush", "ユーザID、パスワードが登録されたものと異なっています");
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            }
            catch(NoSuchAlgorithmException e){
                request.getSession().setAttribute("flush", "エラーが発生しました");
                response.sendRedirect(request.getContextPath() + "/start");
            }

        }
    }

}