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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //エンティティマネージャを生成
	    EntityManager em = DBUtil.createEntityManger();
	    //Taskをパラメータid(intに変換)で検索し、Taskに格納
	    Task task = em.find(Task.class, Integer.parseInt(request.getParameter("id")));
	    //エンティティマネージャを終了
	    em.close();

	    //JSPにtaskを渡す
	    request.setAttribute("task", task);
	    //edit.jspに移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
	    rd.forward(request, response);
	}

}
