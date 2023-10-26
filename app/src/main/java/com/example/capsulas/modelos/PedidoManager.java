package com.example.capsulas.modelos;

import android.content.Context;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PedidoManager {
    private static final String TAG = "PedidoManager";
    private Context context;

    public PedidoManager(Context context) {
        this.context = context;
    }

    public interface OnPedidosLoadedListener {
        void onPedidosLoaded(List<pedido> pedidos);
        void onError(String errorMessage);
    }

    public void getPedidosDelUsuario(String nombreUsuario, OnPedidosLoadedListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String collectionPath = "pedidos"; // Ajusta esto según la estructura de tu base de datos

        // Realiza una consulta para obtener los pedidos del usuario actual
        db.collection(collectionPath)
                .whereEqualTo("nombreUsuario", nombreUsuario)
                .orderBy("timestamp", Query.Direction.DESCENDING) // Puedes ordenar por fecha si tienes una marca de tiempo
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<pedido> pedidos = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            pedido pedido = document.toObject(pedido.class);
                            pedidos.add(pedido);
                        }
                        listener.onPedidosLoaded(pedidos);
                    } else {
                        Log.e(TAG, "Error al obtener pedidos", task.getException());
                        listener.onError("Error al obtener los pedidos");
                    }
                });
    }
}
