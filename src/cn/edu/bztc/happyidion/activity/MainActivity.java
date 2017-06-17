package cn.edu.bztc.happyidion.activity;

import cn.edu.bztc.happyidiom.db.MyDatabaseHelper;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	private TabHost tabHost;// �������ؼ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȡ��������
		setContentView(R.layout.activity_main);

		createCollectDatabase();
		tabHost = getTabHost(); // ��ȡTabHostʵ��
		/* һ��Tab��Ӧһ��name���ƣ�һ�����⣬һ��ͼ�꣬yigeActivityҳ�� */
		// addTab("study",R.string.title_study,R.drawable.study,StudyActivity.class);
		// addTab("search",R.string.title_search,R.drawable.search,SearchActivity.class);
		// addTab("game",R.string.title_game,R.drawable.game,GameActivity.class);
		// addTab("save",R.string.title_save,R.drawable.save,CollectActivity.class);
		// addTab("help",R.string.title_help,R.drawable.help,StudyActivity.class);

		tabHost.addTab(tabHost.newTabSpec("study")
				.setIndicator("ѧϰ",getResources().getDrawable(R.drawable.study))
				.setContent(new Intent(this, StudyActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("search")
				.setIndicator("����",getResources().getDrawable(R.drawable.search))
				.setContent(new Intent(this, SearchActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("game")
				.setIndicator("��Ϸ",getResources().getDrawable(R.drawable.game))
				.setContent(new Intent(this, GameActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("save")
				.setIndicator("�ղ�",getResources().getDrawable(R.drawable.save))
				.setContent(new Intent(this, CollectActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("help")
				.setIndicator("����",getResources().getDrawable(R.drawable.help))
				.setContent(new Intent(this, StudyActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

	}

	/* �����ղ����ݿ� */
	private void createCollectDatabase() {
		MyDatabaseHelper mydatabaseHelper = new MyDatabaseHelper(
				MainActivity.this);
		mydatabaseHelper.getWritableDatabase();
	}

	/* ����ÿ��Tab����ʾ���� */
	private void addTab(String tag, int title_introduction, int title_icon,
			Class ActivityClass) {
		tabHost.addTab(tabHost
				.newTabSpec(tag)
				.setIndicator(getString(title_introduction),
						getResources().getDrawable(title_icon))
				.setContent(new Intent(this, ActivityClass)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
