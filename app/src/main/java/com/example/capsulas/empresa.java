package com.example.capsulas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capsulas.modelos.Adaptador;
import com.example.capsulas.modelos.Empresa;

import java.util.ArrayList;
import java.util.List;

public class empresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        // Crear una lista de empresas
        List<Empresa> empresas = new ArrayList<>();
        Empresa empresa1 = new Empresa("Empresa 1", R.drawable.amongusrosado);
        empresa1.agregarProducto("Bidón de agua", 2000.0, R.drawable.productobidon);
        // Agregar más productos a empresa1 si es necesario
        empresas.add(empresa1);

        Empresa empresa2 = new Empresa("Empresa 2", R.drawable.amongusamarillo);
        empresa2.agregarProducto("Bidón de agua", 2000.0, R.drawable.productobidon);
        // Agregar más productos a empresa2 si es necesario
        empresas.add(empresa2);

        Empresa empresa3 = new Empresa("Empresa 3", R.drawable.amongusverde);
        empresa3.agregarProducto("Bidón de agua", 2000.0, R.drawable.productobidon);
        // Agregar más productos a empresa3 si es necesario
        empresas.add(empresa3);

        // Configurar el adaptador personalizado para el RecyclerView
        Adaptador adaptador = new Adaptador(empresas, this);

        // Configurar el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.listaEmpresa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        // Obtén una referencia al botón "Volver" por su ID
        Button botonVolver = findViewById(R.id.botonVolver);

        // Configura un listener para el botón "Volver"
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presione el botón, regresa a la vista anterior
                finish();
            }
        });
    }
}





