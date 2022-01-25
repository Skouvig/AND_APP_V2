package com.example.andappv2.ui.slideshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andappv2.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Upgrades> upgradesList;

    public recyclerAdapter(ArrayList<Upgrades>upgradesList){
        this.upgradesList=upgradesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView upgradeTxt;

        public MyViewHolder(final View view){
            super(view);
            upgradeTxt = view.findViewById(R.id.upgradeTxtView);

        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    //This is the method where the textView can be changed.
    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String upgrade = upgradesList.get(position).getUpgrades();
        holder.upgradeTxt.setText(upgrade);
    }

    @Override
    public int getItemCount() {
        return upgradesList.size();
    }
}
