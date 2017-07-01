package me.bitfrom.githubsearch.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.bitfrom.githubsearch.R;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;
import me.bitfrom.githubsearch.databinding.RepositoryItemBinding;

/**
 * <p>Adapter for the {@link java.util.List} of the {@link RepositoryEntity}.</p>
 *
 * @author const
 * @version 1
 * @since 01.07.2017
 */
class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder> {

    private List<RepositoryEntity> items;
    private OnItemClickListener itemClickListener;

    @Inject
    RepositoriesAdapter() {
        this.items = new ArrayList<>();
    }

    void setItemsList(@NonNull final List<RepositoryEntity> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    void setOnItemClickListener(@NonNull OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    RepositoryEntity getItemByPosition(int position) {
        return items.get(position);
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_item, parent, false);
        return new RepositoryViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        RepositoryEntity item = items.get(position);
        holder.name.setText(item.getName());
        holder.language.setText(item.getLanguage());
        holder.description.setText(item.getDescription());
        holder.stars.setText(String.valueOf(item.getStars()));
        holder.forks.setText(String.valueOf(item.getStars()));
        holder.ownerName.setText(item.getOwner().getOwnerName());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView language;
        TextView description;
        TextView stars;
        TextView forks;
        TextView ownerName;

        RepositoryViewHolder(@NonNull View itemView,
                             @NonNull OnItemClickListener onItemClickListener) {
            super(itemView);

            RepositoryItemBinding binding = DataBindingUtil.bind(itemView);

            name = binding.name;
            language = binding.language;
            description = binding.description;
            stars = binding.stars;
            forks = binding.forks;
            ownerName = binding.ownerName;

            name.setOnClickListener(v -> onItemClickListener.onItemClicked(getAdapterPosition()));
        }
    }
}