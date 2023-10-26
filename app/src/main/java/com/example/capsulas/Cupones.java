package com.example.capsulas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Cupones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupones);

        // Agregar un botón de retroceso a la barra de acción
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btnRetrocederCupones = findViewById(R.id.btnRetrocederCupones);
        btnRetrocederCupones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Retroceder a la actividad anterior
            }
        });

        // Agregar un OnClickListener para el botón "Agregar Cupón"
        Button btnAgregarCupon = findViewById(R.id.button2);
        btnAgregarCupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad para agregar un nuevo cupón (reemplaza AgregarCuponActivity.class)
                Intent intent = new Intent(Cupones.this, AgregarCupones.class);
                startActivity(intent);
            }
        });
    }
}


