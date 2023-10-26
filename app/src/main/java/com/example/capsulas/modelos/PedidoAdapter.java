package com.example.capsulas.modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capsulas.R;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<pedido> listaPedidos;

    public PedidoAdapter(List<pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pedido, parent, false);

        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        pedido pedido = listaPedidos.get(position);
        // Configura la vista del elemento de la lista con los datos del pedido
        holder.bind(pedido);
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNumeroPedidos;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            textViewNumeroPedidos = itemView.findViewById(R.id.textViewNumeroPedidos);
        }

        public void bind(pedido pedido) {
            // Configura la vista del elemento de la lista
            textViewNumeroPedidos.setText("Número de pedidos: " + pedido.getNumeroPedidos());
            // Puedes configurar otros elementos de la vista aquí
        }
    }
}
