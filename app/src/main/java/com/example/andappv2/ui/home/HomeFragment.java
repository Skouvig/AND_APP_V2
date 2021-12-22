package com.example.andappv2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.andappv2.R;
import com.example.andappv2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Button btn;
    private int count = 0;
    private TextView textCount;
    private TextView message;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        btn = rootView.findViewById(R.id.btn);
        textCount = rootView.findViewById(R.id.textCount);
        message = rootView.findViewById(R.id.message);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onViewCreated (@NonNull View view, @NonNull Bundle saveInstanceState){
        // OnClickListener for the button
        btn.setOnClickListener(v->{
            count++;
            textCount.setText(String.valueOf(count));

            //Different if statements for the message textbox
            if (count == 0 || count < 10){
                message.setText("Start clicking");
            } else if (count == 10 || count <30){
                message.setText("You got it, keep on clicking!");
            } else if(count == 30 || count <50){
                message.setText("Yes, just like that!");
            } else if (count == 50 || count <80){
                message.setText("You are soon able to get your first upgrade..");
            } else if (count == 80 || count <100){
                message.setText("Go for 100 clicks to unluck your first reward");
            } else if (count == 100 || count > 100){
                message.setText("Go into the store to unlock your well deserved reward!");
            }
        });
    }


}