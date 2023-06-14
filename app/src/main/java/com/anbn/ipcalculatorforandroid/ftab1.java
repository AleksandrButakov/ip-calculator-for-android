package com.anbn.ipcalculatorforandroid;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ftab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ftab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ftab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ftab1.
     */
    // TODO: Rename and change types and number of parameters
    public static ftab1 newInstance(String param1, String param2) {
        ftab1 fragment = new ftab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d(MotionEffect.TAG, "onDestroy");
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.d(MotionEffect.TAG, "onStop");
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.d(MotionEffect.TAG, "onStart");
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.d(MotionEffect.TAG, "onPause");
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.d(MotionEffect.TAG, "onResume");
//
//    }

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
        return inflater.inflate(R.layout.fragment_ftab1, container, false);
    }

    /*
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }
     */

}