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
                // During initial setup, plug in the details fragment.

                // create fragment
                DetailsFragment details = new DetailsFragment();

                // get and set the position input by user (i.e., "index")
                // which is the construction arguments for this fragment
                details.setArguments(getIntent().getExtras());

                //
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


            // Populate list with our static array of titles in list in the
            // Shakespeare class

            setListAdapter(new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_activated_1,
                    TrainingInfo.exerciseList.stream().map(Exercise::getName).collect(Collectors.toList())));

            // Check to see if we have a frame in which to embed the details
            // fragment directly in the containing UI.
            // R.id.details relates to the res/layout-land/fragment_layout.xml
            // This is first created when the phone is switched to landscape
            // mode

            View detailsFrame = getActivity().findViewById(R.id.details);


            // Check that a view exists and is visible
            // A view is visible (0) on the screen; the default value.
            // It can also be invisible and hidden, as if the view had not been
            // added.
            //
            mDualPane = detailsFrame != null
                    && detailsFrame.getVisibility() == View.VISIBLE;


            if (savedInstanceState != null) {
                // Restore last state for checked position.
                mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            }

            if (mDualPane) {
                // In dual-pane mode, the list view highlights the selected
                // item.
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                // Make sure our UI is in the correct state.
                showDetails(mCurCheckPosition);
            } else {
                // We also highlight in uni-pane just for fun
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                getListView().setItemChecked(mCurCheckPosition, true);
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("curChoice", mCurCheckPosition);
        }

        // If the user clicks on an item in the list (e.g., Henry V then the
        // onListItemClick() method is called. It calls a helper function in
        // this case.

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {


            showDetails(position);
        }

        // Helper function to show the details of a selected item, either by
        // displaying a fragment in-place in the current UI, or starting a whole
        // new activity in which it is displayed.

        void showDetails(int index) {
            mCurCheckPosition = index;

            // The basic design is mutli-pane (landscape on the phone) allows us
            // to display both fragments (titles and details) with in the same
            // activity; that is FragmentLayout -- one activity with two
            // fragments.
            // Else, it's single-pane (portrait on the phone) and we fire
            // another activity to render the details fragment - two activities
            // each with its own fragment .
            //
            if (mDualPane) {
                // We can display everything in-place with fragments, so update
                // the list to highlight the selected item and show the data.
                // We keep highlighted the current selection
                getListView().setItemChecked(index, true);

                // Check what fragment is currently shown, replace if needed.
                DetailsFragment details = (DetailsFragment) getFragmentManager()
                        .findFragmentById(R.id.details);
                if (details == null || details.getShownIndex() != index) {
                    // Make new fragment to show this selection.

                    details = DetailsFragment.newInstance(index);

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    FragmentTransaction ft = getFragmentManager()
                            .beginTransaction();
                    ft.replace(R.id.details, details);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }

            } else {
                // Otherwise we need to launch a new activity to display
                // the dialog fragment with selected text.
                // That is: if this is a single-pane (e.g., portrait mode on a
                // phone) then fire DetailsActivity to display the details
                // fragment

                // Create an intent for starting the DetailsActivity
                Intent intent = new Intent();

                // explicitly set the activity context and class
                // associated with the intent (context, class)
                intent.setClass(getActivity(), DetailsActivity.class);

                // pass the current position
                intent.putExtra("index", index);

                startActivity(intent);
            }
        }
    }

    // This is the secondary fragment, displaying the details of a particular
    // item.

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

