package aps.gustavodariano.appcadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public static void inserir(Funcionario funcionario, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", funcionario.nome);
        valores.put("endereco", funcionario.endereco);
        valores.put("telefone", funcionario.telefone);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("funcionario", null, valores);

    }

    public static void editar(Funcionario funcionario, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", funcionario.nome);
        valores.put("endereco", funcionario.endereco);
        valores.put("telefone", funcionario.telefone);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("funcionario",valores, " id = " + funcionario.id , null );

    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("funcionario", " id = " + id , null);

    }

    public static List<Funcionario>getFuncionarios(Context context){
        List<Funcionario> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,nome,endereco,telefone FROM funcionario ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Funcionario funcionario = new Funcionario();
                funcionario.id = cursor.getInt( 0);
                funcionario.nome = cursor.getString(1);
                funcionario.endereco = cursor.getString(2);
                funcionario.telefone = cursor.getString(3);

                lista.add( funcionario );

            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static Funcionario getFuncionarioById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,nome,endereco,telefone FROM funcionario WHERE id = " + id, null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Funcionario funcionario = new Funcionario();
            funcionario.id = cursor.getInt( 0);
            funcionario.nome = cursor.getString(1);
            funcionario.endereco= cursor.getString(2);
            funcionario.telefone = cursor.getString(3);
            return funcionario;
        }else{
            return null;
        }
    }


}
