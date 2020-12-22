package com.example.dsccvrgubeta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsccvrgubeta.R;
import com.example.dsccvrgubeta.models.OnBoardingModel;

public class OnBoardingAdapter extends ListAdapter<OnBoardingModel,OnBoardingAdapter.ViewHolder> {

    public OnBoardingAdapter() {
        super(OnBoardingModel.itemCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slide_layout,parent,false);
        //return view

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.onBoardingImage.setBackgroundResource(getItem(position).getImageResource());
        holder.titleTV.setText(getItem(position).getTitle());
        holder.descriptionTV.setText(getItem(position).getDescription());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView onBoardingImage;
        TextView titleTV,descriptionTV;

      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          onBoardingImage=itemView.findViewById(R.id.onBoardingIllustrationImage);
          titleTV=itemView.findViewById(R.id.titleTV);
          descriptionTV=itemView.findViewById(R.id.descriptionTV);
      }
  }
}
