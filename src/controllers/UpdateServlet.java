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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //トークンを受け取る
	    String _token = request.getParameter("_token");
	    //分岐処理
	    if(_token != null && _token.equals(request.getSession().getId()));
	        //トークンがnullではない　かつ　セッションIDと等しい場合

	        //エンティティマネージャを生成
	        EntityManager em = DBUtil.createEntityManger();
	        //Taskをパラメータidの値(Objectからintに変換)で検索して、結果を格納
	        Task task = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

	        //パラメータを取得し、フィールドにセット
	        String content = request.getParameter("content");
	        task.setContent(content);
	        //現在時刻を取得し、フィールドにセット
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        task.setUpdated_at(currentTime);

	        //taskをバリデーションした結果のエラーメッセージをリスト化
	        List<String> errors = TaskValidator.validate(task);
	        //分岐処理
	        if(errors.size() > 0) {
	            //エラーメッセージがある場合
	            //エンティティマネージャを終了
	            em.close();
	            //トークンとして、セッションIDをJSPに送る
	            request.setAttribute("_token", request.getSession().getId());
	            //taskをJSPに送る
	            request.setAttribute("task", task);
	            request.setAttribute("errors", errors);
	            //edit.jspに移動
	            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
	            rd.forward(request, response);
	        } else {
	            //エラーメッセージがない場合
	            //トランザクションの開始
	            em.getTransaction().begin();
	            //TaskをDBに登録
	            em.persist(task);
	            //登録をコミット
	            em.getTransaction().commit();
	            //エンティティマネージャを終了
	            em.close();
	            //セッションにflushとして、メッセージを保存
	            request.getSession().setAttribute("flush", "更新が完了しました");
	            //indexに戻る
	            response.sendRedirect(request.getContextPath() + "/index");
	        }
	}

}
