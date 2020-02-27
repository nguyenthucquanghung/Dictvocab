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

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mTags;

    public TagAdapter(Context mContext, List<String> mTags) {
        this.mContext = mContext;
        this.mTags = mTags;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTag;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View wordView = inflater.inflate(R.layout.item_tag, parent, false);
        return new ViewHolder(wordView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.ViewHolder holder, int position) {
        holder.tvTag.setText(mTags.get(position));
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public void updateData(List<String> newData) {
        mTags.clear();
        mTags.addAll(newData);
        this.notifyDataSetChanged();
    }
}

