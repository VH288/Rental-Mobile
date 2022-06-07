package com.vincenthandoko.rental.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vincenthandoko.rental.DataRiwayatCustomerActivity;
import com.vincenthandoko.rental.R;
import com.vincenthandoko.rental.models.Aset;
import com.vincenthandoko.rental.models.RiwayatTransaksi;

import java.util.List;

public class RiwayatCustomerAdapter extends RecyclerView.Adapter<RiwayatCustomerAdapter.ViewHolder> {
    private List<RiwayatTransaksi> riwayatTransaksiList;

    public RiwayatCustomerAdapter(List<RiwayatTransaksi> riwayatTransaksiList) {
        this.riwayatTransaksiList = riwayatTransaksiList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIdTransaksi, txtTglPemesanan, txtNamaDriver, txtNamaAset;
        CardView cvRiwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdTransaksi = itemView.findViewById(R.id.txtIdTransaksi);
            txtTglPemesanan = itemView.findViewById(R.id.txtTglPemesanan);
            txtNamaDriver = itemView.findViewById(R.id.txtNamaDriver);
            txtNamaAset =itemView.findViewById(R.id.txtNamaAset);
            cvRiwayat = itemView.findViewById(R.id.cvRiwayat);
        }

    }
    public void setRiwayatTransaksiList(List<RiwayatTransaksi> riwayatTransaksiList){
        this.riwayatTransaksiList = riwayatTransaksiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_riwayat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatTransaksi riwayatTransaksi = riwayatTransaksiList.get(position);
        holder.txtNamaAset.setText(riwayatTransaksi.getNamaAset());
        holder.txtNamaDriver.setText(riwayatTransaksi.getNamaDriver());
        holder.txtTglPemesanan.setText(riwayatTransaksi.getTglpemesanan());
        holder.txtIdTransaksi.setText(riwayatTransaksi.getIdt());
        holder.cvRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DataRiwayatCustomerActivity.class);
                intent.putExtra("id",riwayatTransaksi.getIdt());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return riwayatTransaksiList.size();
    }
}
