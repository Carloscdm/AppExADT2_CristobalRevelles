package com.example.a21657540.appexadt2_cristobalrevelles;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21657540.appexadt2_cristobalrevelles.model.Alimento;
import com.example.a21657540.appexadt2_cristobalrevelles.sqliteDB.AlimentoDataSource;

public class DatosActivity extends AppCompatActivity {


    String nombree;
    AlimentoDataSource ads;
    TextView nombre, origen, funcion, tipo, nutrientes;
    Button btnBorrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        ads = new AlimentoDataSource(this);
        nombree = getIntent().getStringExtra("nombre");

        nombre = findViewById(R.id.etNombreAlimento);
        origen = findViewById(R.id.etOrigenAlimento);
        funcion = findViewById(R.id.etFuncionAlimento);
        tipo = findViewById(R.id.etTipoAlimento);
        nutrientes = findViewById(R.id.etNutrientesAlimento);
        btnBorrar = findViewById(R.id.btnBorrar);

        Alimento alimento = ads.leerAlimentos(nombree);

        if(alimento != null){
            Alimento a = new Alimento(alimento.getNombre(), alimento.getTipo(), alimento.getOrigen(),
                    alimento.getNutrientes(), alimento.getFuncion());
            nombre.setText(a.getNombre());
            origen.setText(a.getOrigen());
            funcion.setText(a.getFuncion());
            tipo.setText(a.getTipo());
            nutrientes.setText(a.getNutrientes());

        } else {
            Toast.makeText(this, getResources().
                    getString(R.string.toastNOalimento),
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }


    public void borrarElemento(View view){
       confirmmacion().show();
    }




    public Dialog confirmmacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DatosActivity.this);
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.dialogo));

        builder.setPositiveButton(getResources().getString(R.string.dialogoAceptar),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreElemento = nombre.getText().toString();
                        ads.borrarAlimento(nombreElemento);
                        nombre.setText("");
                        origen.setText("");
                        funcion.setText("");
                        tipo.setText("");
                        nutrientes.setText("");
                        Toast.makeText(DatosActivity.this,getResources().getString(R.string.
                                elemELIM), Toast.LENGTH_LONG).show();
                        finish();
                    }

                });


        builder.setNegativeButton(getResources().getString(R.string.dialogoCancelar),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }




}
