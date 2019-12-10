package com.example.letsgohome.ktx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.letsgohome.R;

import java.util.ArrayList;

public class KTXListViewAdapter extends BaseAdapter implements ListAdapter
{
    private ArrayList<KTXListViewItem> KTXList=new ArrayList<KTXListViewItem>();

    public KTXListViewAdapter()
    {
    }

    @Override
    public int getCount()
    {
        return KTXList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return KTXList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Context context=parent.getContext();

        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.ktxlistview_items, parent, false);
        }

        TextView vehTextView=(TextView) convertView.findViewById(R.id.vehicle);
        TextView depTextView=(TextView) convertView.findViewById(R.id.depTime);
        TextView arrTextView=(TextView) convertView.findViewById(R.id.arrTime);
        TextView chargeTextView=(TextView) convertView.findViewById(R.id.charge);

        KTXListViewItem ktxListViewItem=KTXList.get(position);
        vehTextView.setText(ktxListViewItem.getVehicle());
        depTextView.setText(ktxListViewItem.getDepTime());
        arrTextView.setText(ktxListViewItem.getArrTime());
        chargeTextView.setText(ktxListViewItem.getCharge());

        return convertView;
    }

    public void addItem(KTXListViewItem item)
    {
        KTXList.add(item);
        this.notifyDataSetChanged();
    }
    /*
    public void addItem(String vehicle, String depPlace, String dep, String arrPlace, String arr, String charge)
    {
        KTXListViewItem item=new KTXListViewItem();

        item.setVehicle(vehicle);

        String depart="";
        String arrive="";
        String ch="";

        depart=dep.substring(8, 12);
        depart+=" "+depPlace;
        item.setDepTime(depart);

        arrive=arr.substring(8, 12);
        arrive+=" "+arrPlace;
        item.setArrTime(arrive);

        charge+="원";
        item.setCharge(charge);

        KTXList.add(item);
        this.notifyDataSetChanged();
    }
     */
}