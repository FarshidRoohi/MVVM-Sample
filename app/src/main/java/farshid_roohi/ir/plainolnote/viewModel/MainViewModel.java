package farshid_roohi.ir.plainolnote.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import farshid_roohi.ir.plainolnote.database.AppRepository;
import farshid_roohi.ir.plainolnote.database.NoteEntity;

public class MainViewModel extends AndroidViewModel {

    public  LiveData<List<NoteEntity>> notes;
    private AppRepository              mRepositorys;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.mRepositorys = AppRepository.getInstance(application.getApplicationContext());
        this.notes = this.mRepositorys.notes;

    }

    public void addSampleData() {
        this.mRepositorys.addSampleData();
    }

    public void deleteAllNotes() {
        this.mRepositorys.deleteAllNotes();
    }
}
