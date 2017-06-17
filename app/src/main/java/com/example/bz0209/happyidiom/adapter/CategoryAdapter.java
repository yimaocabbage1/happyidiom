package com.example.bz0209.happyidiom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.entity.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    private int resourceId;

    public CategoryAdapter(Context context, int resource, List<Category> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = getItem(position); // 获取当前项的Category实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.categoryImage = (ImageView) view
                    .findViewById(R.id.category_image);
            viewHolder.categoryName = (TextView) view
                    .findViewById(R.id.category_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.categoryImage.setImageResource(category.getImageId());
        viewHolder.categoryName.setText(category.getName());
        return view;
    }
    class ViewHolder {
        ImageView categoryImage;
        TextView categoryName;
    }

}

