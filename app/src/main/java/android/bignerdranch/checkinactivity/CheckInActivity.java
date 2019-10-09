package android.bignerdranch.checkinactivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.UUID;

public class CheckInActivity extends SingleFragmentActivity {

    public static final String EXTRA_CHECK_IN_ID = "com.bignerdranch.android.Checkinactivity.check_in_id";
    public static final int REQUEST_ERROR = 0;

    public static Intent newIntent(Context packageContext, UUID activityId){
        Intent intent = new Intent(packageContext, CheckInActivity.class);
        intent.putExtra(EXTRA_CHECK_IN_ID, activityId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CHECK_IN_ID);
        return CheckInFragment.newInstance(crimeId);
    }

    @Override
    protected void onResume(){
        super.onResume();

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if(errorCode != ConnectionResult.SUCCESS){
            Dialog errorDialog = apiAvailability.getErrorDialog(this, errorCode, REQUEST_ERROR,
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            finish();
                        }
                    });
            errorDialog.show();
        }
    }
}
