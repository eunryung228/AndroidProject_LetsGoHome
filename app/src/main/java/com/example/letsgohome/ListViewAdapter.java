package com.example.letsgohome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter implements ListAdapter
{
    private ArrayList<ListViewItem> listViewItemList=new ArrayList<ListViewItem>();

    public ListViewAdapter()
    {
    }

    public ArrayList<String> getList()
    {
        ArrayList<String> myList=new ArrayList<String>();
        for(int i=0; i<getCount(); i++)
        {
            myList.add(listViewItemList.get(i).getStation());
        }
        return myList;
    }

    public ArrayList<String> getRegionList()
    {
        ArrayList<String> regionList=new ArrayList<String>();
        for(int i=0; i<getCount(); i++)
        {
            regionList.add(listViewItemList.get(i).getRegion());
        }
        return regionList;
    }

    public String getStationName(int position)
    {
        return listViewItemList.get(position).getStation();
    }

    @Override
    public int getCount()
    {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listViewItemList.get(position);
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
            convertView=inflater.inflate(R.layout.listview_items, parent, false);
        }

        TextView regionTextView=(TextView) convertView.findViewById(R.id.regionName);
        TextView stationTextView=(TextView) convertView.findViewById(R.id.stationName);
        ListViewItem listViewItem=listViewItemList.get(position);
        regionTextView.setText(listViewItem.getRegion());
        stationTextView.setText(listViewItem.getStation());

        Button btnDelete=(Button) convertView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listViewItemList.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public boolean addItem(String region, String station)
    {
        if(listViewItemList.size()==3)
        {
            return false;
        }
        else
        {
            ListViewItem item=new ListViewItem();

            item.setRegion(region);
            item.setStation(station);

            listViewItemList.add(item);
            this.notifyDataSetChanged();

            return true;
        }
    }
}
