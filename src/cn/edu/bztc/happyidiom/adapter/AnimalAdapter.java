package cn.edu.bztc.happyidiom.adapter;

import java.util.List;

import cn.edu.bztc.happyidiom.dao.CollectDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidion.activity.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalAdapter extends ArrayAdapter<Animal>{

	private int resourceId;
	private Context context;
	public AnimalAdapter(Context context, int resource,List<Animal> objects) {
		super(context,resource, objects);
		this.context=context;
		resourceId=resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Animal animal =getItem(position);//��ȡ��ǰ���Animalʵ��
			Log.i("MainActivity","name:"+animal.getName()+",id:"+animal.getId());
		View view;
		ViewHolder viewHolder;
		if(convertView==null){//�ж��Ƿ��һ�����У����������룬���������Ļ��������convertView
			view=LayoutInflater.from(getContext()).inflate(resourceId,null);
			viewHolder=new ViewHolder();
			viewHolder.tvName=(TextView)view.findViewById(R.id.tvName);
			viewHolder.btnSave=(ImageButton)view.findViewById(R.id.btnSave);
			viewHolder.btnSave.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					CollectDao collectDao=new CollectDao(context);
					Log.i("MainActivity","Collectname:"+animal.getName()+",Collectid:"+animal.getId());
					collectDao.add(animal);
					Toast.makeText(context,"�ղسɹ�",Toast.LENGTH_SHORT).show();
				}
			});
			view.setTag(viewHolder);
		}else{//������ǵ�һ�����У�convertView��Ϊ�գ�ֱ��ȡ����ֵ��view
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.tvName.setText(animal.getName());//��ʾ����
		return view;
	}
	class ViewHolder{
		TextView tvName;
		ImageButton btnSave;
	}
}
