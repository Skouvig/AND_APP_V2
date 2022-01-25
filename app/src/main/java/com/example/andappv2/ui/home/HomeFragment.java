package com.example.andappv2.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.andappv2.ui.profile.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Button btn;
    private int noOfClicks;
    private TextView textCount;
    private TextView message;
    SharedPreferences sp;
    private Profile profile;
    private int click=1;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        btn = rootView.findViewById(R.id.btn);
        textCount = rootView.findViewById(R.id.textCount);
        message = rootView.findViewById(R.id.message);

        //Gets the current number of clicks from shared preferences
        sp = getActivity().getSharedPreferences("TotalClicks", Context.MODE_PRIVATE);
        //Add noOfClicks to the current number of clicks from shared preferences
        noOfClicks = noOfClicks+(sp.getInt("clicks",0));

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onViewCreated (@NonNull View view, @NonNull Bundle saveInstanceState){
        // OnClickListener for the button
        btn.setOnClickListener(v->{
            noOfClicks = noOfClicks+click;
            textCount.setText(String.valueOf(noOfClicks));

        //noOfClicks is added into shared preferences
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("clicks", noOfClicks);
            editor.commit();

            //Different if statements for the message textbox
            if (noOfClicks < 1){
                message.setText("Start clicking");
            } else if (noOfClicks< 10){
                message.setText("Thats right!");
            } else if ( noOfClicks <30){
                message.setText("You got it, keep on clicking!");
            } else if( noOfClicks <50){
                message.setText("Yes, just like that!");
            } else if (noOfClicks <80){
                message.setText("You are soon able to get your first upgrade..");
            } else if ( noOfClicks <100){
                message.setText("Go for 100 clicks to unluck your first reward");
            } else if ( noOfClicks > 100){
                message.setText("Go into the store to unlock your well deserved reward!");
            }
        });
    }
}