package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //トークンを受け取る
	    String _token = request.getParameter("_token");

	    //受け取ったトークンによって分岐処理
	    if(_token != null && _token.equals(request.getSession().getId())) {
	        //受け取ったトークンがnullではない　かつ　セッションIDと等い場合

	        //エンティティマネージャを生成
	        EntityManager em = DBUtil.createEntityManger();
	        //Taskをインスタンス化
	        Task t = new Task();
	        //パラメータを受け取り、インスタンスのフィールドにセット
	        String content = request.getParameter("content");
	        t.setContent(content);
	        //現在時刻を取得
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        //作成日時、更新日時のフィールドにセット
	        t.setCreated_at(currentTime);
	        t.setUpdated_at(currentTime);

	        //トランザクションを開始
	        em.getTransaction().begin();
	        //TaskをDBに新規登録
	        em.persist(t);
	        //登録を確定
	        em.getTransaction().commit();
	        //エンティティマネージャを終了
	        em.close();
	        //indexに戻る
	        response.sendRedirect(request.getContextPath() + "/index");

	    }
	}

}
