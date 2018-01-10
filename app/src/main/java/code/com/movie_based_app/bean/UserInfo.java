package code.com.movie_based_app.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by lihui1 on 2018/1/8.
 */

@Table(name = "UserInfo")
public class UserInfo extends Model{

    @Column(name = "UserPhone", index = true)
    public String user_phone;
    @Column(name = "UserAccount")
    public String user_account;
    @Column(name = "UserPwd", index = true)
    public String user_pwd;
    @Column(name = "ReUserPwd")
    public String re_user_pwd;
}
