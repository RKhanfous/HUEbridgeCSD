package com.example.hueemulatorapp.Activities;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hueemulatorapp.Application.ApiHueAdapter;
import com.example.hueemulatorapp.Application.GetLightsCallback;
import com.example.hueemulatorapp.Application.HttpClient;
import com.example.hueemulatorapp.Application.HttpListener;
import com.example.hueemulatorapp.Application.JsonData;
import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HueLight;
import com.example.hueemulatorapp.Data.Lamp;
import com.example.hueemulatorapp.Data.Light;
import com.example.hueemulatorapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainFragment extends Fragment implements OnItemClickListener, HttpListener{

    public static final String TAG = "MAIN_FRAGMENT";

    private RecyclerView listLampsRV;
    private ArrayList<DimLight> lightList;
    private ApiHueAdapter apiHueAdapter;

    private HttpClient httpClient;

    private Context context;
    private DetailFragmentReplacer replacer;

    private ViewGroup container;

    public MainFragment(DetailFragmentReplacer replacer, Context context){
        this.replacer = replacer;
        this.context = context;
    }

    private void init(){
        lightList = new ArrayList<>();
        listLampsRV = container.findViewById(R.id.lampListRV);
        apiHueAdapter = new ApiHueAdapter(lightList, this);
        listLampsRV.setAdapter(apiHueAdapter);
        listLampsRV.setLayoutManager(new LinearLayoutManager(this.context));

        this.httpClient = new HttpClient();

        final SwipeRefreshLayout swipeRefreshLayout = container.findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary), ContextCompat.getColor(context, R.color.colorSecondary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadLights();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        loadLights();

    }
    private void reloadLights(){
        this.lightList = new ArrayList<>();
        apiHueAdapter = new ApiHueAdapter(lightList, this);
        listLampsRV.setAdapter(apiHueAdapter);
        loadLights();
    }

    private void loadLights(){
        try {
            Request requestGetLights = httpClient.get(JsonData.uri + JsonData.lights);
            httpClient.send(requestGetLights, new GetLightsCallback(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onStart() {
        this.init();
        super.onStart();
    }

    @Override
    public void onLightsAvailable(List<DimLight> lights) {
        lightList.addAll(lights);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiHueAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int clickedPosition) {
        DimLight selected = lightList.get(clickedPosition);

        if (selected.getType().equals(Light.TYPE_HUE)){
            replacer.replaceHue((HueLight)lightList.get(clickedPosition));
        } else if (selected.getType().equals(Light.TYPE_DIM)){
            replacer.replaceDim(lightList.get(clickedPosition));
        }
        Log.d(MainFragment.class.getName(), "Clicked on item no. " + clickedPosition);
    }
}
