package zhu.com.sortingdata.mvp.business;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface IUserBiz {
    public void login(String username, String password, OnLoginListener loginListener);
}
