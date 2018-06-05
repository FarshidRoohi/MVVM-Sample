package farshid_roohi.ir.note.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import farshid_roohi.ir.note.database.AppRepository;
import farshid_roohi.ir.note.database.NoteEntity;

public class MainViewModel extends AndroidViewModel {

    public  LiveData<List<NoteEntity>> notes;
    private AppRepository              appRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.appRepository = AppRepository.getInstance(application.getApplicationContext());
        this.notes = this.appRepository.notes;

    }

    public void addSampleData() {
        this.appRepository.addSampleData();
    }

    public void deleteAllNotes() {
        this.appRepository.deleteAllNotes();
    }
}
