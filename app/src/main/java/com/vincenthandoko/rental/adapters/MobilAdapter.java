package com.vincenthandoko.rental.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vincenthandoko.rental.R;
import com.vincenthandoko.rental.models.Aset;

import java.util.List;

public class MobilAdapter extends RecyclerView.Adapter<MobilAdapter.ViewHolder>{
    private List<Aset> asetList;
    private Context context;

    public MobilAdapter(List<Aset> asetList, Context context) {
        this.asetList = asetList;
        this.context = context;
    }

    @NonNull
    @Override
    public MobilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_aset, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobilAdapter.ViewHolder holder, int position) {
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

    public void setMobilList(List<Aset> asetList){
        this.asetList = asetList;
    }
}
