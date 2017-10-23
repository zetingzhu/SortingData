package zhu.com.sortingdata.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public void skipActivity(Class clszz){
        Intent intent = new Intent();
        intent.setClass(getActivity() , clszz);
        startActivity(intent);
    }
}
