package com.psychology.psychologyapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssessmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssessmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssessmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View fragmentView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RadioGroup radioGroupQuestionOne;
    private CardView cardViewQuestion1;
    private CardView cardViewQuestion2;
    private CardView cardViewQuestion3;
    private Spinner locationSpinnerQuestion1;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssessmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssessmentFragment newInstance(String param1, String param2) {
        AssessmentFragment fragment = new AssessmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AssessmentFragment() {
        // Required empty public constructor
    }

    private void initiateAssessment() {

        cardViewQuestion1 = (CardView) fragmentView.findViewById(R.id.cardViewQuestion1);
        cardViewQuestion1.setVisibility(View.VISIBLE);

        cardViewQuestion2 = (CardView) fragmentView.findViewById(R.id.cardViewQuestion2);
        cardViewQuestion2.setVisibility(View.GONE);

        cardViewQuestion3 = (CardView) fragmentView.findViewById(R.id.cardViewQuestion3);
        cardViewQuestion3.setVisibility(View.GONE);

        radioGroupQuestionOne = (RadioGroup) fragmentView.findViewById(R.id.radioGroupQuestionOne);
        radioGroupQuestionOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    Toast.makeText(getActivity(), "You selected "+ checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                    if (checkedRadioButton.getText().equals("Yes")) {
                        locationSpinnerQuestion1.setVisibility(View.GONE);
                        cardViewQuestion2.setVisibility(View.VISIBLE);

                    }else{
                        locationSpinnerQuestion1.setVisibility(View.VISIBLE);
                        cardViewQuestion2.setVisibility(View.GONE);
                    }
                }
            }
        });

        locationSpinnerQuestion1 = (Spinner) fragmentView.findViewById(R.id.location_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.locations, android.R.layout.simple_spinner_item);
        locationSpinnerQuestion1.setAdapter(adapter);
        locationSpinnerQuestion1.setVisibility(View.GONE);
        locationSpinnerQuestion1.setOnItemSelectedListener(this);











    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_assessment, container, false);
        initiateAssessment();
        return fragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        if (!myText.getText().equals("Nothing Selected")) {
            cardViewQuestion2.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "You selected "+myText.getText(), Toast.LENGTH_SHORT).show();

        } else {
            cardViewQuestion2.setVisibility(View.GONE);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}