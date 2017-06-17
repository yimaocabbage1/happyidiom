package cn.edu.bztc.happyidiom.util;

import cn.edu.bztc.happyidion.activity.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogUtil {
	public static void showDialog(String result,Context context){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		LayoutInflater layoutInflater=LayoutInflater.from(context);
		View view=layoutInflater.inflate(R.layout.dialog_info,null);
		builder.setView(view);
		TextView tvdiomInfo=(TextView)view.findViewById(R.id.tvIdiomInfo);
		tvdiomInfo.setText(result);//����֮ǰ�������ʾ���
		builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();//�Ի���ر�
			}
		});
		builder.create().show();//�򿪶Ի���
	}
}
