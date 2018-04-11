package zhu.com.sortingdata.mvp.view;

import zhu.com.sortingdata.mvp.bean.User;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
