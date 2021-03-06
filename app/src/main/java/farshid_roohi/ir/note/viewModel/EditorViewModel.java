package farshid_roohi.ir.note.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import farshid_roohi.ir.note.database.AppRepository;
import farshid_roohi.ir.note.database.NoteEntity;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    private AppRepository mRepository;

    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadNote(final int idNote) {
        this.executor.execute(() -> {
            NoteEntity noteEntity = mRepository.getNoteById(idNote);
            mLiveNote.postValue(noteEntity);
        });

    }

    public void saveNote(String noteText) {
        NoteEntity note = this.mLiveNote.getValue();
        if (note == null) {
            if (TextUtils.isEmpty(noteText.trim())) {
                return;
            }
            note = new NoteEntity(new Date(), noteText.trim());
        } else {
            note.setText(noteText.trim());
        }
        this.mRepository.insertNote(note);
    }

    public void deleteNote() {
        this.mRepository.deleteNote(this.mLiveNote.getValue());
    }
}
