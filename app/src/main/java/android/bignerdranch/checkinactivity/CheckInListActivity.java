package android.bignerdranch.checkinactivity;

import androidx.fragment.app.Fragment;

public class CheckInListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CheckInListFragment();
    }
}
