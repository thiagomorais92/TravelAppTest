package thiago.com.br.myapplication.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.StringReader;

/**
 * Created by Samsung on 16/02/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BoaViagem";
    private static final int VERSAO =2 ;
    private static final String TABLE_VIAGEM="viagem";
    private static final String TABLE_GASTO="gasto";

    public DatabaseHelper(Context context){
        super(context,BANCO_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = ("CREATE TABLE viagem(_id INTEGER PRIMARY KEY,"+
                                          " destino TEXT, tipo_viagem INTEGER,data_chegada DATE,"+
                                          " data_saida DATE, orcamento DOUBLE,"+
                                          " quantidade_pessoas INTEGER);");

        String sql2 = ("CREATE TABLE gasto (_id INTEGER PRIMARY KEY,"+
                                          " categoria TEXT, data DATE,valor DOUBLE,"+
                                          " descricao TEXT, local TEXT, viagem_id INTEGER,"+
                                          " FOREIGN KEY(viagem_id) REFERENCES viagem(_id));");
        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlUpgrade = "DROP TABLE IF EXISTS " + TABLE_VIAGEM;
        db.execSQL(sqlUpgrade);
        onCreate(db);

    }
    public String getTableViagem(){return TABLE_VIAGEM;}
    public String getTableGasto(){return TABLE_GASTO;}
}
