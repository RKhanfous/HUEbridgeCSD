package com.example.hueemulatorapp.Activities;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hueemulatorapp.R;

public class MainFragment extends Fragment {

    public static final String TAG = "MAIN_FRAGMENT";

    private RecyclerView listLamps;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        

        return inflater.inflate(R.layout.main_fragment, container, false);
    }
}
