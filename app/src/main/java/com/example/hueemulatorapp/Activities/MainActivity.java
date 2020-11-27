package com.example.hueemulatorapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hueemulatorapp.Data.Lamp;
import com.example.hueemulatorapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        FrameLayout mainframe = findViewById(R.id.main_framelayout);
        mainframe.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        Fragment mainFragment = new MainFragment();
        Fragment detailFragment = new DetailFragment(new Lamp("id_placeholder", "lightbulb", "Restroom light", "Model ID", "Dim light", 120, 32767, 200, Lamp.EFFECT_NULL));


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_framelayout, detailFragment, DetailFragment.TAG)
                .addToBackStack(DetailFragment.TAG)
                .commit();

//        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, mainFragment, MainFragment.TAG)
//                .addToBackStack(MainFragment.TAG)
//                .commit();
    }
}