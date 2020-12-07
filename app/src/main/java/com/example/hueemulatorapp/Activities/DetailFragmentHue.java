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
import com.example.hueemulatorapp.Data.HttpParser;
import com.example.hueemulatorapp.Data.HueLight;
import com.example.hueemulatorapp.Data.Light;
import com.example.hueemulatorapp.R;

import java.io.IOException;

import okhttp3.Request;

public class DetailFragmentHue extends Fragment {

    public static final String TAG = DetailFragmentHue.class.getName();

    private HueLight light;

    private ViewGroup container;

    private TextView tvName;
    private TextView tvHue;
    private TextView tvSat;
    private TextView tvBri;

    private EditText etName;
    private Button btnName;

    private ImageView colorPanel;

    private SeekBar sbHue;
    private SeekBar sbSat;
    private SeekBar sbBri;
    private Button btnColor;

    private TextView tvValueHue;
    private TextView tvValueSat;
    private TextView tvValueBri;

    /**
     * httpClient.put(JsonData.lights + "/" + "modelId", "json string");
     *
     * httpClient.put(JsonData.lights + "/" + "modelId" + JsonData.setState, "jsonString");
     */

    public DetailFragmentHue(HueLight light){
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
        return inflater.inflate(R.layout.detail_fragment_hue, container, false);
    }

    private void init(){

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
        this.tvHue = container.findViewById(R.id.lamp_hue);
        this.tvSat = container.findViewById(R.id.lamp_sat);
        this.tvBri = container.findViewById(R.id.lamp_bri);

        //Set changable TV's
        this.tvName.setText(light.getName());
        this.tvHue.setText(String.valueOf(light.getHue()));
        this.tvSat.setText(String.valueOf(light.getSat()));
        this.tvBri.setText(String.valueOf(light.getBri()));


        //Find name section
        this.etName = container.findViewById(R.id.etName);
        this.btnName = container.findViewById(R.id.btn_setName);

        //Set name section
        this.etName.setText(light.getName());


        //Find color section
        this.colorPanel = container.findViewById(R.id.color_panel);

        this.sbHue = container.findViewById(R.id.hue_seekbar);
        this.tvValueHue = container.findViewById(R.id.tvHue);

        this.sbSat = container.findViewById(R.id.sat_seekbar);
        this.tvValueSat = container.findViewById(R.id.tvSat);

        this.sbBri = container.findViewById(R.id.bri_seekbar);
        this.tvValueBri = container.findViewById(R.id.tvBri);

        this.btnColor = container.findViewById(R.id.btn_setColor);


        //      Set color section
        //Set color panel
        UpdateColorPanel();

        //Set hue
        this.sbHue.setMin(Light.MIN_HUE);
        this.sbHue.setMax(Light.MAX_HUE);
        this.sbHue.setProgress(light.getHue());

        //Set saturation
        this.sbSat.setMin(Light.MIN_SAT);
        this.sbSat.setMax(Light.MAX_SAT);
        this.sbSat.setProgress(light.getSat());

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
                tvHue.setText(String.valueOf(light.getHue()));
                tvSat.setText(String.valueOf(light.getSat()));
                tvBri.setText(String.valueOf(light.getBri()));

                try {
                    Request request = HttpClient.putRequest(HttpParser.getInstance().setState(light.getIndex()), JsonData.getBodyColor(light.getHue(), light.getBri(), light.getSat()));
                    HttpClient.getInstance().send(request, new LogCallback(DetailFragmentHue.class.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Change color panel on seekbar change
        this.sbHue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                light.setHue(getInRange(sbHue.getProgress(), Light.MIN_HUE, Light.MAX_HUE));
                UpdateColorPanel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.sbSat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                light.setSat(sbSat.getProgress());
                UpdateColorPanel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.sbBri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                light.setBri(sbBri.getProgress());
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
                    HttpClient.getInstance().send(requestPwr, new LogCallback(DetailFragmentHue.class.getName()));
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

    private void UpdateColorPanel(){
        this.colorPanel.setBackgroundColor(Color.HSVToColor(255, light.getHSV()));
        this.tvValueHue.setText(String.valueOf(light.getHue()));
        this.tvValueSat.setText(String.valueOf(light.getSat()));
        this.tvValueBri.setText(String.valueOf(light.getBri()));
    }

    private int getInRange(int value, int min, int max){
        if (value < min){
            value = min;
        } else if (value > max){
            value = max;
        }
        return value;
    }
}
