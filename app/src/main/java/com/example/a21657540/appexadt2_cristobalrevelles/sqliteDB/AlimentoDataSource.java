package com.example.a21657540.appexadt2_cristobalrevelles.sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.a21657540.appexadt2_cristobalrevelles.model.Alimento;

import java.util.ArrayList;

/**
 * Created by 21657540 on 07/03/2018.
 */

public class AlimentoDataSource {


    private Context miContext;
    private AlimentoSqlHelper miSqlHelper;
    SQLiteDatabase database;

    public AlimentoDataSource(Context miContext) {
        this.miContext = miContext;
        this.miSqlHelper = new AlimentoSqlHelper(miContext);

    }


    public SQLiteDatabase openReadable() {
        return miSqlHelper.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return miSqlHelper.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }


    public long insertarAlimento(Alimento alimento) {
        //Abrimos la BD(database)
        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();
        //Instanciamos clase esquema de la BD
        ContentValues ciudadValues = new ContentValues();
        //Añadimos lo que se debe insertar en la tabla (clave - valor)
        ciudadValues.put(AlimentoBDContract.AlimentoEntry.COLUMN_NAME,alimento.getNombre());
        ciudadValues.put(AlimentoBDContract.AlimentoEntry.COLUMN_ORIGEN,alimento.getOrigen());
        ciudadValues.put(AlimentoBDContract.AlimentoEntry.COLUMN_FUNCION,alimento.getFuncion());
        ciudadValues.put(AlimentoBDContract.AlimentoEntry.COLUMN_NUTRIENTES,alimento.getNutrientes());
        ciudadValues.put(AlimentoBDContract.AlimentoEntry.COLUMN_TIPO,alimento.getTipo());

        int idAlimento = (int) database.insert(AlimentoBDContract.AlimentoEntry.TABLE_NAME, null,ciudadValues);
        if(idAlimento != -1){
            database.setTransactionSuccessful();

        }
        database.endTransaction();
        //cerramos la base de datos
        close(database);
        //retornamos el IDContacto para un futuro uso
        return idAlimento;
    }



    public Alimento leerAlimentos(String nombre) {
        String nombree;
        String origen;
        String tipo;
        String funcion;
        String nutrientes;

        //Abrimos la Base de datos en modo lectura
        SQLiteDatabase database = openReadable();
        Alimento alimento = null;

        //Ejecutamos consulta
        String sentencia = "SELECT "
                + AlimentoBDContract.AlimentoEntry.COLUMN_NAME+ ", "
                + AlimentoBDContract.AlimentoEntry.COLUMN_ORIGEN+ ", "
                + AlimentoBDContract.AlimentoEntry.COLUMN_TIPO+ ", "
                + AlimentoBDContract.AlimentoEntry.COLUMN_FUNCION+ ", "
                + AlimentoBDContract.AlimentoEntry.COLUMN_NUTRIENTES
                +" FROM ALIMENTO WHERE "
                + AlimentoBDContract.AlimentoEntry.COLUMN_NAME+ " = '" + nombre+"'";
        Cursor miCursor = database.rawQuery(sentencia, null);
        //Recorremos el cursor
        if(miCursor.moveToFirst()){
            nombree = miCursor.getString(miCursor.getColumnIndex(AlimentoBDContract.AlimentoEntry.COLUMN_NAME));
            origen = miCursor.getString(miCursor.getColumnIndex(AlimentoBDContract.AlimentoEntry.COLUMN_ORIGEN));
            tipo = miCursor.getString(miCursor.getColumnIndex(AlimentoBDContract.AlimentoEntry.COLUMN_TIPO));
            funcion = miCursor.getString(miCursor.getColumnIndex(AlimentoBDContract.AlimentoEntry.COLUMN_FUNCION));
            nutrientes = miCursor.getString(miCursor.getColumnIndex(AlimentoBDContract.AlimentoEntry.COLUMN_NUTRIENTES));
            alimento = new Alimento(nombree,tipo,origen,nutrientes,funcion);
        }
        //database.endTransaction();
        //cerramos la base de datos
        close(database);
        return alimento;
    }



    public void borrarAlimento(String nombre){

        //Abrimos la BD(database)

        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();


        String [] args = {String.valueOf(nombre)};
        database.delete(AlimentoBDContract.AlimentoEntry.TABLE_NAME,
                AlimentoBDContract.AlimentoEntry.COLUMN_NAME+ "=?", args);

        database.setTransactionSuccessful();
        database.endTransaction();
        //cerramos la base de datos
        database.close();


    }




}
