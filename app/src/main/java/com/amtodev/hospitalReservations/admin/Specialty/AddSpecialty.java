package com.amtodev.hospitalReservations.admin.Specialty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.amtodev.hospitalReservations.Class.ConexionSQLite;
import com.amtodev.hospitalReservations.R;

public class AddSpecialty extends AppCompatActivity {

    ConexionSQLite objConexion;
    final String NOMBRE_BASE_DE_DATOS = "agilesReservas";
    EditText SpecialtyName;
    Button btn_AddSpecialty;
    private Cursor fila;
    private SQLiteDatabase db;
    private ContentValues values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_specialty);
        objConexion = new ConexionSQLite(AddSpecialty.this, NOMBRE_BASE_DE_DATOS, null, 1);
        db = objConexion.getWritableDatabase();

        SpecialtyName = (EditText) findViewById(R.id.txtSpecialtyName);
        btn_AddSpecialty  = (Button) findViewById(R.id.btnSaveSpecialty);
        btn_AddSpecialty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (SpecialtyName.getText().toString().isEmpty()){
                    Toast.makeText(AddSpecialty.this, "Don't leave field empty",
                            Toast.LENGTH_LONG).show();
                }else {
                    registrar();
                    SpecialtyName.getText().clear();

                }
            }
        });

    }

    public void openAdminSpecialty(View view) {
        startActivity(new Intent(this, AdminSpecialty.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }

    private void registrar(){
        try{
            SQLiteDatabase miBaseDatos = objConexion.getWritableDatabase();
            values = new ContentValues();

            values.put("especialidad_nombre", SpecialtyName.getText().toString());
            miBaseDatos.insert("especialidades", null, values);
            miBaseDatos.close();
            Toast.makeText(AddSpecialty.this, SpecialtyName.getText().toString() +" Register Created Successfully", Toast.LENGTH_LONG).show();
        }catch(Exception error){
            Toast.makeText(AddSpecialty.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}