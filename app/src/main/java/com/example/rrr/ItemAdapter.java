package com.example.rrr;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20/1/2018.
 */

public class ItemAdapter extends ArrayAdapter<Items>  {
    private ArrayList<Items> list = new ArrayList<>();


    public ItemAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    public void add(Items object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Items getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        ItemHolder itemHolder;
        row = convertView;
        

        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_item,parent,false);
            itemHolder = new ItemHolder();

            itemHolder.tx_date = (TextView) row.findViewById(R.id.item_date);
            itemHolder.tx_category = (TextView) row.findViewById(R.id.item_category);
            itemHolder.tx_description = (TextView) row.findViewById(R.id.item_description);
            itemHolder.tx_condition = (TextView) row.findViewById(R.id.item_condition);
            itemHolder.tx_img = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(itemHolder);
        }
        else {
            itemHolder=(ItemHolder)row.getTag();
        }
        Items items = (Items)this.getItem(position);

        itemHolder.tx_date.setText(items.getDate());
        itemHolder.tx_category.setText(items.getCategory());
        itemHolder.tx_description.setText(items.getDescription());
        itemHolder.tx_condition.setText(items.getCondition());
        itemHolder.tx_img = (ImageView) row.findViewById(R.id.imageView);

        Picasso.with(this.getContext()).load(items.getImgUrl()).fit().into(itemHolder.tx_img);

        return row;
    }


    class ItemHolder
    {
        TextView  tx_date, tx_category, tx_description, tx_condition;
        ImageView tx_img;
    }
}
