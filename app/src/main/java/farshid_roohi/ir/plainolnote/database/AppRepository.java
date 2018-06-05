package farshid_roohi.ir.plainolnote.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import farshid_roohi.ir.plainolnote.Utilites.SampleData;

public class AppRepository {

    private static AppRepository              ourInstance;
    public         LiveData<List<NoteEntity>> notes;
    private        AppDatabase                database;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        this.database = AppDatabase.getInstance(context);
        this.notes = getAllNotes();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.noteDao().insertAll(SampleData.getNotes());
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes() {
        return this.database.noteDao().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.noteDao().deleteAll();
            }
        });
    }

    public NoteEntity getNoteById(int idNote) {
        return database.noteDao().getNotesById(idNote);
    }

    public void insertNote(final NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.noteDao().insertNote(note);
            }
        });
    }

    public void deleteNote(final NoteEntity noteEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.noteDao().deleteNote(noteEntity);
            }
        });
    }
}