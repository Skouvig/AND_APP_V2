package com.example.andappv2.ui.slideshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andappv2.R;
import com.example.andappv2.databinding.FragmentStoreBinding;
import com.example.andappv2.ui.profile.Profile;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    private StoreViewModel storeViewModel;
    private FragmentStoreBinding binding;
    private ArrayList<Upgrades> upgradesList;
    private RecyclerView recyclerView;
    Button upgradeBtn;
    
    SharedPreferences sp;
    private int noOfClicks;
    private int cost =10;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storeViewModel =
                new ViewModelProvider(this).get(StoreViewModel.class);

        binding = FragmentStoreBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        //
        upgradesList = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.recyclerView1);
        upgradeBtn = rootView.findViewById(R.id.upgradeBtn);

        //Gets the current number of clicks from shared preferences
        sp = getActivity().getSharedPreferences("TotalClicks", Context.MODE_PRIVATE);
        //Add noOfClicks to the current number of clicks from shared preferences
        noOfClicks = noOfClicks+(sp.getInt("clicks",0));

        setUpgradeInfo();
        setAdapter();

        return rootView;
        
    }


    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(upgradesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUpgradeInfo() {
        upgradesList.add(new Upgrades("1. upgrade = +1 Click Value", cost, " Clicks"));
        upgradesList.add(new Upgrades("2. upgrade = +2 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("3. upgrade = +4 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("4. upgrade = +8 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("5. upgrade = +16 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("6. upgrade = +32 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("7. upgrade = +64 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("8. upgrade = +128 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("9. upgrade = +256 Click Value", cost, "Clicks"));
        upgradesList.add(new Upgrades("10. upgrade = +512 Click Value", cost, "Clicks"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}