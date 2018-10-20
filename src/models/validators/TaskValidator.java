package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TaskValidator {

    //バリデーションしてエラーメッセージを返すメソッド
    public static List<String> validate(Task task) {
        //エラーメッセージをリスト化
        List<String> errors = new ArrayList<String>();
        //contentをバリデーションして返ってきたメッセージを格納
        String content_error = _validateContent(task.getContent());
        //分岐処理
        if(!content_error.equals("")) {
            //返ってきたメッセージが空ではないなら
            //エラーメッセージリストに追加
            errors.add(content_error);
        }
        //リストを返す
        return errors;
    }

    //contentをバリエーションしてメッセージを返すメソッド
    private static String _validateContent(String content) {
        //分岐処理
        if(content == null || content.equals("")) {
            //contentがnull　または　空の時
            //メッセージを返す
            return "タスクを入力してください";
        }
        //contentがnullではない　または　空ではない時
        //空で返す
        return "";
    }
}
