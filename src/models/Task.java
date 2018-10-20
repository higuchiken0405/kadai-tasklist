package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//エンティティクラス
@Entity
//名前付きクエリ
@NamedQueries({
    @NamedQuery (
            //クエリ名
            name = "getAllTasks",
            //JPQL文　Taskの全てのカラムを取得し、IDを降順で表示
            query = "SELECT t FROM Task AS t ORDER BY t.id DESC"
        )
})
//テーブル名
@Table(name = "tasks")
public class Task {

    //プライマリキー設定
    @Id
    //カラム名id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //フィールドid
    private int id;

    //カラム名content
    @Column(name = "content")
    //フィールドcontent
    private String content;

    //ゲッター・セッター
    //id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //content
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


}
