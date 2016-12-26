package com.session8;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.calendarview.widget.DayView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    ArrayList dates = new ArrayList() ;
    private CalendarView calendarView;
    DateTime temp_date  ;
    boolean law_el_date_lwnoh_etshal = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = (CalendarView) findViewById(R.id.calendar_view);

        HashMap date1 = new HashMap() ;
        date1.put("year",2016);
        date1.put("month",12);
        date1.put("day",5);

        dates.add(date1) ;

        HashMap date2 = new HashMap() ;
        date2.put("year",2016);
        date2.put("month",11);
        date2.put("day",5);
        dates.add(date2) ;


        Calendar calendar = Calendar.getInstance();
         bind_dates(new DateTime(calendar.getTime()));

        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date date) {
                DateTime selected = new DateTime(date);
                int fo7lo2y = Color.parseColor("#5327af");
                if (law_el_date_lwnoh_etshal){
                    DayView dayView = calendarView.findViewByDate(temp_date.toDate()) ;
                    dayView.setBackgroundColor(fo7lo2y);
                    law_el_date_lwnoh_etshal = false ;
                }

                if (compare_dates(selected)){
                    DayView dayView = calendarView.findViewByDate(date) ;

                    dayView.setBackgroundColor(fo7lo2y);
                    temp_date = selected ;
                    law_el_date_lwnoh_etshal = true ;
                }

            }
        });
        calendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(@NonNull Date date) {
                DateTime changed_date = new DateTime(date);
              bind_dates(changed_date);
            }
        });

    }
    void bind_dates(DateTime current_date){

        for (int i=0; i<dates.size() ; i++){



            // getting hash map
            HashMap current = (HashMap)dates.get(i) ;
            // create clendar
            Calendar vacation = Calendar.getInstance() ;
            vacation.set(Calendar.YEAR ,(int)current.get("year") );
            int month = (int)current.get("month") ;
            vacation.set(Calendar.MONTH , month-1);
            vacation.set(Calendar.DAY_OF_MONTH ,  (int)current.get("day") );


         DateTime   vacation_2_date_time = new DateTime(vacation.getTime());
            //    Log.e("date " , aeed_el_3omal_cal.getTime() + "");


            /// on month changed

            // start of binding
            if (vacation_2_date_time.getMonthOfYear()==  current_date.getMonthOfYear() ){
                DayView day_in_cal = calendarView.findViewByDate(vacation_2_date_time.toDate());
                int fo7lo2y = Color.parseColor("#5327af");
                day_in_cal.setBackgroundColor(fo7lo2y);

            }
        }
    }


    boolean compare_dates(DateTime selected){
        DateTime date1 = selected ;
        for (int i=0; i<dates.size();i++){
            // getting hash map
            HashMap current = (HashMap)dates.get(i) ;
            // create clendar
            Calendar vacation = Calendar.getInstance() ;
            vacation.set(Calendar.YEAR ,(int)current.get("year") );
            int month = (int)current.get("month") ;
            vacation.set(Calendar.MONTH , month-1);
            vacation.set(Calendar.DAY_OF_MONTH ,  (int)current.get("day") );


            DateTime   vacation_2_date_time = new DateTime(vacation.getTime());
            //    Log.e("date " , aeed_el_3omal_cal.getTime() + "");


            DateTime date2 = vacation_2_date_time ;
            int year1 = date1.getYear();
            int year2 = date2.getYear();
            if (year1 != year2 )return false ;
            int month1 = date1.getMonthOfYear();
            int month2 = date2.getMonthOfYear();
            if (month1 != month2) return  false ;
            int day1 = date1.getDayOfMonth();
            int day2 = date2.getDayOfMonth();
            Log.e("dayes", day1 + "- "+ day2);
            if (day1 == day2) return  true ;
            return false ;
        }
        return false ;
    }
}
