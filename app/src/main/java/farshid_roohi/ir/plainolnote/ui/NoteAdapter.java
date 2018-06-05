package farshid_roohi.ir.plainolnote.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import farshid_roohi.ir.plainolnote.activitis.EditorActivity;
import farshid_roohi.ir.plainolnote.R;
import farshid_roohi.ir.plainolnote.database.NoteEntity;

import static farshid_roohi.ir.plainolnote.Utilites.Constants.NOTE_ID_KEY;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    private List<NoteEntity> list;

    public NoteAdapter(List<NoteEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        final NoteEntity item = list.get(position);
        holder.noteText.setText(item.getText());
        holder.itemView.setTag(item);
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.fab.getContext(), EditorActivity.class);
                intent.putExtra(NOTE_ID_KEY, item.getId());
                holder.fab.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void putNotes(List<NoteEntity> notes) {
        this.list = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.note_text)
        TextView             noteText;
        @BindView(R.id.fab)
        FloatingActionButton fab;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
