package com.example.nacho.loginrecordar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class SecundariaActivity extends AppCompatActivity {
    private EditText et_Descripcion;
    private AutoCompleteTextView act_Tarea;
    private Button btn_Realizada, btn_Postergar;
    private ImageButton ibtn_Add, ibtn_Del;
    private ListView lv_Tareas;
    private ArrayAdapter lvAdap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        et_Descripcion = (EditText) findViewById(R.id.et_Descripcion);
        act_Tarea = (AutoCompleteTextView) findViewById(R.id.act_Tarea);
        btn_Realizada = (Button) findViewById(R.id.btn_Realizada);
        btn_Postergar = (Button) findViewById(R.id.btn_Postergar);
        ibtn_Add = (ImageButton) findViewById(R.id.ibtn_Add);
        ibtn_Del = (ImageButton) findViewById(R.id.ibtn_Del);
        lv_Tareas = (ListView) findViewById(R.id.lv_Tareas);

        leerSheredPreferences();

        ibtn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTarea();
                leerSheredPreferences();
            }
        });
        ibtn_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarTarea();
                leerSheredPreferences();
                act_Tarea.setText("");
                et_Descripcion.setText("");
            }
        });
        lv_Tareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarea tarea = (Tarea) parent.getAdapter().getItem(position);
                act_Tarea.setText(tarea.getTarea());
                et_Descripcion.setText(tarea.getDescripcion());
            }
        });

    }

    private void leerSheredPreferences () {
        ArrayList<Tarea> tareasPendientes = new ArrayList();

        SharedPreferences pref = getSharedPreferences("TareasActivas", Context.MODE_PRIVATE);
        Map<String,?> tar = pref.getAll();
        for (Map.Entry<String,?> t : tar.entrySet()){
            tareasPendientes.add(new Tarea(t.getKey(), t.getValue().toString()));
        }

        lvAdap = new ArrayAdapter(SecundariaActivity.this, android.R.layout.simple_list_item_1,tareasPendientes);
        lv_Tareas.setAdapter(lvAdap);
    }

    private void guardarTarea (){
        SharedPreferences pref = getSharedPreferences("TareasActivas", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(act_Tarea.getText().toString(), et_Descripcion.getText().toString());
        edit.commit();
    }
    private void eliminarTarea (){
        SharedPreferences pref = getSharedPreferences("TareasActivas", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.remove(act_Tarea.getText().toString());
        edit.commit();
    }
}
