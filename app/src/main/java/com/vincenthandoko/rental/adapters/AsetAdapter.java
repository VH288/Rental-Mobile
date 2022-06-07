package com.vincenthandoko.rental.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vincenthandoko.rental.DataRiwayatCustomerActivity;
import com.vincenthandoko.rental.DetilDataAsetActivity;
import com.vincenthandoko.rental.R;
import com.vincenthandoko.rental.models.Aset;

import java.util.List;

public class AsetAdapter extends RecyclerView.Adapter<AsetAdapter.ViewHolder>{
    private List<Aset> asetList;
    private Context context;

    public AsetAdapter(List<Aset> asetList, Context context) {
        this.asetList = asetList;
        this.context = context;
    }

    @NonNull
    @Override
    public AsetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_aset, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsetAdapter.ViewHolder holder, int position) {
        Aset aset = asetList.get(position);
        holder.txtNamaMobil.setText(aset.getNama());
        holder.txtTipe.setText(aset.getTipe());
        holder.txtTransmisi.setText(aset.getTransmisi());
        String fitur = "";
        if(aset.isFiturAc() == 1){
            fitur = fitur + "Ac, ";
        }
        if(aset.isFiturAirbag() == 1){
            fitur = fitur + "Air Bag, ";
        }
        if(aset.isFiturMultimedia() == 1){
            fitur = fitur + "Multi Media, ";
        }
        holder.txtFitur.setText(fitur);
        holder.txtWarna.setText(aset.getWarna());
        holder.txtHarga.setText(String.valueOf(aset.getHarga()));
        holder.cvAset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetilDataAsetActivity.class);
                intent.putExtra("id",aset.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaMobil, txtTipe, txtTransmisi,
                txtWarna,txtFitur,txtHarga;
        CardView cvAset;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaMobil = itemView.findViewById(R.id.txtNamaMobil);
            txtTipe = itemView.findViewById(R.id.txtTipe);
            txtTransmisi = itemView.findViewById(R.id.txtTransmisi);
            txtWarna = itemView.findViewById(R.id.txtWarna);
            txtFitur = itemView.findViewById(R.id.txtFitur);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            cvAset = itemView.findViewById(R.id.cvAset);
        }
    }

    public void setAsetList(List<Aset> asetList){
        this.asetList = asetList;
    }
}
