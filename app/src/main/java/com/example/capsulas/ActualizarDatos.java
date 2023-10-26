package com.example.capsulas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capsulas.modelos.usuario;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActualizarDatos extends AppCompatActivity {

    private FirebaseFirestore db;  // Instancia de Firestore
    private usuario usuarioActual;  // Instancia de Usuario para los datos actuales del usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);

        // Inicializar la instancia de Firestore
        db = FirebaseFirestore.getInstance();

        // Recupera los datos del usuario actual desde las preferencias compartidas
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre = datos.getString("nombre", "");
        String correo = datos.getString("correo", "");
        String genero = datos.getString("genero", "");
        String ciudad = datos.getString("ciudad", "");
        String contrasena = datos.getString("contrasena", "");

        // Crea una instancia de usuario con los datos recuperados
        usuarioActual = new usuario(nombre, correo, genero, ciudad, contrasena);

        // Llena los campos de la interfaz con los datos actuales
        EditText etUsuario = findViewById(R.id.etUsuario);
        etUsuario.setText(usuarioActual.getNombre());

        EditText etCorreo = findViewById(R.id.etCorreo);
        etCorreo.setText(usuarioActual.getCorreo());

        EditText etContrasena = findViewById(R.id.etContrasena);
        etContrasena.setText(usuarioActual.getContrasena());

        RadioGroup rgGenero = findViewById(R.id.rgGenero);
        int id = (usuarioActual.getGenero().equals("Masculino")) ? R.id.rbMasculino : R.id.rbFemenino;
        rgGenero.check(id);

        Spinner spCiudad = findViewById(R.id.spCiudad);
        int position = obtenerPosicionCiudad(usuarioActual.getCiudad()); // Reemplaza con la función para obtener la posición de la ciudad
        spCiudad.setSelection(position);

        // Configurar un OnClickListener para el botón de retroceso
        findViewById(R.id.btnRetroceder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); // Esto proporcionará el comportamiento de retroceso estándar
            }
        });
        // Encontrar el botón "Actualizar Datos" por su ID y configurar el OnClickListener
        Button btnActualizar = findViewById(R.id.buttonActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para actualizar los datos cuando se hace clic en el botón "Actualizar Datos"
                actualizar(view);
            }
        });
    }

    public void actualizar(View v) {
        // Recuperar los valores de los campos de la interfaz de usuario
        EditText etUsuario = findViewById(R.id.etUsuario);
        String nuevoUsuario = etUsuario.getText().toString();

        EditText etCorreo = findViewById(R.id.etCorreo);
        String nuevoCorreo = etCorreo.getText().toString();

        EditText etContrasena = findViewById(R.id.etContrasena);
        String nuevaContrasena = etContrasena.getText().toString();

        RadioGroup rgGenero = findViewById(R.id.rgGenero);
        int id = rgGenero.getCheckedRadioButtonId();
        String nuevoGenero = (id == R.id.rbMasculino) ? "Masculino" : "Femenino";

        Spinner spCiudad = findViewById(R.id.spCiudad);
        String nuevaCiudad = spCiudad.getSelectedItem().toString();

        // Actualizar los datos de la cuenta existente en Firestore
        actualizarDatosCuenta(nuevoUsuario, nuevoCorreo, nuevoGenero, nuevaCiudad, nuevaContrasena);

        // Puedes agregar código para actualizar los datos en SharedPreferences o tu fuente de datos local si es necesario
    }

    // Método para actualizar los datos del usuario sin cambiar el ID
    private void actualizarDatosCuenta(String nuevoUsuario, String nuevoCorreo, String nuevoGenero, String nuevaCiudad, String nuevaContrasena) {
        // Obtener el ID del usuario almacenado en SharedPreferences
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String usuarioId = datos.getString("_Id", "");
        Toast.makeText(this, "ID: "+usuarioId, Toast.LENGTH_SHORT).show();
        // Asegúrate de que el ID del usuario no esté vacío antes de actualizar
        if (!usuarioId.isEmpty()) {
            // Obtener una referencia al documento de usuario actual en Firestore
            DocumentReference usuarioRef = db.collection("usuario").document(usuarioId);

            // Crear un objeto con los nuevos datos del usuario
            Map<String, Object> nuevosDatos = new HashMap<>();
            nuevosDatos.put("nombre", nuevoUsuario);
            nuevosDatos.put("correo", nuevoCorreo);
            nuevosDatos.put("genero", nuevoGenero);
            nuevosDatos.put("ciudad", nuevaCiudad);
            nuevosDatos.put("contrasena", nuevaContrasena);

            // Actualizar los campos de datos del usuario en Firestore
            usuarioRef.update(nuevosDatos)
                    .addOnSuccessListener(aVoid -> {
                        // Actualización exitosa
                        Toast.makeText(this, "Datos actualizados con éxito", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Manejo de errores
                        Toast.makeText(this, "Error al actualizar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            // El ID del usuario no está disponible, muestra un mensaje de error
            int Toast_SHORT = 0;
            Toast.makeText(this, "No se pudo obtener el ID del usuario", Toast_SHORT).show();
        }
    }


    // Esta función puede ser personalizada para obtener la posición de la ciudad actual en el Spinner
    private int obtenerPosicionCiudad(String ciudadActual) {
        // Implementa la lógica para obtener la posición de la ciudad actual en el Spinner
        // Por ejemplo, puedes buscar la posición del elemento en el adaptador del Spinner
        return 0; // Reemplaza con la lógica real
    }
}
