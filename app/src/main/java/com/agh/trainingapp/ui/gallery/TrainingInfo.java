package com.agh.trainingapp.ui.gallery;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.agh.trainingapp.R;
import com.agh.trainingapp.database.entities.Exercise;

import java.util.List;
import java.util.stream.Collectors;

public class TrainingInfo extends FragmentActivity {

    private static List<Exercise> exerciseList = Exercise.getExercises();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.training_description);

    }

    public static class DetailsActivity extends FragmentActivity {

        private static final String TAG = "DetailsACTIVITY";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                finish();
                return;
            }

            if (savedInstanceState == null) {
                DetailsFragment details = new DetailsFragment();
                details.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, details).commit();
            }
        }


    }

    public static class TitlesFragment extends ListFragment {
        private static final String TAG = "TitlesFRAGMENT";
        boolean mDualPane;
        int mCurCheckPosition = 0;


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            setListAdapter(new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_activated_1,
                    TrainingInfo.exerciseList.stream().map(Exercise::getName).collect(Collectors.toList())));

            View detailsFrame = getActivity().findViewById(R.id.details);


            mDualPane = detailsFrame != null
                    && detailsFrame.getVisibility() == View.VISIBLE;


            if (savedInstanceState != null) {
                mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            }

            if (mDualPane) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                showDetails(mCurCheckPosition);
            } else {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                getListView().setItemChecked(mCurCheckPosition, true);
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("curChoice", mCurCheckPosition);
        }


        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {


            showDetails(position);
        }


        void showDetails(int index) {
            mCurCheckPosition = index;

            if (mDualPane) {
              getListView().setItemChecked(index, true);
              DetailsFragment details = (DetailsFragment) getFragmentManager()
                        .findFragmentById(R.id.details);
                if (details == null || details.getShownIndex() != index) {

                    details = DetailsFragment.newInstance(index);

                    FragmentTransaction ft = getFragmentManager()
                            .beginTransaction();
                    ft.replace(R.id.details, details);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }

            } else {Intent intent = new Intent();
                intent.setClass(getActivity(), DetailsActivity.class);

                intent.putExtra("index", index);

                startActivity(intent);
            }
        }
    }

    public static class DetailsFragment extends Fragment {

        private TextView descripiton;
        private TextView bodyPart;

        public static DetailsFragment newInstance(int index) {
            DetailsFragment f = new DetailsFragment();

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putInt("index", index);
            f.setArguments(args);

            return f;
        }


        public int getShownIndex() {
            return getArguments().getInt("index", 0);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View view = inflater.inflate(R.layout.details_view, container, false);

            descripiton = view.findViewById(R.id.des);
            bodyPart = view.findViewById(R.id.bodyPart);


            descripiton.setText(TrainingInfo.exerciseList.get(getShownIndex()).getDescription());
            bodyPart.setText(TrainingInfo.exerciseList.get(getShownIndex()).getBodyPart());

            return view;
        }
    }

}

