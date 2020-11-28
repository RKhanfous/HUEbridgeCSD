package com.example.hueemulatorapp.Application;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hueemulatorapp.Activities.OnItemClickListener;
import com.example.hueemulatorapp.Data.Lamp;
import com.example.hueemulatorapp.R;

import java.util.List;

public class ApiHueAdapter extends RecyclerView.Adapter<ApiHueAdapter.ImageViewHolder>{

    private static final String TAG = ApiHueAdapter.class.getName();

    private List<Lamp> lampList;
    private OnItemClickListener clickListener;

    public ApiHueAdapter(List<Lamp> lamps, OnItemClickListener listener){
        this.lampList = lamps;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() called");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lamp, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder() called for item " + position);
        Lamp lamp = lampList.get(position);
        holder.tvName.setText(lamp.getName());
        holder.tvModelId.setText(lamp.getModelId());
        holder.color_bar.setBackgroundColor(Color.HSVToColor(lamp.getHSV()));
    }

    @Override
    public int getItemCount() {
        return lampList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView tvName;
        public final TextView tvModelId;
        public final ImageView color_bar;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameLamp);
            tvModelId = itemView.findViewById(R.id.tvModelIdLamp);
            color_bar = itemView.findViewById(R.id.color_bar);
        }

        @Override
        public void onClick(View view){
            int clickedPos = getAdapterPosition();
            clickListener.onItemClick(clickedPos);
        }
    }
}
