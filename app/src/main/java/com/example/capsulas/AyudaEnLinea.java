package com.example.capsulas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AyudaEnLinea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_en_linea);

        // Agregar un botón de retroceso a la barra de acción
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btnRetrocederAyudaEnLinea = findViewById(R.id.btnRetrocederAyudaEnLinea);
        btnRetrocederAyudaEnLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Retroceder a la actividad anterior
            }
        });

        // Configura un OnClickListener para el botón ayudaPedidos
        ImageButton ayudaPedidosButton = findViewById(R.id.ayudaPedidos);
        ayudaPedidosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para abrir la vista AyudaPedidos
                Intent intent = new Intent(AyudaEnLinea.this, AyudaPedidos.class);
                startActivity(intent);
            }
        });
    }
}

