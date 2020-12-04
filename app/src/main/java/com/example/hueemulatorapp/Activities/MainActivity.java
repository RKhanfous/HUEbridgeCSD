package com.example.hueemulatorapp.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hueemulatorapp.Data.DimLight;
import com.example.hueemulatorapp.Data.HttpParser;
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

        ImageButton buttonPort = findViewById(R.id.button_portmenu);
        buttonPort.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showPortDialog();
            }
        });

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

    public void showPortDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.port_menu,  null);
        dialogBuilder.setView(dialogView);
        final EditText editPort = dialogView.findViewById(R.id.editText_port);

        dialogBuilder.setTitle(getResources().getString(R.string.app_name));
        dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HttpParser.getInstance().setPort(Integer.parseInt(editPort.getText().toString()));
                Toast.makeText(MainActivity.this, "Port changed to " + editPort.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Canceled changing ports", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}