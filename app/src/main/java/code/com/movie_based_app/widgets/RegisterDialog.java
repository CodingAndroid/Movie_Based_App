package code.com.movie_based_app.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import code.com.movie_based_app.R;
import code.com.movie_based_app.bean.UserInfo;

/**
 * Created by lihui1 on 2018/1/8.
 */

public class RegisterDialog extends Dialog{

    private Button register_btn;
    private EditText register_phone;
    private EditText register_account;
    private EditText register_pwd;
    private EditText register_re_pwd;

    public RegisterDialog(@NonNull Context context) {
        super(context);
    }

    public RegisterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RegisterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
        register_btn = (Button) findViewById(R.id.bt_register);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = new UserInfo();
                userInfo.user_phone = register_phone.getText().toString();
                userInfo.user_account = register_account.getText().toString();
                userInfo.user_pwd = register_pwd.getText().toString();
                userInfo.re_user_pwd = register_re_pwd.getText().toString();
                userInfo.save();
                Log.d("-", userInfo.user_phone+userInfo.user_account+userInfo.user_pwd);
                LoginDialog loginDialog = new LoginDialog(getContext(), R.style.Dialog_Login);
                loginDialog.show();
                WindowManager windowManager = getWindow().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = loginDialog.getWindow().getAttributes();
                lp.width = (int)(display.getWidth()); //设置宽度
                loginDialog.getWindow().setAttributes(lp);
            }
        });
    }

    private void initView(){
        register_phone = (EditText) findViewById(R.id.et_phone);
        register_account = (EditText) findViewById(R.id.et_account);
        register_pwd = (EditText) findViewById(R.id.et_password);
        register_re_pwd = (EditText) findViewById(R.id.et_re_password);
    }
}
