package com.example.agendavacia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.agendavacia.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoE;
    Button  btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        txtNombre = (EditText)findViewById(R.id.ingrese_nombre);
        txtTelefono= (EditText)findViewById(R.id.ingrese_numero);
        txtCorreoE =(EditText)findViewById(R.id.ingrese_email);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        /*icono de navegacion de regreso*/
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.retur);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id =  dbContactos.insertarContacto(txtNombre.getText().toString(),txtTelefono.getText().toString(),txtCorreoE.getText().toString());
                if(id > 0 ){
                    Toast.makeText(NuevoActivity.this, "REGISTRO AGREGADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void  limpiar(){
        txtNombre.setText("");
        txtCorreoE.setText("");
        txtTelefono.setText("");
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }


}