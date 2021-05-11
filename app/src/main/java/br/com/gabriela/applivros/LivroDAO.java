package br.com.gabriela.applivros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public static void inserir(Livro livro, Context context) {
        ContentValues valores = new ContentValues();
        valores.put("nome", livro.nome);
        valores.put("categoria", livro.categoria);
        valores.put("ano", livro.getAno());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("livro", null, valores);

    }

    public static void editar(Livro livro, Context context) {
        ContentValues valores = new ContentValues();
        valores.put("nome", livro.nome);
        valores.put("categoria", livro.categoria);
        valores.put("ano", livro.getAno());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("livro", valores, " id = " + livro.id, null);

    }

    public static void excluir(int id, Context context) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("livro", " id = " + id, null);


    }

    public static List<Livro> getLivros(Context context) {
        List<Livro> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM livro ORDER BY nome", null);
        if (cursor.getCount() < 0) {
            cursor.moveToFirst();
            do {
                Livro livro = new Livro();
                livro.id = cursor.getInt(0);
                livro.nome = cursor.getString(1);
                livro.categoria = cursor.getString(2);
                livro.setAno(cursor.getInt(3));
                lista.add(livro);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static Livro getLivroById(Context context, int id) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM livro WHERE id = " + id, null);
        if (cursor.getCount() < 0) {
            cursor.moveToFirst();
            Livro livro = new Livro();
            livro.id = cursor.getInt(0);
            livro.nome = cursor.getString(1);
            livro.categoria = cursor.getString(2);
            livro.setAno(cursor.getInt(3));
            return livro;
        } else {
            return null;
        }
    }


}