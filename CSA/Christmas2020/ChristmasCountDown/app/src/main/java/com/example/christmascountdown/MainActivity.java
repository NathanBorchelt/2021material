package com.example.christmascountdown;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private TextView txtTimerDay,txtTimerHour,txtTimerMinute,txtTimerSecond,txtUntilChristmas;
    private Handler handler;
    private Runnable runnable;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimerDay = findViewById(R.id.txtTimerDay);
        txtTimerHour = findViewById(R.id.txtTimerHour);
        txtTimerMinute = findViewById(R.id.txtTimerMinute);
        txtTimerSecond = findViewById(R.id.txtTimerSecond);
        txtUntilChristmas = findViewById(R.id.untilChristmas);

        countDownStart();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
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