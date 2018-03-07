package com.example.a21657540.appexadt2_cristobalrevelles.sqliteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a21657540.appexadt2_cristobalrevelles.model.Alimento;

import java.util.ArrayList;

/**
 * Created by 21657540 on 07/03/2018.
 */

public class AlimentoSqlHelper extends SQLiteOpenHelper {



    private ArrayList<Alimento> alimentos;


    static final String DATABASE_NAME = "AlimentosDB";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE_ALIMENTOS =
            "CREATE TABLE "+ AlimentoBDContract.AlimentoEntry.TABLE_NAME+ "( "+
                    AlimentoBDContract.AlimentoEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    AlimentoBDContract.AlimentoEntry.COLUMN_NAME+" TEXT NOT NULL," +
                    AlimentoBDContract.AlimentoEntry.COLUMN_ORIGEN+" TEXT NOT NULL,"+
                    AlimentoBDContract.AlimentoEntry.COLUMN_TIPO+" TEXT NOT NULL,"+
                    AlimentoBDContract.AlimentoEntry.COLUMN_FUNCION+" TEXT NOT NULL,"+
                    AlimentoBDContract.AlimentoEntry.COLUMN_NUTRIENTES+" TEXT NOT NULL);";


    public AlimentoSqlHelper(Context contexto) {
        super(contexto, DATABASE_NAME, null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALIMENTOS);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AlimentoBDContract.AlimentoEntry.TABLE_NAME);
        onCreate(db);
    }

}
