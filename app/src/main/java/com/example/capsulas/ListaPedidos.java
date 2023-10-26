package com.example.capsulas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capsulas.modelos.PedidoAdapter;
import com.example.capsulas.modelos.pedido;

import java.util.List;


public class ListaPedidos extends Fragment {

    private List<pedido> listaPedidos; // Lista de objetos Pedido, puedes crear una clase Pedido

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_pedidos, container, false);

        // Inicializar y configurar RecyclerView y su adaptador
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPedidos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        PedidoAdapter adapter = new PedidoAdapter(listaPedidos); // Debes crear tu propio adaptador
        recyclerView.setAdapter(adapter);

        return view;
    }
}
