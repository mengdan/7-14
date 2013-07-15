package cn.com.csuguide.activity;
import cn.com.csuguide.R;
import cn.com.csuguide.model.CheckThread;
import cn.com.csuguide.model.LoginThread;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.DialogFactory;
import cn.com.csuguide.util.SharePreferenceUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	private Button mBtnLogin;//��½��ť
	private EditText mAccounts, mPassword;
	private CheckBox mAutoSavePassword;//�Զ���½
	private TextView Result;//��ʾ��¼������Ϣ
	String accounts;//�û���
	String password;//����
	DemoApplication application;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Result = (TextView) findViewById(R.id.textView1);
		application=(DemoApplication) this.getApplicationContext();
		application.addActivity(this);
		initView();
	}
	@Override
	protected void onResume() {// ��onResume�����������ж������Ƿ����
		super.onResume();
		if (!isNetworkAvailable()) {
			toast(this);
		} 
	}


	public void onClick(View v) {
		submit();
	}
	
	/**
	 * �ύ�˺�������Ϣ��������
	 */
	private void submit() {
		 accounts = mAccounts.getText().toString();
		 password = mPassword.getText().toString();
		if (accounts.length() == 0 || password.length() == 0) {
			DialogFactory.ToastDialog(this, "�������ֵ�¼", "�ף��ʺŻ����벻��Ϊ��Ŷ");
		} else {
			if (!isNetworkAvailable()) {
				toast(this);
			} else{
			showRequestDialog();
				//��������������֣���֤�˺�
		       System.out.println("��ʼ���ӷ�����...");
				//�����߳�
				LoginThread lt=new LoginThread(LoginActivity.this,accounts, password);
				lt.start();
				
				CheckThread ct=new CheckThread(this);
				ct.start();
				
//				if(accounts.equals("0909103303")&&password.equals("123456")){
//					mDialog.dismiss();
//					Toast.makeText(this, "��½�ɹ�������ʹ�����Ѳ�ѯ���༶ͬѧ��ѯ����", Toast.LENGTH_LONG).show();
//					//��ת��������
//					DemoApplication.isLogin=true;
//					SharePreferenceUtil sharePreferenceUtil=new SharePreferenceUtil(this, Config.SAVE_USER);
//		        	sharePreferenceUtil.setId(accounts);
//		        	sharePreferenceUtil.setPasswd(password);
//		        	try {
//						Thread.sleep(500);//�߳�����0.5��
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}//
//					Intent intent=new Intent(this,MainFuncActivity.class);
//		        	startActivity(intent);
//				}
				

		        System.out.println("loginactivity---->end");
			}
			
		}
	}
/**
 * �����¼��ť�󣬵�����֤�Ի���
 */
private Dialog mDialog = null;

private void showRequestDialog() {
	if (mDialog != null) {
		mDialog.dismiss();
		mDialog = null;
	}
	mDialog = DialogFactory.creatRequestDialog(this, "������֤�˺�...");
	mDialog.show();
}
	public void initView() {
		mAutoSavePassword = (CheckBox) findViewById(R.id.auto_save_password);
		mBtnLogin = (Button) findViewById(R.id.login_btn);
		mBtnLogin.setOnClickListener(this);
		mAccounts = (EditText) findViewById(R.id.lgoin_accounts);
		mPassword = (EditText) findViewById(R.id.login_password);
		if (mAutoSavePassword.isChecked()) {
			SharePreferenceUtil util = new SharePreferenceUtil(
					LoginActivity.this, Config.SAVE_USER);
			mAccounts.setText(util.getId());
			mPassword.setText(util.getPasswd());
			Toast.makeText(this, "��½�ɹ�������ʹ�����Ѳ�ѯ���༶ͬѧ��ѯ����", Toast.LENGTH_LONG).show();
			DemoApplication.isLogin=true;
			Intent intent=new Intent(this,MainFuncActivity.class);
        	startActivity(intent);
		}
	}
	
	/**
	 * �ж��ֻ������Ƿ����
	 * 
	 * @param context
	 * @return
	 */
	private boolean isNetworkAvailable() {
		ConnectivityManager mgr = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	private void toast(Context context) {
		new AlertDialog.Builder(context)
				.setTitle("��ܰ��ʾ")
				.setMessage("�ף�������������δ��Ŷ")
				.setPositiveButton("ǰ����",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(
										android.provider.Settings.ACTION_WIRELESS_SETTINGS);
								startActivity(intent);
							}
						}).setNegativeButton("ȡ��", null).create().show();
	}
	
	
}
