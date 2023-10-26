package com.example.capsulas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class detalles_producto extends AppCompatActivity {
    private int cantidad = 1; // Comienza con una cantidad predeterminada de 1
    private TextView cantidadProductoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);



        Button botonRetrocederDetallesProducto2 = findViewById(R.id.botonRetrocederDetallesProducto2);
        botonRetrocederDetallesProducto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cerrar esta actividad y volver a la anterior
            }
        });

        // Recuperar los datos del producto del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreProducto = extras.getString("nombreProducto");
            double precioProducto = extras.getDouble("precioProducto");
            int imagenProducto = extras.getInt("imagenProducto");

            ImageView imagenProductoView = findViewById(R.id.imagenProducto);
            TextView nombreProductoView = findViewById(R.id.nombreProducto);
            TextView precioProductoView = findViewById(R.id.precioProducto);
            cantidadProductoView = findViewById(R.id.cantidadProducto);

            imagenProductoView.setImageResource(imagenProducto);
            nombreProductoView.setText(nombreProducto);
            precioProductoView.setText("Precio: $" + precioProducto);

            Button botonAumentar = findViewById(R.id.botonAumentar);
            Button botonReducir = findViewById(R.id.botonReducir);
            Button botonComprar = findViewById(R.id.botonComprar);

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
                    // Reducir la cantidad del producto (asegurándose de que no sea menor que 1)
                    if (cantidad > 1) {
                        cantidad--;
                        actualizarVista();
                    }
                }
            });

            botonComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Realizar la acción de compra y crear una colección en Firebase
                    realizarCompra();
                }
            });
        }
    }

    private void actualizarVista() {
        cantidadProductoView.setText(String.valueOf(cantidad));
    }

    private void realizarCompra() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener el nombre del usuario desde la preferencia compartida (ajusta esto según tu implementación)
        // Aquí asumimos que tienes una preferencia compartida llamada "nombreUsuario"
        // donde almacenas el nombre del usuario cuando inicia sesión

        // Recuperar el nombre del usuario de las preferencias compartidas
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String nombreUsuario = datos.getString("nombre", "");

        // Crear un nuevo documento en la colección "pedidos" con los datos necesarios
        Map<String, Object> pedido = new HashMap<>();
        pedido.put("nombreUsuario", nombreUsuario);
        pedido.put("numeroPedidos", cantidad);

        db.collection("pedidos")
                .add(pedido)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Pedido agregado con éxito
                        Toast.makeText(detalles_producto.this, "Pedido realizado con éxito", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error al agregar el pedido
                        Toast.makeText(detalles_producto.this, "Error al realizar el pedido", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String getNombreUsuarioDesdeSharedPreferences() {
        // Aquí debes implementar la lógica para obtener el nombre de usuario desde las preferencias compartidas
        // Este es solo un ejemplo, debes adaptarlo a tu implementación.
        SharedPreferences sharedPref = getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE);
        return sharedPref.getString("nombreUsuario", "");
    }
}



