package com.example.dsccvrgubeta.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class OnBoardingModel {
    private String title,description;
    private int imageResource;

    public OnBoardingModel(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OnBoardingModel)) return false;
        OnBoardingModel that = (OnBoardingModel) o;
        return getImageResource() == that.getImageResource() &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public String toString() {
        return "OnBoardingModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }

    public static DiffUtil.ItemCallback<OnBoardingModel> itemCallback=new DiffUtil.ItemCallback<OnBoardingModel>() {

        @Override
        public boolean areItemsTheSame(@NonNull OnBoardingModel oldItem, @NonNull OnBoardingModel newItem) {
            return oldItem.getImageResource()==newItem.getImageResource();
        }

        @Override
        public boolean areContentsTheSame(@NonNull OnBoardingModel oldItem, @NonNull OnBoardingModel newItem) {
            return oldItem.equals(newItem);
        }
    };
}
