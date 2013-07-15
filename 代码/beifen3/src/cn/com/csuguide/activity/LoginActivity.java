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
	private Button mBtnLogin;//登陆按钮
	private EditText mAccounts, mPassword;
	private CheckBox mAutoSavePassword;//自动登陆
	private TextView Result;//显示登录处理信息
	String accounts;//用户名
	String password;//密码
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
	protected void onResume() {// 在onResume方法里面先判断网络是否可用
		super.onResume();
		if (!isNetworkAvailable()) {
			toast(this);
		} 
	}


	public void onClick(View v) {
		submit();
	}
	
	/**
	 * 提交账号密码信息到服务器
	 */
	private void submit() {
		 accounts = mAccounts.getText().toString();
		 password = mPassword.getText().toString();
		if (accounts.length() == 0 || password.length() == 0) {
			DialogFactory.ToastDialog(this, "中南助手登录", "亲！帐号或密码不能为空哦");
		} else {
			if (!isNetworkAvailable()) {
				toast(this);
			} else{
			showRequestDialog();
				//与服务器交互部分，验证账号
		       System.out.println("开始连接服务器...");
				//创建线程
				LoginThread lt=new LoginThread(LoginActivity.this,accounts, password);
				lt.start();
				
				CheckThread ct=new CheckThread(this);
				ct.start();
				
//				if(accounts.equals("0909103303")&&password.equals("123456")){
//					mDialog.dismiss();
//					Toast.makeText(this, "登陆成功！可以使用室友查询，班级同学查询功能", Toast.LENGTH_LONG).show();
//					//跳转到主界面
//					DemoApplication.isLogin=true;
//					SharePreferenceUtil sharePreferenceUtil=new SharePreferenceUtil(this, Config.SAVE_USER);
//		        	sharePreferenceUtil.setId(accounts);
//		        	sharePreferenceUtil.setPasswd(password);
//		        	try {
//						Thread.sleep(500);//线程休眠0.5秒
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
 * 点击登录按钮后，弹出验证对话框
 */
private Dialog mDialog = null;

private void showRequestDialog() {
	if (mDialog != null) {
		mDialog.dismiss();
		mDialog = null;
	}
	mDialog = DialogFactory.creatRequestDialog(this, "正在验证账号...");
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
			Toast.makeText(this, "登陆成功！可以使用室友查询，班级同学查询功能", Toast.LENGTH_LONG).show();
			DemoApplication.isLogin=true;
			Intent intent=new Intent(this,MainFuncActivity.class);
        	startActivity(intent);
		}
	}
	
	/**
	 * 判断手机网络是否可用
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
				.setTitle("温馨提示")
				.setMessage("亲！您的网络连接未打开哦")
				.setPositiveButton("前往打开",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(
										android.provider.Settings.ACTION_WIRELESS_SETTINGS);
								startActivity(intent);
							}
						}).setNegativeButton("取消", null).create().show();
	}
	
	
}
