package com.example.capsulas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detalles_producto extends AppCompatActivity {

    private int cantidad = 0; // Variable para almacenar la cantidad del producto
    private TextView cantidadProductoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        Button botonRetrocederDetallesProducto2 = findViewById(R.id.botonRetrocederDetallesProducto2);
        botonRetrocederDetallesProducto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presione el botón de retroceso, cerrar esta actividad y volver a la anterior
                finish();
            }
        });

        // Recuperar los datos del producto del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreProducto = extras.getString("nombreProducto");
            double precioProducto = extras.getDouble("precioProducto");
            int imagenProducto = extras.getInt("imagenProducto");

            // Mostrar los datos del producto en la vista
            ImageView imagenProductoView = findViewById(R.id.imagenProducto);
            TextView nombreProductoView = findViewById(R.id.nombreProducto);
            TextView precioProductoView = findViewById(R.id.precioProducto);
            cantidadProductoView = findViewById(R.id.cantidadProducto); // Actualiza el TextView de cantidad

            // Establecer la imagen del producto, el nombre y el precio en la vista
            imagenProductoView.setImageResource(imagenProducto);
            nombreProductoView.setText(nombreProducto);
            precioProductoView.setText("Precio: $" + precioProducto);

            Button botonAumentar = findViewById(R.id.botonAumentar);
            Button botonReducir = findViewById(R.id.botonReducir);

            botonAumentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Aumentar la cantidad del producto
                    cantidad++;
                    actualizarVista();
                }
            });

            botonReducir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reducir la cantidad del producto (asegurándose de que no sea negativa)
                    if (cantidad > 0) {
                        cantidad--;
                    }
                    actualizarVista();
                }
            });
        }
    }

    private void actualizarVista() {
        // Actualizar el TextView con la cantidad actual
        cantidadProductoView.setText(String.valueOf(cantidad));
    }
}


