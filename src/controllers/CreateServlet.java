package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
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
	        Task task = new Task();
	        //パラメータを受け取り、インスタンスのフィールドにセット
	        String content = request.getParameter("content");
	        task.setContent(content);
	        //現在時刻を取得
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        //作成日時、更新日時のフィールドにセット
	        task.setCreated_at(currentTime);
	        task.setUpdated_at(currentTime);

	        //タスクをバリデーションした結果のエラーメッセージをリスト化
	        List<String> errors = TaskValidator.validate(task);
	        //分岐処理
	        if(errors.size() > 0) {
	            //エラーメッセージがあるなら
	            //エンティティマネージャーを終了
	            em.close();
	            //トークンとしてセッションIDをJSPに送る
	            request.setAttribute("_token", request.getSession().getId());
	            //taskをJSPに送る
	            request.setAttribute("task", task);
	            //エラーメッセージをJSPに送る
	            request.setAttribute("errors", errors);
	            //new.jspに移動
	            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/tasks/new.jsp");
	            rd.forward(request, response);
	        } else {
	            //エラーメッセージがないなら
	            //トランザクションを開始
	            em.getTransaction().begin();
	            //TaskをDBに新規登録
	            em.persist(task);
	            //登録を確定
	            em.getTransaction().commit();
	            //エンティティマネージャを終了
	            em.close();
	            //セッションにflushとしてメッセージを保存
	            request.getSession().setAttribute("flush", "登録が完了しました");
	            //indexに戻る
	            response.sendRedirect(request.getContextPath() + "/index");
	        }
	    }
	}
}
