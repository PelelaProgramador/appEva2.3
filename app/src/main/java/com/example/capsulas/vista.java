package com.example.capsulas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class vista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista);

        Button botonRetrocederDetalles = findViewById(R.id.botonRetrocederDetalles);
        botonRetrocederDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presione el botón de retroceso, cerrar esta actividad y volver a la anterior
                finish();
            }
        });

        // Botón "Información" en tu actividad actual (vista.java)
        ImageButton btnInformacion = findViewById(R.id.btnInformacion);
        btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperar el nombre y la imagen de la empresa de la actividad actual
                String nombreEmpresa = getIntent().getStringExtra("nombreEmpresa");
                int imagenEmpresa = getIntent().getIntExtra("imagenEmpresa", 0);

                // Crear un Intent para abrir la nueva actividad (EmpresaInformacion)
                Intent infoIntent = new Intent(vista.this, EmpresaInformacion.class);

                // Pasa los datos necesarios a través del Intent
                infoIntent.putExtra("nombreEmpresa", nombreEmpresa);
                infoIntent.putExtra("imagenEmpresa", imagenEmpresa);

                // Inicia la nueva actividad
                startActivity(infoIntent);
            }
        });

        // Botón para ver detalles del producto
        Button botonVerProducto = findViewById(R.id.button5);
        botonVerProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad de detalles del producto
                Intent intent = new Intent(vista.this, detalles_producto.class);

                // Agregar datos del producto como extras al Intent
                intent.putExtra("nombreProducto", "Bidon de 20 litros");  // Reemplaza con el nombre real del producto
                intent.putExtra("precioProducto", 2000.0);  // Reemplaza con el precio real del producto
                intent.putExtra("imagenProducto", R.drawable.productobidon);  // Reemplaza con la imagen real del producto

                // Iniciar la actividad de detalles del producto
                startActivity(intent);
            }
        });
        // Botón "Opiniones"
        ImageButton btnOpiniones = findViewById(R.id.btnOpiniones);
        btnOpiniones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la vista de opiniones (EmpresaOpiniones)
                Intent opinionesIntent = new Intent(vista.this, EmpresaOpiniones.class);

                // Recuperar el nombre y la imagen de la empresa de la actividad actual
                String nombreEmpresa = getIntent().getStringExtra("nombreEmpresa");
                int imagenEmpresa = getIntent().getIntExtra("imagenEmpresa", 0);

                // Pasa los datos necesarios a través del Intent
                opinionesIntent.putExtra("nombreEmpresa", nombreEmpresa);
                opinionesIntent.putExtra("imagenEmpresa", imagenEmpresa);

                // Inicia la vista de opiniones
                startActivity(opinionesIntent);
            }
        });

        // Recuperar los datos de la empresa del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombreEmpresa = intent.getStringExtra("nombreEmpresa");
            int imagenEmpresa = intent.getIntExtra("imagenEmpresa", 0);
            int imagenProducto = intent.getIntExtra("imagenProducto", 0);

            // Mostrar los datos de la empresa en la segunda vista
            ImageView imagenEmpresaView = findViewById(R.id.imagenEmpresa);
            ImageView imagenProductoView = findViewById(R.id.imagenProducto);
            TextView nombreEmpresaView = findViewById(R.id.nombreEmpresa);
            TextView productosView = findViewById(R.id.productos);

            // Obtén la lista de productos de la empresa por nombre
            String productos = obtenerProductosPorNombre(nombreEmpresa);

            // Establecer la imagen de la empresa y el nombre de la empresa en la vista
            imagenEmpresaView.setImageResource(imagenEmpresa);
            nombreEmpresaView.setText(nombreEmpresa);
            productosView.setText(productos);

            // Establecer la imagen del producto en la vista
            if (imagenProducto != 0) {
                imagenProductoView.setImageResource(imagenProducto);
            } else {
                // Si no se proporciona una imagen de producto, puedes ocultar el ImageView o mostrar una imagen predeterminada.
                imagenProductoView.setVisibility(View.GONE);
            }
        }
    }

    private String obtenerProductosPorNombre(String nombreEmpresa) {
        // Aquí debes buscar y construir una lista de productos según el nombre de la empresa
        // y devolverla como una cadena de texto formateada.
        // Esto es un ejemplo simple:
        if (nombreEmpresa.equals("Empresa 1")) {
            return "Productos:\n- Bidón de agua: $2000.0";
        } else if (nombreEmpresa.equals("Empresa 2")) {
            return "Productos:\n- Bidón de agua: $2000.0";
        } else if (nombreEmpresa.equals("Empresa 3")) {
            return "Productos:\n- Bidón de agua: $2000.0";
        } else {
            return "No se encontraron productos para esta empresa.";
        }
    }
}



