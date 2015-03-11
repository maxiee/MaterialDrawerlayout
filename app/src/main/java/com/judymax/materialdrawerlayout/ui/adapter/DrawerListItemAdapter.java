package com.judymax.materialdrawerlayout.ui.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.judymax.materialdrawerlayout.R;

import java.util.ArrayList;

/**
 * Created by Maxiee on 2015/3/11.
 */
public class DrawerListItemAdapter extends BaseAdapter {

    private ArrayList<Item> data;
    private LayoutInflater mInflater;
    private int normalColor, highlightColor;

    private int selectdPosition;

    public DrawerListItemAdapter(Context context, ArrayList<Item> data,
                                 @ColorRes int normalColorId, @ColorRes int highlightColorId) {
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.normalColor = context.getResources().getColor(normalColorId);
        this.highlightColor = context.getResources().getColor(highlightColorId);
        this.selectdPosition = 0;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Item getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getSelectdPosition() {
        return  selectdPosition;
    }

    public void setSelectedPosition(int selectdPosition) {
        this.selectdPosition = selectdPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.drawer_list_item, parent, false);

            holder = new ViewHolder();
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).title);
        try {
            holder.iv_icon.setImageResource(getItem(position).iconId);
        } catch (Exception e) {
            holder.iv_icon.setImageResource(R.drawable.ic_launcher);
        }

        if (position != selectdPosition) {
            try {
                holder.iv_icon.setColorFilter(normalColor, PorterDuff.Mode.MULTIPLY);
            } catch (Exception e) {}
        } else {
            holder.tv_title.setTextColor(highlightColor);
            try {
                holder.iv_icon.setColorFilter(highlightColor, PorterDuff.Mode.MULTIPLY);
            } catch (Exception e) {}
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView iv_icon;
        public TextView tv_title;
    }

    public static class Item {

        public int iconId;
        public String title;

        public Item(String title, @DrawableRes int iconId) {
            this.title = title;
            this.iconId = iconId;
        }
    }
}
