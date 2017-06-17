package com.example.bz0209.happyidiom.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bz0209.happyidiom.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class DialogUtil {
    public static void showDialog(String result,Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_info,null);
        builder.setView(view);
        TextView tvIdiomInfo = (TextView) view.findViewById(R.id.tvIdiomInfo);
        tvIdiomInfo.setText(result);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
