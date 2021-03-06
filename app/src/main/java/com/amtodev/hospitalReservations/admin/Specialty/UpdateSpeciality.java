package com.amtodev.hospitalReservations.admin.Specialty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
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

public class UpdateSpeciality extends AppCompatActivity {

    EditText SpecialtyUpdate;
    Button Button_SpecialtyUpdate, Button_SpecialtyDelete;
    ConexionSQLite objConexion;
    final String NOMBRE_BASE_DATOS = "agilesReservas";
    int especialidad_id;

    private Cursor fila;
    private SQLiteDatabase db;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_speciality);
        objConexion = new ConexionSQLite(UpdateSpeciality.this, NOMBRE_BASE_DATOS, null, 1);
        db = objConexion.getWritableDatabase();
        SpecialtyUpdate = (EditText) findViewById(R.id.txtSpecialtyUpdate);
        Button_SpecialtyUpdate = (Button) findViewById(R.id.btnUpdateSpecialty);
        Button_SpecialtyDelete = (Button) findViewById(R.id.btnDeleteSpecialty);

        Button_SpecialtyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SpecialtyUpdate.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateSpeciality.this, "Don't leave empty fields",
                            Toast.LENGTH_LONG).show();
                } else {
                    Update();
                    SpecialtyUpdate.getText().clear();
                }
            }
        });
        Button_SpecialtyDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }


    private void GoBackActivity(){
        Intent actividad = new Intent(UpdateSpeciality.this, ViewSpecialty.class);
        startActivity(actividad);
        UpdateSpeciality.this.finish();
    }

    private void Update(){
        try{
            SQLiteDatabase miBaseDatos = objConexion.getWritableDatabase();
            String comando = "UPDATE especialidades SET especialidad_nombre='"+ SpecialtyUpdate.getText() + "'," + "WHERE especialidad_id = '" + especialidad_id + "'";
            miBaseDatos.execSQL(comando);
            miBaseDatos.close();
            Toast.makeText(UpdateSpeciality.this, "Specialty Successfully Update", Toast.LENGTH_SHORT).show();
        }catch (Exception error){
            Toast.makeText(UpdateSpeciality.this, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void openUpdateSpeciality(View view) {
        startActivity(new Intent(this, ViewSpecialty.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }

    private void delete(){
        try{
            SQLiteDatabase miBaseDatos = objConexion.getWritableDatabase();
            String comando = "DELETE FROM especialidades WHERE especialidad_id ='"+ especialidad_id + "'";
            miBaseDatos.execSQL(comando);
            miBaseDatos.close();
            Toast.makeText(UpdateSpeciality.this, "InterHospital successfully Delete", Toast.LENGTH_SHORT).show();
        }catch (Exception error){
            Toast.makeText(UpdateSpeciality.this, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle valoresAdicionales = getIntent().getExtras();
        if (valoresAdicionales == null){
            Toast.makeText(UpdateSpeciality.this, "You need send some InterHospital ID", Toast.LENGTH_SHORT).show();
            especialidad_id = 0;
            GoBackActivity();
        }else{
            especialidad_id = valoresAdicionales.getInt("especialidad_id");
            verContacto();
        }
    }


    private void verContacto(){
        try{
            SQLiteDatabase base = objConexion.getReadableDatabase();
            String consulta = "SELECT especialidad_id,especialidad_nombre FROM especialidades WHERE especialidad_id = '"+ especialidad_id +"'";
            @SuppressLint("Recycle") Cursor cadaRegistro = base.rawQuery(consulta, null);
            if (cadaRegistro.moveToFirst()){
                do{
                    SpecialtyUpdate.setText(cadaRegistro.getString(1));
                }while(cadaRegistro.moveToNext());
            }
        }catch (Exception error){
            Toast.makeText(UpdateSpeciality.this, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSpeciality.this);
        builder.setTitle("Confirm");
        builder.setMessage("Do you want to delete this InterHospital?");
        builder.setPositiveButton("Yes, Delete", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UpdateSpeciality.this, "InterHospital no Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();
    }
}