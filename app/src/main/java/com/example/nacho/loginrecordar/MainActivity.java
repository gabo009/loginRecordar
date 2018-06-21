package com.example.nacho.loginrecordar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et_Usuario, et_Password;
    private Button btn_Ingresar;
    private CheckBox chb_RecUsuario, chb_RecPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Password = (EditText) findViewById(R.id.et_Password);
        et_Usuario = (EditText) findViewById(R.id.et_Usuario);
        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);
        chb_RecPass = (CheckBox) findViewById(R.id.chb_RecPass);
        chb_RecUsuario = (CheckBox) findViewById(R.id.chb_RecUsuario);

        leerSheredPreferences();

        chb_RecPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chb_RecUsuario.setChecked(true);
            }
        });

        chb_RecUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chb_RecUsuario.isChecked()){
                    chb_RecPass.setChecked(false);
                }
            }
        });

        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_Password.getText().toString().equals("123456")){
                    Intent intent = new Intent(MainActivity.this, SecundariaActivity.class);
                    startActivity(intent);
                }else{
                    et_Password.setError(getResources().getString(R.string.error));
                }
                if(chb_RecUsuario.isChecked()){
                    guardarUsuario();
                    Toast.makeText(MainActivity.this, "usuario guardado", Toast.LENGTH_SHORT).show();
                }
                if(chb_RecPass.isChecked()){
                    guardarContrasena();
                    Toast.makeText(MainActivity.this, "contraseña guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void leerSheredPreferences (){
        SharedPreferences pref = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        et_Usuario.setText(pref.getString("usuario", ""));
        et_Password.setText(pref.getString("contrasena", ""));
    }

    private void guardarUsuario (){
        SharedPreferences pref = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("usuario", et_Usuario.getText().toString());
        edit.commit();
    }
    private void guardarContrasena (){
        SharedPreferences pref = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("contrasena", et_Password.getText().toString());
        edit.commit();
    }
}