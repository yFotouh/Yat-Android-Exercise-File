package com.tests.yatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String name = getIntent().getStringExtra("AKEY2");
        boolean exist = getIntent().getBooleanExtra("AKEY3", false);
        TextView tv = (TextView) findViewById(R.id.textView3);
        if (exist == true)
            tv.setText(name + " is " + exist);
        else
            tv.setText(name + " is " + exist);
    }

    private void loadFragment() {
// create a FragmentManager
        BlankFragment blankFragment = new BlankFragment();
        androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragment, blankFragment);
        fragmentTransaction.commit(); // save the changes
    }
}