package models;

import java.sql.Timestamp;

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
        ),
    @NamedQuery (
            //クエリ名
            name = "getTasksCount",
            //JPQL文　Taskのレコード数を取得
            query = "SELECT COUNT(t) FROM Task AS t"
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

    //カラム名created_At
    @Column(name = "created_at")
    //フィールドcreated_at
    private Timestamp created_at;

    //カラム名
    @Column(name = "updated_at")
    //フィールドupdated_at
    private Timestamp updated_at;


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
    //created_at
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    //updated_at
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
