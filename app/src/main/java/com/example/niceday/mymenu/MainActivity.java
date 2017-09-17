package com.example.niceday.mymenu;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, ResultFragment.OnFragmentInteractionListener {

    ResultFragment resultFragment= new ResultFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.menuContainer, new MenuFragment()).commit();





    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void openNew(View view) {

        if(resultFragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction().remove(resultFragment).commit();
        }
        else

        getSupportFragmentManager().beginTransaction().add(R.id.resultContainer, resultFragment).commit();

    }
}
