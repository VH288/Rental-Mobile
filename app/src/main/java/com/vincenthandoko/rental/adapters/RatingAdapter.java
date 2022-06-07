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
import com.vincenthandoko.rental.models.Rating;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    private List<Rating> ratingList;
    private Context context;

    public RatingAdapter(List<Rating> ratingList, Context context) {
        this.ratingList = ratingList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rating,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rating rating = ratingList.get(position);
        holder.txtIdCustomer.setText(rating.getIdCustomer());
        holder.txtRate.setText(String.valueOf(rating.getRate()));
        holder.txtComment.setText(rating.getComment());
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            CardView cvRating;
            TextView txtIdCustomer, txtRate, txtComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvRating = itemView.findViewById(R.id.cvRating);
            txtIdCustomer = itemView.findViewById(R.id.txtIdCustomer);
            txtRate = itemView.findViewById(R.id.txtRate);
            txtComment = itemView.findViewById(R.id.txtComment);
        }
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
