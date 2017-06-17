package cn.edu.bztc.happyidiom.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bztc.happyidiom.dao.CollectDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidiom.minterface.ChangeListData;
import cn.edu.bztc.happyidion.activity.CollectActivity;
import cn.edu.bztc.happyidion.activity.MainActivity;
import cn.edu.bztc.happyidion.activity.R;

public class CollectAdapter extends ArrayAdapter<Animal> {

	private int resourceId;
	private Context context;
	private ChangeListData mChangeListData;

	public CollectAdapter(Context context, int resource, List<Animal> objects) {
		super(context, resource, objects);
		this.context = context;
		resourceId = resource;
	}

	public ChangeListData getChangeListData() {
		return mChangeListData;
	}

	public void setChangeListData(ChangeListData mChangeListData) {
		this.mChangeListData = mChangeListData;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Animal animal = getItem(position);// ��ȡ��ǰ���Animalʵ��
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {// �ж��Ƿ��һ�����У����������룬���������Ļ��������convertView
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
			viewHolder.btnDel = (ImageButton) view.findViewById(R.id.btnDel);
			viewHolder.btnDel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					/* ������ʾ�� */
					showDialog();
				}

				/* ������ʾ�� */
				private void showDialog() {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setMessage("ȷ��ɾ����");// ��������
					/* ��ӶԻ�����ȷ����ť�͵���¼� */
					builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							CollectDao collectDao = new CollectDao(context);
							collectDao.del(animal.getId());
							Toast.makeText(context, "ɾ���ɹ�", Toast.LENGTH_SHORT).show();
							if (mChangeListData != null)
								mChangeListData.changedata();
						}
					});
					/* ��ӶԻ�����ȡ����ť�͵���¼� */
					builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
						}
					});
					AlertDialog dialog = builder.create();// ��ȡdialog
					dialog.show();// ��ʾ�Ի���
				}
			});
			view.setTag(viewHolder);
		} else {// ������ǵ�һ�����У�convertView��Ϊ�գ�ֱ��ȡ����ֵ��view
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.tvName.setText(animal.getName());// ��ʾ����
		return view;
	}

	class ViewHolder {
		TextView tvName;
		ImageButton btnDel;
	}
}
