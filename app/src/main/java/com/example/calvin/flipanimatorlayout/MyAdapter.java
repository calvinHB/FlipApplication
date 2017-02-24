package com.example.calvin.flipanimatorlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by calvin on 17/2/21.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    public MyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setList(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList==null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        if(position<0 || position>=getCount())
            return null;

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if(convertView==null)
        {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_layout, parent, false);
            mHolder.mContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(mHolder);
        }else
            mHolder = (ViewHolder) convertView.getTag();

        mHolder.mContent.setText((String) getItem(position));
        return convertView;
    }

    private static class ViewHolder
    {
        TextView mContent;
    }
}
