package com.example.capsulas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class EmpresaInformacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_informacion);

        // Recuperar los datos de la empresa desde el Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreEmpresa = extras.getString("nombreEmpresa");
            int imagenEmpresa = extras.getInt("imagenEmpresa");

            // Obtener referencias a los elementos de la vista en activity_empresa_informacion.xml
            ImageView imagenEmpresaView = findViewById(R.id.imagenEmpresa);
            TextView nombreEmpresaView = findViewById(R.id.nombreEmpresa);

            // Establecer la imagen y el nombre de la empresa en la vista
            imagenEmpresaView.setImageResource(imagenEmpresa);
            nombreEmpresaView.setText(nombreEmpresa);
        }

        // Configurar un listener para el botón de retroceso
        Button botonRetroceder = findViewById(R.id.botonRetroceder);
        botonRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presione el botón de retroceso, cerrar esta actividad y volver a la anterior
                finish();
            }
        });
    }
}

