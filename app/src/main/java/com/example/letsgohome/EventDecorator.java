package com.example.letsgohome;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator
{
    private final int color;
    private ArrayList<CalendarDay> dates;

    public EventDecorator(int color)
    {
        this.color=color;
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
        view.addSpan(new DotSpan(10, color));
    }
}
