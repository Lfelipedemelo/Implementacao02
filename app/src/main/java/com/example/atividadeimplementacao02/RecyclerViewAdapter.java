package com.example.atividadeimplementacao02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<ItemMenu> itensMenu = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<ItemMenu> itensMenu, Context context){
        this.itensMenu = itensMenu;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemmenu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nome.setText(itensMenu.get(position).getNome());
        holder.descricao.setText(itensMenu.get(position).getDescricao());
        holder.calorias.setText(itensMenu.get(position).getCalorias());
        holder.gluten.setText(itensMenu.get(position).getGluten());
        holder.preco.setText(itensMenu.get(position).getPreco());
        Glide.with(context)
                .asBitmap()
                .load(itensMenu.get(position).getImagem())
                .into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        return itensMenu.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView descricao;
        TextView calorias;
        TextView gluten;
        TextView preco;
        ImageView imagem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtNome);
            descricao = itemView.findViewById(R.id.txtDescricao);
            calorias = itemView.findViewById(R.id.txtCalorias);
            gluten = itemView.findViewById(R.id.txtGluten);
            preco = itemView.findViewById(R.id.txtPreco);
            imagem = itemView.findViewById(R.id.imgImagem);
        }
    }

}
