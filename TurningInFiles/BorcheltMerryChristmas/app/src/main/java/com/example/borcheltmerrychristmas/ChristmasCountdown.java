package com.example.borcheltmerrychristmas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChristmasCountdown {
    public ChristmasCountdown() {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String dateTodayAsString = today.toString();
        String[] yearDate = dateTodayAsString.split(" ");
        //System.out.println(today.toString());
        //System.out.println(yearDate[yearDate.length-1]);
        String dateAsStringForChristmas = yearDate[yearDate.length - 1] + "-12-25";
        Date christmas = null;
        try {
            christmas = dateTime.parse(dateAsStringForChristmas);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
    }
    public static String[] getTimeLeft() throws ParseException {
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

        return new String[]{dayOutput, hourOutput, minuteOutput, secondOutput};

    }
}
