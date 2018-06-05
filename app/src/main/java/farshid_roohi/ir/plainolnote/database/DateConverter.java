package farshid_roohi.ir.plainolnote.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestemp) {
        return timestemp == null ? null : new Date(timestemp);
    }

    @TypeConverter
    public static Long toTimestemp(Date date) {
        return date == null ? null : date.getTime();
    }
}
