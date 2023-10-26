package com.example.capsulas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;  // Instancia de Firestore (o tu base de datos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la instancia de Firestore
        db = FirebaseFirestore.getInstance();  // Reemplaza por tu base de datos si no usas Firestore
    }

    public void login(View v) {
        EditText campo1 = findViewById(R.id.Correo);
        String correo = campo1.getText().toString();
        EditText campo2 = findViewById(R.id.contrasena);
        String contrasena = campo2.getText().toString();

        // Realiza una consulta en la base de datos para verificar los datos del usuario
        db.collection("usuario")
                .whereEqualTo("correo", correo)
                .whereEqualTo("contrasena", contrasena)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Los datos del usuario coinciden, permite iniciar sesión
                        CheckBox cbRecuerdame = findViewById(R.id.cbRecuerdame);
                        boolean chequeado = cbRecuerdame.isChecked();
                        if (chequeado) {
                            SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                            SharedPreferences.Editor editor = datos.edit();
                            editor.putString("correo", correo);

                            QueryDocumentSnapshot usuarioDoc = (QueryDocumentSnapshot) queryDocumentSnapshots.getDocuments().get(0);

                            // Almacena los datos del usuario en las preferencias compartidas
                            editor.putString("nombre", usuarioDoc.getString("nombre"));
                            editor.putString("genero", usuarioDoc.getString("genero"));
                            editor.putString("ciudad", usuarioDoc.getString("ciudad"));
                            editor.putString("_id", usuarioDoc.getString("_id"));
                            editor.putString("contrasena", contrasena);
                            editor.apply();
                        }


                        Intent i = new Intent(this, Principal1.class);
                        startActivity(i);
                    } else {
                        // No se encontró un usuario con los datos proporcionados, muestra un mensaje de error
                        Toast.makeText(this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    // Manejo de errores
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void crearCuenta(View v) {
        Intent i = new Intent(this, RegistrarCuenta.class);
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String correo = datos.getString("correo", "");
        if (!correo.equals("")) {
            Intent i = new Intent(this, Principal1.class);
            startActivity(i);
        }
    }
}



