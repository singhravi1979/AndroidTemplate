package com.techrupt.android.parentbuddy.ViewController.Faqs.Activity;

import android.support.v4.app.Fragment;

import com.techrupt.android.parentbuddy.ViewController.Faqs.Fragment.FaqFragment;
import com.techrupt.android.parentbuddy.ViewController.SingleFragmentActivity;

/**
 * Created by ravindersingh on 22/02/17.
 */

public class FaqActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return FaqFragment.newInstance();
    }
}
