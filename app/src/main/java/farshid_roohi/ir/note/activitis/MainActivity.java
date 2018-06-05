package farshid_roohi.ir.note.activitis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import farshid_roohi.ir.note.database.NoteEntity;
import farshid_roohi.ir.note.ui.NoteAdapter;
import farshid_roohi.ir.plainolnote.R;
import farshid_roohi.ir.note.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter   noteAdapter;
    private MainViewModel mViewModel;


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @OnClick(R.id.fab)
    void fabClickHandler() {
        startActivity(new Intent(this, EditorActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        initRecyclerView();
        initViewModel();

    }

    private void initViewModel() {
        Observer<List<NoteEntity>> noteObserver =
                new Observer<List<NoteEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                        noteAdapter.putNotes(noteEntities);
                    }
                };

        this.mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        this.mViewModel.notes.observe(this, noteObserver);
    }

    private void initRecyclerView() {
        this.mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(manager);
        this.noteAdapter = new NoteAdapter(new ArrayList<NoteEntity>());
        this.mRecyclerView.setAdapter(this.noteAdapter);

        DividerItemDecoration divider = new
                DividerItemDecoration(mRecyclerView.getContext(), manager.getOrientation());
        mRecyclerView.addItemDecoration(divider);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        }
        if (id == R.id.action_delete_all_notes) {
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        this.mViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        this.mViewModel.addSampleData();
    }
}
