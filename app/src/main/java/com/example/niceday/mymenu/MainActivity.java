package com.example.niceday.mymenu;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, ResultFragment.OnFragmentInteractionListener {

    ResultFragment resultFragment= new ResultFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.content, new MenuFragment()).commit();




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onFragmentInteraction(int index, int qty, ArrayList<MenuItem> currentMenu){

        double price = currentMenu.get(index).price;
        String name = currentMenu.get(index).name;

        Bundle bundle = new Bundle();

        bundle.putInt("qty", qty);
        bundle.putString("name", name);
        bundle.putDouble("price",price);

        resultFragment.setArguments(bundle);
        if(resultFragment.isAdded()){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(resultFragment).attach(resultFragment).commit();
        }

    }


    public void openNew(View view) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter,R.anim.exit);
        if(!resultFragment.isAdded())
        {
            ft.add(R.id.content, resultFragment).commit();
        }
        else {

            if(resultFragment.isHidden()){
                ft.show(resultFragment).commit();
            }else{
                ft.hide(resultFragment).commit();
            }
        }
    }
}
