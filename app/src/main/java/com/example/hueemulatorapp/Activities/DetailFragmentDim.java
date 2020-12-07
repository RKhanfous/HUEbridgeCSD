package com.example.hueemulatorapp.Activities;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hueemulatorapp.Application.HttpClient;
import com.example.hueemulatorapp.Application.JsonData;
import com.example.hueemulatorapp.Application.LogCallback;
import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HttpParser;
import com.example.hueemulatorapp.Data.Light;
import com.example.hueemulatorapp.R;

import java.io.IOException;

import okhttp3.Request;

public class DetailFragmentDim extends Fragment {

    public static final String TAG = DetailFragmentDim.class.getName();

    private DimLight light;

    private ViewGroup container;

    private TextView tvName;
    private TextView tvBri;

    private EditText etName;
    private Button btnName;

    private ImageView colorPanel;

    private SeekBar sbBri;
    private Button btnColor;

    private TextView tvValueBri;

    /**
     * httpClient.put(JsonData.lights + "/" + "modelId", "json string");
     * <p>
     * httpClient.put(JsonData.lights + "/" + "modelId" + JsonData.setState, "jsonString");
     */

    public DetailFragmentDim(DimLight light) {
        super();
        this.light = light;
    }

    @Override
    public void onStart() {
        this.init();
        super.onStart();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.detail_fragment_dim, container, false);
    }

    private void init() {

        //Find non-changeable TV's
        TextView tvUniqueId = container.findViewById(R.id.lamp_id);
        TextView tvType = container.findViewById(R.id.lamp_type);
        TextView tvModelId = container.findViewById(R.id.lamp_model_id);
        //Set non-changeable TV's
        tvUniqueId.setText(light.getId());
        tvType.setText(light.getType());
        tvModelId.setText(light.getModelId());


        //Find changable TV's
        this.tvName = container.findViewById(R.id.lamp_name);
        this.tvBri = container.findViewById(R.id.lamp_bri);

        //Set changable TV's
        this.tvName.setText(light.getName());
        this.tvBri.setText(String.valueOf(light.getBri()));


        //Find name section
        this.etName = container.findViewById(R.id.etName);
        this.btnName = container.findViewById(R.id.btn_setName);

        //Set name section
        this.etName.setText(light.getName());


        //Find color section
        this.colorPanel = container.findViewById(R.id.color_panel);

        this.sbBri = container.findViewById(R.id.bri_seekbar);
        this.tvValueBri = container.findViewById(R.id.tvBri);

        this.btnColor = container.findViewById(R.id.btn_setColor);


        //Set color section
        //Set color panel
        UpdateColorPanel();

        //Set brightness
        this.sbBri.setMin(Light.MIN_BRI);
        this.sbBri.setMax(Light.MAX_BRI);
        this.sbBri.setProgress(light.getBri());

        //Set buttons
        this.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light.setName(etName.getText().toString());
                tvName.setText(light.getName());

                try {
                    Log.d(DetailFragmentDim.class.getName(), JsonData.getBodyRename(light.getName()));
                    Request requestRename = HttpClient.putRequest(HttpParser.getInstance().rename(light.getIndex()), JsonData.getBodyRename(light.getName()));
                    HttpClient.getInstance().send(requestRename, new LogCallback(DetailFragmentDim.class.getName() + " - Rename"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        this.btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBri.setText(String.valueOf(light.getBri()));

                DimLight updated = light;
                try {
                    Request requestBrightness = HttpClient.putRequest(HttpParser.getInstance().setState(updated.getIndex()), JsonData.getBodyBrightness(light.getBri()));
                    HttpClient.getInstance().send(requestBrightness, new LogCallback(DetailFragmentDim.class.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.sbBri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                light.setBri(sbBri.getProgress() - 1);
                UpdateColorPanel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final ImageButton btnPower = container.findViewById(R.id.btnPower);
        if (light.isOn()){
            btnPower.setBackgroundResource(R.drawable.ic_power_on);
        } else {
            btnPower.setBackgroundResource(R.drawable.ic_power_off);
        }

        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light.setOn(!light.isOn());
                try {
                    Request requestPwr = HttpClient.putRequest(HttpParser.getInstance().setState(light.getIndex()), JsonData.getBodyLightOn(light.isOn()));
                    HttpClient.getInstance().send(requestPwr, new LogCallback(DetailFragmentDim.class.getName()));
                    if (light.isOn()) {
                        btnPower.setBackgroundResource(R.drawable.ic_power_on);
                    } else {
                        btnPower.setBackgroundResource(R.drawable.ic_power_off);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void UpdateColorPanel() {
        this.colorPanel.setBackgroundColor(Color.HSVToColor(255, light.getHSV()));
        this.tvValueBri.setText(String.valueOf(light.getBri()));
    }
}