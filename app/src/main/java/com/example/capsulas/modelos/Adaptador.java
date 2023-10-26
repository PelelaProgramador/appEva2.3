package com.example.capsulas.modelos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.capsulas.R;
import com.example.capsulas.vista;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.EmpresaViewHolder> {

    private List<Empresa> empresas;
    private Context context;

    public Adaptador(List<Empresa> empresas, Context context) {
        this.empresas = empresas;
        this.context = context;
    }

    @Override
    public EmpresaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_empresa, parent, false);
        return new EmpresaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmpresaViewHolder holder, int position) {
        Empresa empresa = empresas.get(position);

        // Configura los elementos de la vista con los datos de la empresa
        holder.nombreEmpresa.setText(empresa.getNombre());
        holder.imagenEmpresa.setImageResource(empresa.getImagenId());

        // Configura un listener para manejar los clics en la vista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la segunda vista y pasa los datos de la empresa a través de un Intent
                Intent intent = new Intent(context, vista.class);
                intent.putExtra("nombreEmpresa", empresa.getNombre());
                intent.putExtra("imagenEmpresa", empresa.getImagenId());
                // Añade el ID de la imagen del primer producto en la lista, si existe
                if (!empresa.getProductos().isEmpty()) {
                    intent.putExtra("imagenProducto", empresa.getProductos().get(0).getImagenProductoId());
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public class EmpresaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenEmpresa;
        TextView nombreEmpresa;

        public EmpresaViewHolder(View itemView) {
            super(itemView);
            imagenEmpresa = itemView.findViewById(R.id.imagenEmpresa);
            nombreEmpresa = itemView.findViewById(R.id.nombreEmpresa);
        }
    }
}




