package com.example.finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DateUtils {
    public static final long DAYS_IN_MONTHS = 30;
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;

    public static Long getTimestamp(String dateStr) throws ParseException {
        Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
        return date.getTime();
    }

    public static String timestampToDateString(Long timestamp) {
        return new SimpleDateFormat(DATE_FORMAT).format(timestamp);
    }

    public static ArrayList<String> getPreviousDaysFromNow() {
        Date today = new Date();
        var dates = new ArrayList<String>();
        var todayTimestamp = today.getTime();
        dates.add(timestampToDateString(todayTimestamp));
        for (int day = 1; day <= DAYS_IN_MONTHS; day++) {
            dates.add(getPreviousDay(todayTimestamp, day));
        }

        return dates;
    }

    private static String getPreviousDay(long todayTimestamp, long amount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final long total = amount * ONE_DAY_MILLI_SECONDS;
        long previousDayMilliSeconds = todayTimestamp - total;
        Date previousDate = new Date(previousDayMilliSeconds);
        return dateFormat.format(previousDate);
    }

}
