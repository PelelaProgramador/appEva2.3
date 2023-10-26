package com.example.capsulas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Informacion extends Fragment {

    public Informacion() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_informacion, container, false);

        // Recuperar los elementos de la vista

        TextView nombreUsuarioTextView = view.findViewById(R.id.nombreUsuario);
        TextView mostrarCorreoTextView = view.findViewById(R.id.mostrarCorreo);

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getContext());

        // Obtener los datos del usuario
                String nombre = datos.getString("nombre", "");
                String correo = datos.getString("correo", "");
        // Utiliza los datos en tu vista
                nombreUsuarioTextView.setText(nombre);
                mostrarCorreoTextView.setText(correo);



        // Obtener referencias a tus botones de imagen
        ImageButton btnInformacionPersonal = view.findViewById(R.id.btnInformacionPersonal);
        ImageButton btnCupones = view.findViewById(R.id.btnCupones);
        ImageButton btnAyudaEnLinea = view.findViewById(R.id.btnAyudaEnLinea);

        // Agregar un OnClickListener para el botón "Información Personal"
        btnInformacionPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción que se realiza al hacer clic en el botón "Información Personal"
                // Por ejemplo, puedes abrir una nueva actividad (reemplaza InformacionPersonalActivity.class)
                Intent intent = new Intent(getContext(), InformacionPersonal.class);
                startActivity(intent);
            }
        });

        // Agregar un OnClickListener para el botón "Cupones"
        btnCupones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción que se realiza al hacer clic en el botón "Cupones"
                // Por ejemplo, puedes abrir una nueva actividad (reemplaza CuponesActivity.class)
                Intent intent = new Intent(getContext(), Cupones.class);
                startActivity(intent);
            }
        });

        // Agregar un OnClickListener para el botón "Ayuda en Línea"
        btnAyudaEnLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción que se realiza al hacer clic en el botón "Ayuda en Línea"
                // Por ejemplo, puedes abrir una nueva actividad (reemplaza AyudaEnLineaActivity.class)
                Intent intent = new Intent(getContext(), AyudaEnLinea.class);
                startActivity(intent);
            }
        });

        // Obtener referencia al botón "Eliminar Cuenta"
        Button btnEliminarCuenta = view.findViewById(R.id.btnEliminar);

        // Agregar un OnClickListener para el botón "Eliminar Cuenta"
        btnEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un cuadro de diálogo de confirmación
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Estás seguro de que deseas eliminar tu cuenta?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Iniciar la lógica para eliminar la cuenta
                        eliminarCuenta();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // El usuario canceló la eliminación de la cuenta
                        // No se realiza ninguna acción
                    }
                });
                builder.show();
            }
        });

        return view;
    }

    // Método para eliminar la cuenta del usuario
    private void eliminarCuenta() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // La cuenta se eliminó correctamente
                            // Puedes redirigir al usuario a la pantalla de inicio de sesión
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            // Ocurrió un error al eliminar la cuenta
                            // Puedes mostrar un mensaje de error o tomar otras medidas
                            // Por ejemplo:
                            // Toast.makeText(getContext(), "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }}

