package com.example.hueemulatorapp.Activities;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hueemulatorapp.Data.Lamp;
import com.example.hueemulatorapp.R;

public class DetailFragment extends Fragment {

    public static final String TAG = "DETAIL_FRAGMENT";

    private Lamp lamp;

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

    public DetailFragment(Lamp lamp){
        super();
        this.lamp = lamp;
    }

    @Override
    public void onStart() {
        this.init();
        super.onStart();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    private void init(){

        //Find non-changeable TV's
        TextView tvUniqueId = container.findViewById(R.id.lamp_id);
        TextView tvType = container.findViewById(R.id.lamp_type);
        TextView tvModelId = container.findViewById(R.id.lamp_model_id);
        TextView tvProductName = container.findViewById(R.id.lamp_product_name);
        //Set non-changeable TV's
        tvUniqueId.setText(lamp.getId());
        tvType.setText(lamp.getType());
        tvModelId.setText(lamp.getModelId());


        //Find changable TV's
        this.tvName = container.findViewById(R.id.lamp_name);
        this.tvHue = container.findViewById(R.id.lamp_hue);
        this.tvSat = container.findViewById(R.id.lamp_sat);
        this.tvBri = container.findViewById(R.id.lamp_bri);

        //Set changable TV's
        this.tvName.setText(lamp.getName());
        this.tvHue.setText(String.valueOf(lamp.getHue()));
        this.tvSat.setText(String.valueOf(lamp.getSat()));
        this.tvBri.setText(String.valueOf(lamp.getBri()));


        //Find name section
        this.etName = container.findViewById(R.id.etName);
        this.btnName = container.findViewById(R.id.btn_setName);

        //Set name section
        this.etName.setText(lamp.getName());


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
        this.sbHue.setMin(Lamp.MIN_HUE);
        this.sbHue.setMax(Lamp.MAX_HUE);
        this.sbHue.setProgress(lamp.getHue());

        //Set saturation
        this.sbSat.setMin(Lamp.MIN_SAT);
        this.sbSat.setMax(Lamp.MAX_SAT);
        this.sbSat.setProgress(lamp.getSat());

        //Set brightness
        this.sbBri.setMin(Lamp.MIN_BRI);
        this.sbBri.setMax(Lamp.MAX_BRI);
        this.sbBri.setProgress(lamp.getBri());

        //Set buttons
        this.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setName(etName.getText().toString());
                tvName.setText(lamp.getName());

                Lamp updated = lamp;

                // @TODO do api call with lamp changing name
            }
        });

        this.btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHue.setText(String.valueOf(lamp.getHue()));
                tvSat.setText(String.valueOf(lamp.getSat()));
                tvBri.setText(String.valueOf(lamp.getBri()));

                Lamp updated = lamp;

                // @TODO do api call with lamp changing color
            }
        });

        //Change color panel on seekbar change
        this.sbHue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lamp.setHue(getInRange(sbHue.getProgress(), Lamp.MIN_HUE, Lamp.MAX_HUE));
                Log.d(DetailFragment.TAG, "HSV: [" + lamp.getHSV()[0] + ", " + lamp.getHSV()[1] + ", " + lamp.getHSV()[2] + "]");
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
                lamp.setSat(sbSat.getProgress());
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
                lamp.setBri(sbBri.getProgress());
                UpdateColorPanel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void UpdateColorPanel(){
        this.colorPanel.setBackgroundColor(Color.HSVToColor(255, lamp.getHSV()));
        this.tvValueHue.setText(String.valueOf(lamp.getHue()));
        this.tvValueSat.setText(String.valueOf(lamp.getSat()));
        this.tvValueBri.setText(String.valueOf(lamp.getBri()));
    }

    //wrote this code then found out about setMin and setMax :)

    private int getInRange(int value, int min, int max){
        if (value < min){
            value = min;
        } else if (value > max){
            value = max;
        }
        return value;
    }
//
//    private int toLampHue(int progressHue){
//        return getInRange(progressHue * lamp.MAX_HUE / 1000, 0, lamp.MAX_HUE);
//    }
//
//    private int toProgressHue(int lampHue){
//        return getInRange(lampHue * 1000 / lamp.MAX_HUE, 0, 1000);
//    }
//
//    private int toLampSat(int progressSat){
//        return getInRange(progressSat * lamp.MAX_SAT / 1000, 0, lamp.MAX_SAT);
//    }
//
//    private int toProgressSat(int lampSat){
//        return getInRange(lampSat * 1000 / lamp.MAX_SAT, 0, 1000);
//    }
//
//    private int toLampBri(int progressBri){
//        return getInRange(progressBri * lamp.MAX_BRI / 1000, 1, lamp.MAX_BRI);
//    }
//
//    private int toProgressBri(int lampBri){
//        return getInRange(lampBri * 1000 / lamp.MAX_BRI, 0, 1000);
//    }
}
