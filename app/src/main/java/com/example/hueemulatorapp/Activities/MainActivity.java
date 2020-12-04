package com.example.hueemulatorapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HueLight;
import com.example.hueemulatorapp.R;

public class MainActivity extends AppCompatActivity implements DetailFragmentReplacer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        FrameLayout mainframe = findViewById(R.id.main_framelayout);
        mainframe.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        Fragment mainFragment = new MainFragment(this, this);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, mainFragment, MainFragment.TAG)
                .commit();
    }

    @Override
    public void replaceHue(HueLight light) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_framelayout, new DetailFragmentHue(light), DetailFragmentHue.TAG)
                .addToBackStack(DetailFragmentHue.TAG)
                .commit();
    }

    @Override
    public void replaceDim(DimLight light) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_framelayout, new DetailFragmentDim(light), DetailFragmentDim.TAG)
                .addToBackStack(DetailFragmentDim.TAG)
                .commit();
    }
}