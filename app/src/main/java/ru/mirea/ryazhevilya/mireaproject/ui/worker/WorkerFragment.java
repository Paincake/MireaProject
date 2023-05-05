package ru.mirea.ryazhevilya.mireaproject.ui.worker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.ryazhevilya.mireaproject.R;
import ru.mirea.ryazhevilya.mireaproject.Timer;
import ru.mirea.ryazhevilya.mireaproject.TimerWorker;
import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentDataBinding;
import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentWorkerBinding;

public class WorkerFragment extends Fragment {
    private WorkRequest uploadWorkRequest;
    private FragmentWorkerBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWorkerBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        Log.d(WorkerFragment.class.getSimpleName(), "is Started: " + Timer.isStarted);

        binding.buttonGetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(WorkerFragment.class.getSimpleName(), "is Started in onClick: " + Timer.isStarted);

                if(!Timer.isStarted) {
                    binding.tvTime.setText("Timer has not been started yet!");
                }
                else {
                    Log.d(WorkerFragment.class.getSimpleName(), "Timer.time in onClick: " + Timer.time);
//                    binding.tvTime.setText(Timer.time);
                }
            }
        });
        binding.buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadWorkRequest =
                        new OneTimeWorkRequest.Builder(TimerWorker.class)
                                .build();
                WorkManager
                        .getInstance(getContext())
                        .enqueue(uploadWorkRequest);

            }
        });
        return root;
    }
}
