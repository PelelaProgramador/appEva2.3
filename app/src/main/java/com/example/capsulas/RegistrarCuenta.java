package com.example.capsulas;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capsulas.modelos.usuario;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrarCuenta extends AppCompatActivity {

    FirebaseFirestore db;  // Instancia de Firestore
    CollectionReference usuariosRef; // Referencia a la colección "usuario"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);

        // Inicializar la instancia de Firestore
        db = FirebaseFirestore.getInstance();

        // Obtener una referencia a la colección "usuario"
        usuariosRef = db.collection("usuario");
    }

    @SuppressLint("NonConstantResourceId")
    public void registrar(View v) {
        // Recuperando los valores de los campos de la interfaz de usuario
        EditText etUsuario = findViewById(R.id.etUsuario);
        String usuario = etUsuario.getText().toString();

        EditText etCorreo = findViewById(R.id.etCorreo);
        String correo = etCorreo.getText().toString();

        RadioGroup rgGenero = findViewById(R.id.rgGenero);
        int id = rgGenero.getCheckedRadioButtonId();
        String genero = (id == R.id.rbMasculino) ? "Masculino" : "Femenino";

        Spinner spCiudad = findViewById(R.id.spCiudad);
        String ciudad = spCiudad.getSelectedItem().toString();

        EditText etPassword = findViewById(R.id.etPassword);
        String contrasena = etPassword.getText().toString();

        EditText etPassword2 = findViewById(R.id.etPassword2);
        String contrasena2 = etPassword2.getText().toString();

        // Validación de contraseña
        if (!contrasena.equals(contrasena2)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un objeto Usuario con los datos ingresados
        usuario nuevoUsuario = new usuario(usuario, correo, genero, ciudad, contrasena);

        // Agregar el nuevo usuario a Firestore
        agregarUsuarioFirestore(nuevoUsuario);
    }

    private void agregarUsuarioFirestore(usuario usuario) {
        // Obtener una referencia a la colección "usuario" (debes crearla previamente en Firestore)
        db.collection("usuario")
                .add(usuario) // Utiliza .add() para que Firestore genere un ID automáticamente
                .addOnSuccessListener(documentReference -> {
                    // Registro exitoso, obtén el ID del usuario
                    String usuarioId = documentReference.getId();
                    // Asignar el ID generado automáticamente al atributo _id del usuario
                    usuario.set_Id(usuarioId);

                    // Ahora puedes agregar el objeto usuario con el ID asignado manualmente
                    db.collection("usuario")
                            .document(usuarioId)
                            .set(usuario)
                            .addOnSuccessListener(aVoid -> {
                                // Registro exitoso
                                Toast.makeText(this, "Registro exitoso con ID: " + usuarioId, Toast.LENGTH_SHORT).show();

                                // Guardar el ID del usuario en las preferencias compartidas
                                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                                SharedPreferences.Editor editor = datos.edit();
                                editor.putString("_Id", usuarioId); // Guardar el ID del usuario
                                editor.apply(); // Aplicar los cambios en las preferencias compartidas
                            })
                            .addOnFailureListener(e -> {
                                // Manejo de errores
                                Toast.makeText(this, "Registro fallido: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    // Manejo de errores
                    Toast.makeText(this, "Registro fallido: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }}




