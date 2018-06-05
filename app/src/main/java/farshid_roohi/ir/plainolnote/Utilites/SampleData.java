package farshid_roohi.ir.plainolnote.Utilites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import farshid_roohi.ir.plainolnote.database.NoteEntity;

public class SampleData {


    public static final String SAMPLE_TEXT_1 = "A Sample Note";
    public static final String SAMPLE_TEXT_2 = "A Note With a \n Feed";
    public static final String SAMPLE_TEXT_3 = "It is a long established \n" +
            "fact that a reader will be distracted by the readable content of a page when looking at its layout.\n\n" +
            " The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters," +
            " as opposed to using 'Content here, content here', making it look like readable English. " +
            "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text," +
            " and a search for 'lorem ipsum' will uncover many web sites still in their infancy.\n" +
            " Various versions have evolved over the years, sometimes by accident, " +
            "sometimes on purpose (injected humour and the like).";


    public static Date getDate(int diff) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, diff);
        return calendar.getTime();
    }

    public static List<NoteEntity> getNotes() {
        List<NoteEntity> list = new ArrayList<>();
        list.add(new NoteEntity(getDate(0), SAMPLE_TEXT_1));
        list.add(new NoteEntity(getDate(-1), SAMPLE_TEXT_2));
        list.add(new NoteEntity(getDate(-2), SAMPLE_TEXT_3));
        return list;
    }
}
