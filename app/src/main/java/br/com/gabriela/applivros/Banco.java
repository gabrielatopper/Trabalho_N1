package br.com.gabriela.applivros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "AppLivros";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS livro ( " +
                "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "   nome TEXT NOT NULL , " +
                        "   categoria TEXT NOT NULL , " +
                        "   ano INTEGER ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
