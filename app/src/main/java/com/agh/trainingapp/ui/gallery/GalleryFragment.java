package com.agh.trainingapp.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agh.trainingapp.R;
import com.agh.trainingapp.database.async.DeleteTrainingTask;
import com.agh.trainingapp.database.async.GetTrainingsTask;
import com.agh.trainingapp.database.entities.Training;
import com.agh.trainingapp.utils.DispTrainingAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GalleryFragment extends Fragment implements DispTrainingAdapter.OnTrainingListener {

    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Training> trainingArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);

        try {
            trainingArrayList = new GetTrainingsTask().execute().get();
        } catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }
        recyclerView = view.findViewById(R.id.trainingDisp);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new DispTrainingAdapter(trainingArrayList, this);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onTrainingClick(int position) {

        Intent i = new Intent(getActivity(), TrainingInfo.class);
        i.putExtra("num", "value");
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }

    @Override
    public void onDeleteIconClick(int position) {
        new DeleteTrainingTask(trainingArrayList.get(position)).execute();
        trainingArrayList.remove(position);
        this.mAdapter.notifyItemRemoved(position);
    }
}