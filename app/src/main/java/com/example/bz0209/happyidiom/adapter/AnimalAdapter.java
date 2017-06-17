package com.example.bz0209.happyidiom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.dao.CollectDao;
import com.example.bz0209.happyidiom.entity.Animal;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class AnimalAdapter extends ArrayAdapter<Animal> {
    private int resourceId;
    private Context context;
    public AnimalAdapter(Context context, int resource, List<Animal> objects) {
        super(context, resource, objects);
        this.context = context;
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Animal animal = getItem(position); // 获取当前项的Animal实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName= (TextView) view
                    .findViewById(R.id.tvName);
            viewHolder.btnSave = (ImageButton) view
                    .findViewById(R.id.btnSave);
            viewHolder.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CollectDao collectDao=new CollectDao(context);
                    collectDao.AddData(animal);
                    Toast.makeText(context, "收藏成功" + animal.getName() + "", Toast.LENGTH_SHORT).show();

                }
            });

            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.tvName.setText(animal.getName());
        viewHolder.btnSave.setFocusable(false);
        viewHolder.btnSave.setFocusableInTouchMode(false);

        return view;
    }
    class ViewHolder {
        TextView tvName;
        ImageButton btnSave;
    }
}

