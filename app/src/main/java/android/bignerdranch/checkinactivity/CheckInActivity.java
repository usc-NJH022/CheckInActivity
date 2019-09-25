package android.bignerdranch.checkinactivity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CheckInActivity extends SingleFragmentActivity {

    public static final String EXTRA_CHECK_IN_ID = "com.bignerdranch.android.Checkinactivity.check_in_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CheckInActivity.class);
        intent.putExtra(EXTRA_CHECK_IN_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CheckInFragment.newInstance(crimeId);
    }
}
