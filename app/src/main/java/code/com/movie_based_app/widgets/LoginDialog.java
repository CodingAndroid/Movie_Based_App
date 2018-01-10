package code.com.movie_based_app.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.List;

import code.com.movie_based_app.MovieApp;
import code.com.movie_based_app.R;
import code.com.movie_based_app.bean.UserInfo;

/**自定义dialog
 * Created by lihui1 on 2017/12/26.
 */

public class LoginDialog extends Dialog{
    private EditText et_PhoneNum;
    private EditText et_Password;
    private Button bt_Login;
    private Button forget_pass;
    private Button register;

    public LoginDialog(@NonNull Context context) {
        super(context);
    }

    public LoginDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoginDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Select select = new Select();
                List<UserInfo> userInfoList = select.from(UserInfo.class).execute();
                for(UserInfo userInfo : userInfoList){
                    if (et_PhoneNum.getText().toString().equals(userInfo.user_phone) && et_Password.getText().toString().equals(userInfo.user_pwd)){
                        dismiss();
                    } else if (et_PhoneNum.getText().length()==0||et_Password.getText().length()==0){
                        Toast.makeText(getContext(), "账号或密码不能为空!", Toast.LENGTH_SHORT).show();
                    } else if (et_PhoneNum.getText().length() != 11){
                        Toast.makeText(getContext(), "格式有误!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDialog registerDialog = new RegisterDialog(getContext(), R.style.Dialog_Login);
                registerDialog.show();
            }
        });
    }

    public void initView(){
        et_PhoneNum = (EditText) findViewById(R.id.et_PhoneNum);
        et_Password = (EditText) findViewById(R.id.et_Password);
        bt_Login = (Button) findViewById(R.id.bt_Login);
        forget_pass = (Button) findViewById(R.id.forget_pass);
        register = (Button) findViewById(R.id.register);
    }

}
