package farshid_roohi.ir.note.activitis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farshid_roohi.ir.note.Utilites.Constants;
import farshid_roohi.ir.note.database.NoteEntity;
import farshid_roohi.ir.plainolnote.R;
import farshid_roohi.ir.note.viewModel.EditorViewModel;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    @BindView(R.id.text_note)
    TextView textNote;
    private boolean mNewTitle, mEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            this.mEditing = savedInstanceState.getBoolean(Constants.EDITING_KEY);
        }

        initViewModel();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (!this.mNewTitle) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            deleteAndReturn();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        this.mViewModel.saveNote(textNote.getText().toString());
        finish();
    }

    private void deleteAndReturn() {
        this.mViewModel.deleteNote();
        finish();
    }

    private void initViewModel() {
        this.mViewModel = ViewModelProviders.of(this).get(EditorViewModel.class);

        this.mViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {
                if (noteEntity != null && !mEditing) {
                    textNote.setText(noteEntity.getText());
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Note");
            this.mNewTitle = true;
        } else {
            setTitle("Edit Note");
            int idNote = extras.getInt(Constants.NOTE_ID_KEY);
            this.mViewModel.loadNote(idNote);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(Constants.EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}
