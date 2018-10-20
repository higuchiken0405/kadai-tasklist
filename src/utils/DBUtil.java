package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

    //永続化ユニットの名前を定義(persistence.xmlの一般タブの名前と同じ)
    private static final String PERSISTENCE_UNIT_NAME = "tasklist";
    //エンティティマネージャーファクトリーemfを定義
    private static EntityManagerFactory emf;

    //エンティティマネージャーを得るメソッド
    public static EntityManager createEntityManger() {
        //エンティティマネージャファクトリがエンティティマネージャーを生成して返す
        return _getEntityManagerFactory().createEntityManager();
    }

    //エンティティマネージャーファクトリを得るメソッド(外部からファクトリを生成できないようにprivate)
    private static EntityManagerFactory _getEntityManagerFactory() {
        if(emf == null) {
            //エンティティマネージャーファクトリがない場合、生成する
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        //エンティティマネージャーファクトリを返す
        return emf;
    }



}
