package zhu.com.sortingdata.mvp.business;

import zhu.com.sortingdata.mvp.bean.User;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();

}
