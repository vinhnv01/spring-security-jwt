package com.example.usermanagerment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nguyễn Vinh
 */
public class ConverDateToLong {

    public Long covert(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
        long milliseconds = 0;
        try {
            Date d = format.parse(stringDate);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return milliseconds;
    }
}
