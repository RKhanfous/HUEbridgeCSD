package com.example.hueemulatorapp.Activities;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hueemulatorapp.Application.ApiHueAdapter;
import com.example.hueemulatorapp.Application.HttpClient;
import com.example.hueemulatorapp.Application.JsonData;
import com.example.hueemulatorapp.Data.Lamp;
import com.example.hueemulatorapp.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainFragment extends Fragment implements OnItemClickListener{

    public static final String TAG = "MAIN_FRAGMENT";

    private RecyclerView listLampsRV;
    private ArrayList<Lamp> lampList;
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
        lampList = new ArrayList<>();
        listLampsRV = container.findViewById(R.id.lampListRV);
        apiHueAdapter = new ApiHueAdapter(lampList, this);
        listLampsRV.setAdapter(apiHueAdapter);
        listLampsRV.setLayoutManager(new LinearLayoutManager(this.context));

        this.httpClient = new HttpClient();
        try {
            Request requestGetLights = httpClient.get(JsonData.uri + JsonData.lights);
            httpClient.send(requestGetLights, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("I DO GET HERE");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        List<Lamp> lamps = JsonData.readGetLightsResponse(response.body().string());
                        //lamps.add(new Lamp("id", ""));
                        onLightsAvailable(lamps);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
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

    public void onLightsAvailable(List<Lamp> lamps) {
        lampList.addAll(lamps);
        apiHueAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int clickedPosition) {
        replacer.replace(lampList.get(clickedPosition));
        Log.d(MainFragment.class.getName(), "Clicked on item no. " + clickedPosition);
    }
}
