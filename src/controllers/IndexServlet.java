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

	    //ページネーションのページの初期値を設定
	    int page = 1;
	    try {
	        //ページ番号をクリックした時、パラメータの値(Stringからintへ変換)を取得し、pageへ代入
	        page = Integer.parseInt(request.getParameter("page"));
	    } catch (NumberFormatException e) {}

	    //「getAllTask」を実行し、結果をリストtasksに格納
	    List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
	                                //指定した数の次の数から
	                                .setFirstResult(5 * (page -1))
	                                //指定した5の数だけ
	                                .setMaxResults(5)
	                                //複数の結果を取得
	                                .getResultList();
	    //「getTasksCount」を実行し、結果をtasks_countに格納
	    long tasks_count = (long) em.createNamedQuery("getTasksCount", Long.class)
	                                        //一つの結果を取得
	                                        .getSingleResult();
	    //エンティティマネージャを終了
	    em.close();

	     //JSPにtasksを渡す
	    request.setAttribute("tasks", tasks);
	    //JSPにtasks_countを渡す
	    request.setAttribute("tasks_count", tasks_count);
	    //JSPにpageを渡す
	    request.setAttribute("page", page);

	    //分岐処理
	    if(request.getSession().getAttribute("flush") != null) {
	        //flushがnullでないなら、セッションにあるflushをJSPに送る
	        request.setAttribute("flush", request.getSession().getAttribute("flush"));
	        //セッションからflushを削除
	        request.getSession().removeAttribute("flush");
	    }

	    //index.jspに移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
	    rd.forward(request, response);
	}
}
