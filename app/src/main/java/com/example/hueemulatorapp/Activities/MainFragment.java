package com.example.hueemulatorapp.Activities;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hueemulatorapp.Application.HttpListener;
import com.example.hueemulatorapp.Data.Lamp;
import com.example.hueemulatorapp.R;

import java.util.List;

public class MainFragment extends Fragment implements HttpListener {

    public static final String TAG = "MAIN_FRAGMENT";

    private RecyclerView listLampsRV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        listLampsRV = container.findViewById(R.id.lampListRV);

        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onLightsAvailable(List<Lamp> lamps) {
        
    }
}
