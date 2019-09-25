package android.bignerdranch.checkinactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckInLab {
    private static CheckInLab sCheckInLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CheckInLab get(Context context){
        if(sCheckInLab == null){
            sCheckInLab = new CheckInLab(context);
        }
        return sCheckInLab;
    }

    private CheckInLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new android.bignerdranch.checkinactivity.CheckInDb.CheckInBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addCheckInActivity(CheckInDaily c){
        ContentValues values = getContentValues(c);

        mDatabase.insert(android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.NAME, null, values);
    }

    public List<CheckInDaily> getCheckInActivities() {
        List<android.bignerdranch.checkinactivity.CheckInDaily> dailies = new ArrayList<>();

        android.bignerdranch.checkinactivity.CheckInDb.CheckInCursorWrapper cursor = queryCrimes(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                dailies.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally{
            cursor.close();
        }
        return dailies;
    }

    public android.bignerdranch.checkinactivity.CheckInDaily getCheckInActivity(UUID id){
        android.bignerdranch.checkinactivity.CheckInDb.CheckInCursorWrapper cursor = queryCrimes(
                android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        }finally{
            cursor.close();
        }
    }

    public File getPhotoFile(android.bignerdranch.checkinactivity.CheckInDaily daily){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, daily.getPhotoFilename());
    }

    public void updateCheckIn(android.bignerdranch.checkinactivity.CheckInDaily daily){
        String uuidString = daily.getId().toString();
        ContentValues values = getContentValues(daily);

        mDatabase.update(android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.NAME, values,
                android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private android.bignerdranch.checkinactivity.CheckInDb.CheckInCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.NAME,
                null, // columns - null select all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );
        return new android.bignerdranch.checkinactivity.CheckInDb.CheckInCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(CheckInDaily daily){
        ContentValues values = new ContentValues();
        values.put(android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.Cols.UUID, daily.getId().toString());
        values.put(android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.Cols.TITLE, daily.getTitle());
        values.put(android.bignerdranch.checkinactivity.CheckInDb.CheckInDbSchema.CheckInTable.Cols.DATE, daily.getDate().getTime());

        return values;
    }
}
