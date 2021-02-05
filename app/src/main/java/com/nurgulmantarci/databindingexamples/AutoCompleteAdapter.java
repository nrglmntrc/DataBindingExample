package com.nurgulmantarci.databindingexamples;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.nurgulmantarci.databindingexamples.model.AutoCompleteHeader;
import com.nurgulmantarci.databindingexamples.model.Region;

import java.util.List;

public class AutoCompleteAdapter extends RecyclerView.Adapter<AutoCompleteAdapter.AutoCompleteViewHolder> {

    List<Object> regions;

    public AutoCompleteAdapter(List<Object> regions) {
        this.regions = regions;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = regions.get(position);
        if (item instanceof Region) {
            return R.layout.item_autocomplete;
        } else if (item instanceof AutoCompleteHeader) {
            return R.layout.item_autocomplete_header;
        }
        throw new RuntimeException("invalid obj");
    }

    @Override
    public AutoCompleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AutoCompleteViewHolder.create(LayoutInflater.from(parent.getContext()), parent, viewType);
    }


    @Override
    public void onBindViewHolder(AutoCompleteViewHolder holder, int position) {
        holder.bindto(regions.get(position));
    }


    @Override
    public int getItemCount() {
        return regions.size();
    }

    static class AutoCompleteViewHolder extends RecyclerView.ViewHolder {

        static AutoCompleteViewHolder create(LayoutInflater inflater, ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(inflater,viewType, parent, false);
            return new AutoCompleteViewHolder(binding);
        }

        private ViewDataBinding  binding;

        public AutoCompleteViewHolder(ViewDataBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindto(Object data) {
            binding.setVariable(BR.data,data);
            binding.executePendingBindings();
        }
    }
}
