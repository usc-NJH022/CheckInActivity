package android.bignerdranch.checkinactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckInListFragment extends Fragment {

    private RecyclerView mCheckInRecyclerView; // change to mActivityRecyclerView
    private CheckInAdapter mAdapter;
    private boolean mSubtitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_check_in_list, container, false);

        mCheckInRecyclerView = (RecyclerView) view
                .findViewById(R.id.check_in_recycler_view);
        mCheckInRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_check_in_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
                if(mSubtitleVisible){
                    subtitleItem.setTitle(R.string.hide_subtitle);
                }else{
                    subtitleItem.setTitle(R.string.show_subtitle);
                }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_crime:
                CheckInDaily daily = new CheckInDaily();
                android.bignerdranch.checkinactivity.CheckInLab.get(getActivity()).addCheckInActivity(daily);
                Intent intent = CheckInPagerActivity
                        .newIntent(getActivity(), daily.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateSubtitle(){
        CheckInLab checkInLab = CheckInLab.get(getActivity());
        int activityCount = checkInLab.getCheckInActivities().size();
        String subtitle = getString(R.string.subtitle_format, activityCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI(){
        android.bignerdranch.checkinactivity.CheckInLab dailyLab = android.bignerdranch.checkinactivity.CheckInLab.get(getActivity());
        List<CheckInDaily> dailies = dailyLab.getCheckInActivities();

        if(mAdapter == null) {
            mAdapter = new CheckInAdapter(dailies);
            mCheckInRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.setDailies(dailies);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class CheckInHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckInDaily mDaily;

        public CheckInHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_check_in, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        @Override
        public void onClick(View view){
            Intent intent = CheckInPagerActivity.newIntent(getActivity(), mDaily.getId());
            startActivity(intent);
        }

        public void bind(CheckInDaily daily){
            mDaily = daily;
            mTitleTextView.setText(mDaily.getTitle());
            mDateTextView.setText(mDaily.getDate().toString());
        }
    }

    private class CheckInAdapter extends RecyclerView.Adapter<CheckInHolder>{

        private List<CheckInDaily> mDailies;

        public CheckInAdapter(List<CheckInDaily> dailies){
            mDailies = dailies;
        }

        @Override
        public CheckInHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CheckInHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CheckInHolder holder, int position) {
            CheckInDaily daily = mDailies.get(position);
            holder.bind(daily);
        }

        @Override
        public int getItemCount() {
            return mDailies.size();
        }

        public void setDailies(List<CheckInDaily> dailies){
            mDailies = dailies;
        }
    }
}
