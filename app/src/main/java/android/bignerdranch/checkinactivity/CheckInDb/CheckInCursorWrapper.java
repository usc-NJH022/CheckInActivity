package android.bignerdranch.checkinactivity.CheckInDb;

import android.bignerdranch.checkinactivity.CheckInDaily;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class CheckInCursorWrapper extends CursorWrapper {
    public CheckInCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public CheckInDaily getActivities(){
        String uuidString = getString(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.UUID));
        String title = getString(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.TITLE));
        String place = getString(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.PLACE));
        String details = getString(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.DETAILS));
        double longitude = getDouble(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.LATITUDE));
        double latitude = getDouble(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.LONGITUDE));
        long date = getLong(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.DATE));

        CheckInDaily daily = new CheckInDaily(UUID.fromString(uuidString));
        daily.setTitle(title);
        daily.setPlace(place);
        daily.setDetails(details);
        daily.setLongitude(longitude);
        daily.setLatitude(latitude);
        daily.setDate(new Date(date));

        return daily;
    }
}
