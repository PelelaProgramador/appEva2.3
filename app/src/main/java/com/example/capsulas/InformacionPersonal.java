package com.example.capsulas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InformacionPersonal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);

        // Agregar un botón de retroceso a la barra de acción
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Configurar un OnClickListener para el ImageButton
        ImageButton misDatosButton = findViewById(R.id.informacionPersonalButton);
        misDatosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la actividad ActualizarDatosActivity
                Intent intent = new Intent(InformacionPersonal.this, ActualizarDatos.class);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Manejar el evento de clic en el botón de retroceso de la barra de acción
        onBackPressed(); // Esto proporcionará el comportamiento de retroceso estándar
        return true;
    }
}


