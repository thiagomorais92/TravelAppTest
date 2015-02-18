package thiago.com.br.myapplication.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samsung on 18/02/2015.
 */
public class BoaViagemDAO {
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public BoaViagemDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb() {
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }
    public void close(){
        helper.close();
    }

}
