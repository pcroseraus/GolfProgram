/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.utils;

import com.myapps.golfprogram.golfprogram.exceptions.DayOutOfRangeException;
import com.myapps.golfprogram.golfprogram.exceptions.MonthOutOfRangeException;
import com.myapps.golfprogram.golfprogram.exceptions.YearOutOfRangeException;
import java.util.Calendar;

/**
 *
 * @author Brett
 */
public class DateUtils {

    public DateUtils() {
        
    }
    
    public int validateDay(int month, int year, String dayItem) throws DayOutOfRangeException {
        int day = 0;

        if (dayItem != null && !dayItem.isEmpty()) {
            try {
                day = Integer.parseInt(dayItem.trim());
                int maxDays = getNumberOfDaysInMonth(month, year, day);
                if (day < 0 || day > maxDays) {
                    throw new DayOutOfRangeException("Day is outside of the valid range");
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Day is not parsable");
            }
        }
        return day;
    }
    
    public int validateMonth(String monthItem) throws MonthOutOfRangeException {
        int month = 0;
        if(monthItem != null && !monthItem.isEmpty()) {
            try{
                month = Integer.parseInt(monthItem);
                if(month < 1 || month > 12){
                    throw new MonthOutOfRangeException("Month is not in the range of 1 to 12");
                }
            } catch(NumberFormatException e ){
                throw new NumberFormatException("Month is not parsable");
            }
        }
        return month;
    }
    
    public int validateYear(String yearItem) throws YearOutOfRangeException {
        int year = 0;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (yearItem != null && !yearItem.isEmpty()) {
            try {
                year = Integer.parseInt(yearItem.trim());
                if (year < 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                    throw new YearOutOfRangeException("Year is outside of the valid range");
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Year is not parsable");
            }
        }        
        return year;
    }
 
    private int getNumberOfDaysInMonth(int month, int year, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day );
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
}
