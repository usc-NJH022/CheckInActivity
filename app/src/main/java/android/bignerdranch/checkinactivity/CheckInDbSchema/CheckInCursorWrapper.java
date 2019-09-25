package android.bignerdranch.checkinactivity.CheckInDbSchema;

import android.bignerdranch.checkinactivity.CheckInDaily;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class CheckInCursorWrapper extends CursorWrapper {
    public CheckInCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public CheckInDaily getCrime(){
        String uuidString = getString(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.UUID));
        String title = getString(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CheckInDbSchema.CheckInTable.Cols.DATE));

        CheckInDaily daily = new CheckInDaily(UUID.fromString(uuidString));
        daily.setTitle(title);
        daily.setDate(new Date(date));

        return daily;
    }
}
