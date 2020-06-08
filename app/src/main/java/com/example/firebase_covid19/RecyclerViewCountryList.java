package com.example.firebase_covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewCountryList extends RecyclerView.Adapter<CustomViewHolder>
{
    private List<CustomData> data;

    public RecyclerViewCountryList (@NonNull List<CustomData> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View customLayout = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        CustomViewHolder viewHolder = new CustomViewHolder(customLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.template;
    }
}