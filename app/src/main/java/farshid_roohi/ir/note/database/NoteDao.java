package farshid_roohi.ir.note.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> noteEntity);

    @Delete
    void deleteNote(NoteEntity noteEntity);

    @Query("SELECT * FROM NOTES WHERE id = :id")
    NoteEntity getNotesById(int id);

    @Query("SELECT * FROM NOTES ORDER BY date DESC")
    LiveData<List<NoteEntity>> getAll();

    @Query("DELETE FROM notes")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();

}
