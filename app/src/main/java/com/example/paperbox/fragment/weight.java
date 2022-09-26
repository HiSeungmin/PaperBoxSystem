package com.example.paperbox.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paperbox.R;

import java.text.SimpleDateFormat;
import java.util.Locale;
//import android.os.Bundle;
//import com.dinuscxj.progressbar.CircleProgressBar;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link weight#newInstance} factory method to
 * create an instance of this fragment.
 */
public class weight extends Fragment { //implements CircleProgressBar.ProgressFormatter

    TextView date;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public weight() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment weight.
     */
    // TODO: Rename and change types and number of parameters
    public static weight newInstance(String param1, String param2) {
        weight fragment = new weight();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        View v = inflater.inflate(R.layout.fragment_weight, container, false);

        date = v.findViewById(R.id.Date_textView);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN);
        String time = sdf.format (System.currentTimeMillis());

        date.setText(time);

        return v;
    }
}