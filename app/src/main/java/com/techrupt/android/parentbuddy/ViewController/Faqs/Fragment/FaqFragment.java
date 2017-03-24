package com.techrupt.android.parentbuddy.ViewController.Faqs.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techrupt.android.parentbuddy.R;

/**
 * Created by ravindersingh on 22/02/17.
 */

public class FaqFragment extends Fragment {

    public static Fragment newInstance()
    {
        return new FaqFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View faqview=inflater.inflate(R.layout.activity_fragment_all_faqs,container,false);
        return faqview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
