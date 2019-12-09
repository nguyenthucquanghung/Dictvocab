package lomo.hust.dictvocab.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.object.Word;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private Context mContext;
    private List<Word> mWords;

    public WordAdapter(Context mContext, List<Word> mWords) {
        this.mContext = mContext;
        this.mWords = mWords;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKey;
        private TextView tvTraits;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_key);
            tvTraits = itemView.findViewById(R.id.tv_traits);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View wordView = inflater.inflate(R.layout.item_word, parent, false);
        return new ViewHolder(wordView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.ViewHolder holder, int position) {
        Word word = mWords.get(position);
        String type = word.getType().replaceAll("ừ", "ừ, ");
        int len = type.length();
        if (len > 0 && type.charAt(len - 1) == ' ')
            type = type.substring(0, len-2);
        String keyAndType = word.getKey();
        if (!word.getType().equals(""))
            keyAndType += " (" + type + ")";
        StringBuilder traits = new StringBuilder();
        for (String trait: word.getTrait())
            traits.append(trait).append("\n");
        holder.tvKey.setText(keyAndType);
        holder.tvTraits.setText(word.getTrait().size() > 0? traits : "");
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public void updateData(List<Word> newData) {
        mWords.clear();
        mWords.addAll(newData);
        this.notifyDataSetChanged();
    }
}
