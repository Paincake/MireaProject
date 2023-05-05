package ru.mirea.ryazhevilya.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentPreferencesBinding;
import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentSlideshowBinding;


public class PreferencesFragment extends Fragment {

    private FragmentPreferencesBinding binding;

    public PreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPreferencesBinding.inflate(inflater, container, false);
        SharedPreferences sharedPref =
                getActivity().getSharedPreferences("mirea_project", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String groupName = sharedPref.getString("ADDRESS", "unknown");
        int listNumber = sharedPref.getInt("INDEX", 0);
        if(!groupName.equals("unknown") || listNumber != 0){
            binding.editTextAddress.setText(groupName);
            binding.editTextIndex.setText(String.valueOf(listNumber));
        }
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("ADDRESS", binding.editTextAddress.getText().toString());
                editor.putInt("INDEX", Integer.parseInt(binding.editTextIndex.getText().toString()));
                editor.apply();
            }
        });
        return binding.getRoot();
    }
}