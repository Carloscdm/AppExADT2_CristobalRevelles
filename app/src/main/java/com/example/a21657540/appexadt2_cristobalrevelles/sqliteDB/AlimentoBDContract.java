package com.example.a21657540.appexadt2_cristobalrevelles.sqliteDB;

import android.provider.BaseColumns;

/**
 * Created by 21657540 on 07/03/2018.
 */

public class AlimentoBDContract {


    public static abstract class AlimentoEntry implements BaseColumns {

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";
        public static final String COLUMN_TIPO = "TIPO";
        public static final String COLUMN_ORIGEN = "ORIGEN";
        public static final String COLUMN_NUTRIENTES = "NUTRIENTES";
        public static final String COLUMN_FUNCION = "FUNCION";
        public static final String TABLE_NAME = "ALIMENTO";
    }



}
