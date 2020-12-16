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
                    SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
                    Date today = new Date();
                    String dateTodayAsString = today.toString();
                    String[] yearDate = dateTodayAsString.split(" ");
                    //System.out.println(today.toString());
                    //System.out.println(yearDate[yearDate.length-1]);
                    String dateAsStringForChristmas = yearDate[yearDate.length - 1] + "-12-25";
                    Date christmas = dateTime.parse(dateAsStringForChristmas);
                    long diffTime = christmas.getTime() - today.getTime();
                    long days = diffTime / (24 * 60 * 60 * 1000);
                    diffTime -= days * (24 * 60 * 60 * 1000);
                    long hours = diffTime / (60 * 60 * 1000);
                    diffTime -= hours * (60 * 60 * 1000);
                    long minutes = diffTime / (60 * 1000);
                    diffTime -= minutes * (60 * 1000);
                    long seconds = diffTime / 1000;
                    String dayOutput = " " + String.format("%02d", days);
                    String hourOutput = " " + String.format("%02d", hours);
                    String minuteOutput = " " + String.format("%02d", minutes);
                    String secondOutput = " " + String.format("%02d", seconds);
                    txtTimerDay.setText(dayOutput);
                    txtTimerHour.setText(hourOutput);
                    txtTimerMinute.setText(minuteOutput);
                    txtTimerSecond.setText(secondOutput);
                    txtUntilChristmas.setText("Until Christmas " + yearDate[yearDate.length - 1]);
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