package com.example.firebase_covid19;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder
{
    public TextView text;
    public TextView image;
    Context myContext;

    public CustomViewHolder(@NonNull View customLayoutView)
    {
        super(customLayoutView);

        text = customLayoutView.findViewById(R.id.template_text);
        image = customLayoutView.findViewById(R.id.template_text2);
    }

    public void bindData(final CustomData data)
    {
        text.setText(data.text);
        image.setText(data.imageID);
    }

}

