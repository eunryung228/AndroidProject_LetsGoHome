package com.example.letsgohome;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ImageSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator
{
    private final Drawable drawable;
    private ArrayList<CalendarDay> dates;
    private Context cont;

    public EventDecorator(Context context)
    {
        cont=context;
        drawable=context.getResources().getDrawable(R.drawable.home);
        this.dates=new ArrayList<>();
    }

    public void AddItem(CalendarDay day)
    {
        dates.add(day);
    }
    public void RemoveItem(CalendarDay day)
    {
        dates.remove(day);
    }

    public boolean isIn(CalendarDay day)
    {
        return dates.contains(day);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day)
    {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view)
    {
        view.addSpan(new ImageSpan(cont, R.drawable.home));
    }
}