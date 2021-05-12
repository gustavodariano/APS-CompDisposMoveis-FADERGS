package aps.gustavodariano.appcadastro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static final int VERSAO = 2;
    private static final String NOME = "CadastroDB";


    public Banco(Context context){
        super(context, NOME, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS funcionario(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "endereco TEXT NOT NULL," +
                "telefone TEXT NOT NULL ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


    }
}
