package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.notes.adapters;

import android.annotation.SuppressLint;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gettingtoknowandroidgeekbrainsjava.R;
import com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain.NotesCity;
import java.util.ArrayList;
import java.util.List;

public class NotesCityAdapter extends RecyclerView.Adapter<NotesCityAdapter.NotesCityViewHolder> {

    private final List<NotesCity> items = new ArrayList<>();

    private OnNoteClicked noteClicked;

    public void addItems(List<NotesCity> toAdd) {
        items.addAll(toAdd);
    }

    public void clear() {
        items.clear();
    }

    @NonNull
    @Override
    public NotesCityAdapter.NotesCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes_city, parent, false);

        return new NotesCityViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotesCityAdapter.NotesCityViewHolder holder, int position) {

        NotesCity item = items.get(position);

        holder.getIdNote().setText(Integer.toString(item.getId()));
        holder.getNameNote().setText(item.getName());
        holder.getDateNote().setText(item.getDataCreate());
        holder.getAvatarNote().setImageResource(item.getAvatar());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    public interface OnNoteClicked {
        void onNoteClicked(NotesCity notesCity);
    }

    public class NotesCityViewHolder extends RecyclerView.ViewHolder {

        private TextView idNote;
        private TextView nameNote;
        private TextView descriptionNote;
        private TextView dateNote;
        private ImageView imageNote;
        private ImageView avatarNote;

        public NotesCityViewHolder(@NonNull View itemView) {
            super(itemView);
//            registerContextMenu(itemView);
            idNote = itemView.findViewById(R.id.textView_id_notes_city);
            nameNote = itemView.findViewById(R.id.textView_name_notes_city);
            dateNote = itemView.findViewById(R.id.textView_data_notes_city);
            avatarNote = itemView.findViewById(R.id.imageView_avatar_notes_city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (noteClicked != null) {
                        noteClicked.onNoteClicked(items.get(getAdapterPosition()));
                    }
                }
            });
        }

        public TextView getIdNote() {
            return idNote;
        }

        public TextView getNameNote() {
            return nameNote;
        }

        public TextView getDescriptionNote() {
            return descriptionNote;
        }

        public ImageView getImageNote() {
            return imageNote;
        }

        public ImageView getAvatarNote() {
            return avatarNote;
        }

        public TextView getDateNote() {
            return dateNote;
        }
    }
}
