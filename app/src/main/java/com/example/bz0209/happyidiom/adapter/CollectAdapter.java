package com.example.bz0209.happyidiom.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bz0209.happyidiom.R;
import com.example.bz0209.happyidiom.dao.CollectDao;
import com.example.bz0209.happyidiom.entity.Animal;
import com.example.bz0209.happyidiom.minterface.ChangeListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class CollectAdapter extends ArrayAdapter<Animal> {
    private  int resourceID;
    //private List<Animal> list = new ArrayList<Animal>();
   private  List<Animal> list;
    private Context context;
    private CollectDao collectDao;
    private ChangeListData mChangeListData;

    public CollectAdapter(Context context, int resource, List<Animal> objects,CollectDao collectDao) {
        super(context, resource, objects);
        this.context=context;
        resourceID=resource;
        list=objects;
        this.collectDao=collectDao;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  Animal  animal=getItem(position);
        //final  Animal  a=list.get(position);
        //collectDao=new CollectDao(context);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.btnDel=(ImageButton)view.findViewById(R.id.btnDel);
            viewHolder.tvName=(TextView)view.findViewById(R.id.tvName);
            view.setTag(viewHolder);

            viewHolder.btnDel.setOnClickListener(new View.OnClickListener(){
              /*  public void onClick(View v){
                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setTitle("确定要删除吗");
                    builder.setPositiveButton("确定",new android.content.DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialogInterface, int which){
                            list.remove(animal);
                            collectDao.DeleteData(animal.getId());
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            if (mChangeListData != null)
                                mChangeListData.changedata();
                            //list.clear();
                            //collectDao.QueryData(list);
                           // notifyDataSetChanged();
                        }
                    });
                    builder.setNeutralButton("取消",null);
                    builder.show();*/
                @Override
                    public void onClick(View arg0) {
                    /* 弹出提示框 */
                        showDialog();
                    }

                /* 弹出提示框 */
                private void showDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("确定删除？");// 设置内容
                    /* 添加对话框中确定按钮和点击事件 */
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            CollectDao collectDao = new CollectDao(context);
                            collectDao.DeleteData(animal.getId());
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            list.clear();
                            collectDao.QueryData(list);
                           notifyDataSetChanged();
                        }
                    });
                    /* 添加对话框中取消按钮和点击事件 */
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog dialog = builder.create();// 获取dialog
                    dialog.show();// 显示对话框



                }
            });

        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();


        }
        viewHolder.tvName.setText(animal.getName());
        viewHolder.btnDel.setFocusable(false);
        viewHolder.btnDel.setFocusableInTouchMode(false);
        return view;

    }
    class ViewHolder{
        TextView tvName;
        ImageButton btnDel;
    }



}

