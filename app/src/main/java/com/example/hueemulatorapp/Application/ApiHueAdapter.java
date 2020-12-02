package com.example.hueemulatorapp.Application;

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
import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class ApiHueAdapter extends RecyclerView.Adapter<ApiHueAdapter.ImageViewHolder>{

    private static final String TAG = ApiHueAdapter.class.getName();

    private List<DimLight> lightList;
    private OnItemClickListener clickListener;

    public ApiHueAdapter(List<DimLight> lights, OnItemClickListener listener){
        this.lightList = lights;
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
        DimLight light = lightList.get(position);
        holder.tvName.setText(light.getName());
        holder.tvModelId.setText(light.getModelId());
        holder.tvType.setText(light.getType());
        holder.color_bar.setBackgroundColor(Color.HSVToColor(light.getHSV()));
    }

    @Override
    public int getItemCount() {
        return lightList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView tvName;
        public final TextView tvModelId;
        public final TextView tvType;
        public final ImageView color_bar;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameLamp);
            tvModelId = itemView.findViewById(R.id.tvModelId);
            tvType = itemView.findViewById(R.id.tvTypeLamp);
            color_bar = itemView.findViewById(R.id.color_bar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int clickedPos = getAdapterPosition();
            clickListener.onItemClick(clickedPos);
        }
    }
}
