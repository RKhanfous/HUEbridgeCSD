package com.example.hueemulatorapp.Application;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hueemulatorapp.Activities.DetailFragmentHue;
import com.example.hueemulatorapp.Activities.OnItemClickListener;
import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HttpParser;
import com.example.hueemulatorapp.Data.HueLight;
import com.example.hueemulatorapp.Data.Light;
import com.example.hueemulatorapp.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

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
        final ImageViewHolder fHolder = holder;
        final DimLight light = lightList.get(position);
        fHolder.tvName.setText(light.getName());
        fHolder.tvModelId.setText(light.getModelId());
        fHolder.tvType.setText(light.getType());

        if (light.getType().equals(Light.TYPE_DIM)){
            fHolder.color_bar.setBackgroundColor(Color.HSVToColor(255, light.getHSV()));
        } else if (light.getType().equals(Light.TYPE_HUE)){
            HueLight hueLight = (HueLight)light;
            fHolder.color_bar.setBackgroundColor(Color.HSVToColor(255, hueLight.getHSV()));
        }

        if (light.isOn()){
            fHolder.btnPower.setBackgroundResource(R.drawable.ic_power_on);
        } else {
            fHolder.btnPower.setBackgroundResource(R.drawable.ic_power_off);
        }

        holder.btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light.setOn(!light.isOn());
                Request requestPwr = null;
                try {
                    requestPwr = HttpClient.putRequest(HttpParser.SetState(light.getIndex()), JsonData.getBodyLightOn(light.isOn()));
                    HttpClient.getInstance().send(requestPwr, new LogCallback(ApiHueAdapter.class.getName()));
                    if (light.isOn()){
                        fHolder.btnPower.setBackgroundResource(R.drawable.ic_power_on);
                    } else {
                        fHolder.btnPower.setBackgroundResource(R.drawable.ic_power_off);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //            final ImageButton btnPower = container.findViewById(R.id.btnPower);
//
//            if (light.isOn()){
//                btnPower.setBackgroundResource(R.drawable.ic_power_on);
//            } else {
//                btnPower.setBackgroundResource(R.drawable.ic_power_off);
//            }
//
//            btnPower.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    light.setOn(!light.isOn());
//                    try {
//                        Request requestPwr = HttpClient.putRequest(HttpParser.SetState(light.getIndex()), JsonData.getBodyLightOn(light.isOn()));
//                        HttpClient.getInstance().send(requestPwr, new LogCallback(DetailFragmentHue.class.getName()));
//                        if (light.isOn()) {
//                            btnPower.setBackgroundResource(R.drawable.ic_power_on);
////                        btnPower.setBackgroundTintList(getColorStateList(R.color.colorSecondary));
//                        } else {
//                            btnPower.setBackgroundResource(R.drawable.ic_power_off);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
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
        public final ImageButton btnPower;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameLamp);
            tvModelId = itemView.findViewById(R.id.tvModelId);
            tvType = itemView.findViewById(R.id.tvTypeLamp);
            color_bar = itemView.findViewById(R.id.color_bar);
            btnPower = itemView.findViewById(R.id.btnPower);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int clickedPos = getAdapterPosition();
            clickListener.onItemClick(clickedPos);
        }
    }
}
