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
import com.vincenthandoko.rental.DataRiwayatDriverActivity;
import com.vincenthandoko.rental.R;
import com.vincenthandoko.rental.models.RiwayatTransaksi;

import java.util.List;

public class RiwayatDriverAdapter extends RecyclerView.Adapter<RiwayatDriverAdapter.ViewHolder> {
    private List<RiwayatTransaksi> riwayatTransaksiList;
    private Context context;

    public RiwayatDriverAdapter(List<RiwayatTransaksi> riwayatTransaksiList, Context context) {
        this.riwayatTransaksiList = riwayatTransaksiList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_riwayat_driver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatTransaksi riwayatTransaksi = riwayatTransaksiList.get(position);
        holder.txtNamaAset.setText(riwayatTransaksi.getNamaAset());
        holder.txtTglPemesanan.setText(riwayatTransaksi.getTglpemesanan());
        holder.txtIdTransaksi.setText(riwayatTransaksi.getIdt());
        holder.cvRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DataRiwayatDriverActivity.class);
                intent.putExtra("id",riwayatTransaksi.getIdt());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return riwayatTransaksiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIdTransaksi, txtTglPemesanan, txtNamaAset;
        CardView cvRiwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdTransaksi = itemView.findViewById(R.id.txtIdTransaksi);
            txtTglPemesanan = itemView.findViewById(R.id.txtTglPemesanan);
            txtNamaAset =itemView.findViewById(R.id.txtNamaAset);
            cvRiwayat = itemView.findViewById(R.id.cvRiwayat);
        }

    }
    public void setRiwayatTransaksiList(List<RiwayatTransaksi> riwayatTransaksiList){
        this.riwayatTransaksiList = riwayatTransaksiList;
    }
}
