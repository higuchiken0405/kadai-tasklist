package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //エンティティマネージャーを生成
	    EntityManager em = DBUtil.createEntityManger();

	    //「getAllTask」を実行し、結果をリストtasksに格納
	    List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
	                                .getResultList();
	    //エンティティマネージャを終了
	    em.close();

	     //リクエストスコープを通してJSPにtasksを渡す
	    request.setAttribute("tasks", tasks);

	    //index.jspに移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
	    rd.forward(request, response);
	}
}
