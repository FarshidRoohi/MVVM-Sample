package farshid_roohi.ir.plainolnote;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import farshid_roohi.ir.plainolnote.Utilites.SampleData;
import farshid_roohi.ir.plainolnote.database.AppDatabase;
import farshid_roohi.ir.plainolnote.database.NoteDao;
import farshid_roohi.ir.plainolnote.database.NoteEntity;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    public static final String TAG = "junit";

    private AppDatabase appDatabase;
    private NoteDao     noteDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        noteDao = appDatabase.noteDao();

        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        appDatabase.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrieveNote() {
        noteDao.insertAll(SampleData.getNotes());
        int count = noteDao.getCount();
        Log.i(TAG, "createAndRetrieveNote count  : " + count);
        assertEquals(SampleData.getNotes().size(), count);

    }

    @Test
    public void compareNote() {
        noteDao.insertAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity dbNote   = noteDao.getNotesById(1);
        assertEquals(original.getText(), dbNote.getText());
    }

}
