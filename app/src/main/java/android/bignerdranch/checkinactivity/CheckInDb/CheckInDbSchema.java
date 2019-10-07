package android.bignerdranch.checkinactivity.CheckInDb;

public class CheckInDbSchema {
    public static final class CheckInTable{
        public  static final String NAME = "crimes";

        public class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String PLACE = "place";
        }
    }
}
