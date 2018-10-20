package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //エンティティマネージャの生成
	    EntityManager em =DBUtil.createEntityManger();
	    //Taskをパラメータid(String型なのでintに変換)の値で検索し、Taskの格納
	    Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));
	    //エンティティマネージャの終了
	    em.close();
	    //tをJSPに渡す
	    request.setAttribute("task", t);
	    //show.jspへ移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/show.jsp");
	    rd.forward(request, response);
	}


}