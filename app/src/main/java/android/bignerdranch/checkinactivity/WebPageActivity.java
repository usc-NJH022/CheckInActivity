package android.bignerdranch.checkinactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class WebPageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext, Uri webPageUri){
        Intent intent = new Intent(packageContext, WebPageActivity.class);
        intent.setData(webPageUri);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return WebPageFragment.newInstance(getIntent().getData());
    }
}
