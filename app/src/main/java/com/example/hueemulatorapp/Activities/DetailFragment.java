package com.example.hueemulatorapp.Activities;


import android.graphics.Color;
import android.os.Bundle;
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

    private Lamp lamp;

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

    public DetailFragment(Lamp lamp){
        super();
        this.lamp = lamp;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Find non-changeable TV's
        TextView tvUniqueId = container.findViewById(R.id.lamp_id);
        TextView tvType = container.findViewById(R.id.lamp_type);
        TextView tvModelId = container.findViewById(R.id.lamp_model_id);
        TextView tvProductName = container.findViewById(R.id.lamp_product_name);

        //Set non-changeable TV's
        tvUniqueId.setText(lamp.getId());
        tvType.setText(lamp.getType());
        tvModelId.setText(lamp.getModelId());
        tvProductName.setText(lamp.getProductName());


        //Find changable TV's
        this.tvName = container.findViewById(R.id.lamp_name);
        this.tvHue = container.findViewById(R.id.lamp_hue);
        this.tvSat = container.findViewById(R.id.lamp_sat);
        this.tvBri = container.findViewById(R.id.lamp_bri);

        //Set changable TV's
        this.tvName.setText(lamp.getName());
        this.tvHue.setText(lamp.getHue());
        this.tvSat.setText(lamp.getSat());
        this.tvBri.setText(lamp.getBri());


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
        this.sbHue.setMin(lamp.MIN_HUE);
        this.sbHue.setMax(lamp.MAX_HUE);
        this.sbHue.setProgress(lamp.getHue());
        this.tvValueHue.setText(lamp.getHue());

        //Set saturation
        this.sbSat.setMin(lamp.MIN_SAT);
        this.sbSat.setMax(lamp.MAX_SAT);
        this.sbSat.setProgress(lamp.getSat());
        this.tvValueSat.setText(lamp.getSat());

        //Set brightness
        this.sbBri.setMin(lamp.MIN_BRI);
        this.sbBri.setMax(lamp.MAX_BRI);
        this.sbBri.setProgress(lamp.getBri());
        this.tvValueBri.setText(lamp.getBri());

        //Set buttons
        this.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setName(etName.getText().toString());
                Lamp updated = lamp;

                // do api call with lamp changing name
            }
        });

        this.btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lamp updated = lamp;

                // do api call with lamp changing color
            }
        });

        //Change color panel on seekbar change
        this.sbHue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lamp.setHue(sbHue.getProgress());
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

        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    private void UpdateColorPanel(){
        this.colorPanel.setBackgroundColor(Color.HSVToColor(lamp.getHSV()));
    }

    //wrote this code then found out about setMin and setMax :)

//    private int getInRange(int value, int min, int max){
//        if (value < min){
//            value = min;
//        } else if (value > max){
//            value = max;
//        }
//        return value;
//    }
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
