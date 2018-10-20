package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DestroyServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //トークンを受け取る
	    String _token = request.getParameter("_token");

	    if(_token != null && _token.equals(request.getSession().getId())) {
	        //トークンがnullではない　かつ　セッションIDと等しい時

	        //エンティティマネージャを生成
	        EntityManager em = DBUtil.createEntityManger();
	        //セッションにあるTaskのIDを取得し、その値(Objectなのでintに変換)でTaskを検索し、格納する
	        Task task = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

	        //トランザクション開始
	        em.getTransaction().begin();
	        //Taskの削除
	        em.remove(task);
	        //削除処理のコミット
	        em.getTransaction().commit();
	        //エンティティマネージャを終了
	        em.close();
	        //セッションの削除
	        request.getSession().removeAttribute("message_id");
	        //indexに戻る
	        response.sendRedirect(request.getContextPath() + "/index");
	    }
	}

}
