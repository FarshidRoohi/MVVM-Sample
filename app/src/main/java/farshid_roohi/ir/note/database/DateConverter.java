package farshid_roohi.ir.note.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long time) {
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static Long toTimesTemp(Date date) {
        return date == null ? null : date.getTime();
    }
}
