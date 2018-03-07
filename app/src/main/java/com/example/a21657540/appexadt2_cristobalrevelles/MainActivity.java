package com.example.a21657540.appexadt2_cristobalrevelles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21657540.appexadt2_cristobalrevelles.model.Alimento;
import com.example.a21657540.appexadt2_cristobalrevelles.sqliteDB.AlimentoDataSource;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar, btnConsultar, btnLimpiar;
    EditText nombre;
    EditText origen, tipo, nutrientes, funcion;
    AlimentoDataSource ads;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        nombre = findViewById(R.id.etNombre1);
        origen = findViewById(R.id.etOrigen);
        tipo = findViewById(R.id.etTipo);
        nutrientes = findViewById(R.id.etNutrientes);
        funcion = findViewById(R.id.etFuncion);

        ads = new AlimentoDataSource(this);

        //guardamos las preferencias
        SharedPreferences sp = getSharedPreferences("PreferencesDB", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ORIGEN: ", "VEGETAL/ANIMAL");
        editor.putString("NUTRIENTES:", "Carbohidratos/Proteínas animales/" +
                "Proteínas vegetales/Vitaminas/Lípidos”");
        editor.putString("\uF0A7\t“FUNCION:", "Energética/Plástica/Reguladora");
        editor.commit();
    }




    public void consultarAlimento(View view){
        if(nombre.length()== 0) {
            Toast.makeText(this, getResources().getString
                    (R.string.toastCampoNombre), Toast.LENGTH_LONG).show();
        } else {
            String nombreAlimento = nombre.getText().toString();
            Intent intent = new Intent(this, DatosActivity.class);
            intent.putExtra("nombre",nombre.getText().toString());
            startActivity(intent);
            nombre.setText("");

        }
    }


    public void RegistrarAlimento(View view){
        if(nombre.length()== 0){
            Toast.makeText(this, getResources().getString
                    (R.string.toastCampoNombre),Toast.LENGTH_LONG).show();
        } else if(origen.length() == 0){
            Toast.makeText(this, getResources().getString
                    (R.string.toastCampoOrigen),Toast.LENGTH_LONG).show();
        } else if(tipo.length()== 0){
            Toast.makeText(this, getResources().getString
                    (R.string.toastCampoTipo),Toast.LENGTH_LONG).show();
        } else if(nutrientes.length()== 0) {
            Toast.makeText(this, getResources().getString
                    (R.string.toastCampoNutrientes),Toast.LENGTH_LONG).show();
        } else if(funcion.length()== 0) {
            Toast.makeText(this, getResources().getString
                    (R.string.toastCampoFuncion),Toast.LENGTH_LONG).show();
        } else {
            Alimento alimento = new Alimento(nombre.getText().toString(),
                    tipo.getText().toString(), origen.getText().toString(),
                    nutrientes.getText().toString(), funcion.getText().toString());

          int id = (int)  ads.insertarAlimento(alimento);

          if(id != -1){
                Toast.makeText(this, getResources().
                        getString(R.string.toastInsertOk),Toast.LENGTH_LONG).show();
                limpiarCampos(view);
          } else {
              Toast.makeText(this, getResources().
                      getString(R.string.toastInsertKO),Toast.LENGTH_LONG).show();
              limpiarCampos(view);
          }
        }
    }


    public void limpiarCampos(View view){
        nombre.setText("");
        origen.setText("");
        tipo.setText("");
        nutrientes.setText("");
        funcion.setText("");
    }




}
