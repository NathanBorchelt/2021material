package com.example.borcheltmerrychristmas.ui.countdown;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.borcheltmerrychristmas.ChristmasCountdown;
import com.example.borcheltmerrychristmas.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDownFragment extends Fragment {

    private CountDownViewModel countDownViewModel;
    private TextView txtTimerDay,txtTimerHour,txtTimerMinute,txtTimerSecond,txtUntilChristmas;
    private Handler handler;
    private Runnable runnable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countDownViewModel =
                new ViewModelProvider(this).get(CountDownViewModel.class);
        View root = inflater.inflate(R.layout.fragment_countdown, container, false);
        txtTimerDay = root.findViewById(R.id.txtTimerDay);
        txtTimerHour = root.findViewById(R.id.txtTimerHour);
        txtTimerMinute = root.findViewById(R.id.txtTimerMinute);
        txtTimerSecond = root.findViewById(R.id.txtTimerSecond);
        txtUntilChristmas = root.findViewById(R.id.untilChristmas);

        countDownStart();
        return root;
    }
    private void countDownStart(){
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    String[] timeUntil = ChristmasCountdown.getTimeLeft();
                    txtTimerDay.setText(timeUntil[0]);
                    txtTimerHour.setText(timeUntil[2]);
                    txtTimerMinute.setText(timeUntil[2]);
                    txtTimerSecond.setText(timeUntil[3]);
                    txtUntilChristmas.setText("Until Christmas ");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                handler.postDelayed(this,1000);
            }
        };
        handler.postDelayed(runnable,1000);
    }
}