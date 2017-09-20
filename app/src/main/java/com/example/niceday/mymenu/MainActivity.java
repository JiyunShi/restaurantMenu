package com.example.niceday.mymenu;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, ResultFragment.OnFragmentInteractionListener {

    ResultFragment resultFragment= new ResultFragment();
    MenuFragment menuFragment = new MenuFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.content, menuFragment).commit();




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onFragmentInteraction(ArrayList<String> qtys,ArrayList<String> items,ArrayList<String> prices){


        Bundle bundle = new Bundle();

        bundle.putStringArrayList("qtys", qtys);
        bundle.putStringArrayList("names", items);
        bundle.putStringArrayList("prices",prices);

        resultFragment.setArguments(bundle);
        if(resultFragment.isAdded()){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(resultFragment).attach(resultFragment).commit();
        }

    }


    public void openNew(View view) {
        Button switchButton = (Button)findViewById(R.id.switchResultBtn);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter,R.anim.exit);
        ft2.setCustomAnimations(R.anim.menuenter,R.anim.enter);
        if(!resultFragment.isAdded())
        {
            switchButton.setText("Hide Bill");
            ft.add(R.id.content, resultFragment).commit();
        }
        else {

            if(resultFragment.isHidden()){
                switchButton.setText("Hide Bill");
                ft.show(resultFragment).commit();
            }else{
                switchButton.setText("Show Bill");
                ft.hide(resultFragment).commit();
                ft2.hide(menuFragment).show(menuFragment).commit();

            }
        }




    }
}
