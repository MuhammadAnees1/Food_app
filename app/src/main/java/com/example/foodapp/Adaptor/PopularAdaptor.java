package com.example.foodapp.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Domian.FoodDomain;
import com.example.foodapp.R;
import com.example.foodapp.ShowDetailsActivity;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {

    private ArrayList<FoodDomain> popularFood;

    public PopularAdaptor(ArrayList<FoodDomain> popularFood) {
        this.popularFood = popularFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain food = popularFood.get(position);
        holder.title.setText(food.getTitle());
        holder.fee.setText(String.valueOf(food.getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(food.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                    intent.putExtra("object", popularFood.get(clickedPosition));
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fee, title;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
        }
    }
}

