package com.example.testconexionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ConexionBD conexion;
    TextView nombreIngresado;
    int contadorID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreIngresado = (TextView) findViewById(R.id.entradaNombre) ;
        conexion = new ConexionBD(this, "bd_usuariosTest", null, 1);
        contadorID = 1;

    }

    public void insertarNombre(View view){
        SQLiteDatabase base = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", contadorID+"");
        valores.put("nombre", nombreIngresado.getText().toString());

        Long resultado = base.insert("usuarios", "id", valores);
        contadorID++;
        nombreIngresado.setText("");
        Toast.makeText(this, "Usuario ingresado (id: "+resultado+")", Toast.LENGTH_SHORT).show();
    }

    public void recuperaNombre(View view){
        SQLiteDatabase base = conexion.getReadableDatabase();
        String[] parametros = {contadorID+""};
        String[] columnas = {"id", "nombre"};

        Cursor c = base.query("usuarios", columnas, "id=?", parametros, null,null,null);

        while(c.moveToNext()){
            Toast.makeText(this, "ID: "+c.getInt(0)+" nombre: "+c.getString(1), Toast.LENGTH_SHORT).show();
        }

    }
}