package com.k4rnaj1k.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
    public static Date parse(String date) throws ParseException {
        String[] dateformats = {"dd/MM/yyyy", "MM-dd-yyyy", "yyyy/MM/dd"};
        Date res;
        DateFormat sdf;
        for (String format :
                dateformats) {
            try {
                sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                res = sdf.parse(date);
                if (res != null)
                    return res;
            } catch (ParseException ignored) {}
        }
        throw new ParseException("", 0);
    }
}
