package com.example.niceday.mymenu;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.System.currentTimeMillis;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<MenuItem> lunchMenu = new ArrayList<MenuItem>();
    ArrayList<MenuItem> dinnerMenu = new ArrayList<MenuItem>();
    ArrayList<MenuItem> currentMenu = new ArrayList<MenuItem>();

    ArrayList<Button> addbtns = new ArrayList<Button>();
    ArrayList<Button> reducebtns = new ArrayList<Button>();
    ArrayList<TextView> itemqtys = new ArrayList<TextView>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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

        this.createMenu();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_menu, container,false);

        LinearLayout rootMenu = _view.findViewById(R.id.rootMenu);
        TextView menuTitle = _view.findViewById(R.id.menuTitleTxt);
        this.setupMenu(rootMenu, menuTitle);

        return _view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(ArrayList<String> qtys,ArrayList<String> items,ArrayList<String> prices ) {
        if (mListener != null) {
            mListener.onFragmentInteraction(qtys,items, prices);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ArrayList<String> qtys,ArrayList<String> items,ArrayList<String> prices);
    }




    //create MenuList
    public void createMenu(){

        MenuItem lunchItem1 = new MenuItem("Chili Cheese Fries", "Crispy Fries topped with Chili & Cheese", 7.95);
        MenuItem lunchItem2 = new MenuItem("Spicy Southwest Chicken Sandwich", "Fresh Redbird Chicken Breast perfectly grilled with fire roasted green Chiles", 10.95);
        MenuItem lunchItem3 = new MenuItem("BBQ Pulled Pork Sandwich", "Slow cooked Pulled Pork with BBQ Sauce and Coleslaw on a Bricoche Bun", 11.95);
        MenuItem lunchItem4 = new MenuItem("Veggie Wrap", "Portabella Mushrooms, Roasted Red pepers, Lettuce, Tomato Wrap", 8.95);
        lunchMenu.add(lunchItem1);
        lunchMenu.add(lunchItem2);
        lunchMenu.add(lunchItem3);
        lunchMenu.add(lunchItem4);
        MenuItem dinnerItem1 = new MenuItem("Potato Skins", "Potato Skins topped with melted cheddar Cheese, Bacon Bits, Sour Cream & Cheese", 9.95);
        MenuItem dinnerItem2 = new MenuItem("Turkey Club", "Hand carved Turkey Breast, Bacon, Lettuce, Tomato, Swiss Cheese & a touch of Mayo piled on our hearty Multi grain Bread", 10.95);
        MenuItem dinnerItem3 = new MenuItem("Fish & Chips", "Tempurs hand bailered While Fish filets, French Fries with a side of horseadish Dijon", 11.95);
        MenuItem dinnerItem4 = new MenuItem("Rock Gardens Salad", "Organic Baby Mixed Greens topped with Cranberries, Chopped Tomatos, Red Onions, Feta Cheese served with a pomegranate Vinaigrette", 8.95);
        dinnerMenu.add(dinnerItem1);
        dinnerMenu.add(dinnerItem2);
        dinnerMenu.add(dinnerItem3);
        dinnerMenu.add(dinnerItem4);

    }

    public LinearLayout insertMenuItem(int index, ArrayList<MenuItem> menuLists){
        LinearLayout menuLine = new LinearLayout(getContext());
        menuLine.setOrientation(LinearLayout.HORIZONTAL);
        menuLine.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout menuItem = new LinearLayout(getContext());
        menuItem.setOrientation(LinearLayout.VERTICAL);
        menuItem.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,2.0f));

        TextView itemName = new TextView(getContext());
        itemName.setTextColor(Color.parseColor("#ff0000"));

        TextView itemDesc = new TextView(getContext());
        itemDesc.setTextColor(Color.parseColor("#ffffff"));


        itemName.setText(menuLists.get(index).name +" -- $"+menuLists.get(index).price);
        itemDesc.setText(menuLists.get(index).description);
        menuItem.addView(itemName);
        menuItem.addView(itemDesc);


        LinearLayout menuButton = new LinearLayout(getContext());
        menuButton.setOrientation(LinearLayout.HORIZONTAL);
        menuButton.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));


        Button addBtn = new Button(getContext());
        Button reduceBtn = new Button(getContext());

        addBtn.setLayoutParams(new LinearLayout.LayoutParams(
                80, 80));
        addBtn.setTextColor(Color.parseColor("#ffffff"));
        addBtn.setText("+");
        addBtn.setId(View.generateViewId());
        addBtn.setOnClickListener(this);
        reduceBtn.setLayoutParams(new LinearLayout.LayoutParams(
                80, 80));
        reduceBtn.setTextColor(Color.parseColor("#ffffff"));
        reduceBtn.setText("-");
        reduceBtn.setId(View.generateViewId());
        reduceBtn.setOnClickListener(this);

        addbtns.add(addBtn);
        reducebtns.add(reduceBtn);

        TextView itemQty = new TextView(getContext());

        itemQty.setLayoutParams(new LinearLayout.LayoutParams(
                80, 80));
        itemQty.setTextColor(Color.parseColor("#ffffff"));
        itemQty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        itemQty.setText("0");

        itemqtys.add(itemQty);

        menuButton.addView(reduceBtn);
        menuButton.addView(itemQty);
        menuButton.addView(addBtn);
        menuLine.addView(menuItem);
        menuLine.addView(menuButton);
        return menuLine;
    }

    public void setupMenu(LinearLayout rootMenu, TextView title){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long lunchStartMilli = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 15);
        long lunchOverDinnerStartMilli = cal.getTimeInMillis();
        cal.add(Calendar.HOUR_OF_DAY,8);
        long dinnerOverMilli = cal.getTimeInMillis();
        long currentTime = currentTimeMillis();

        if(currentTime>=lunchStartMilli&&currentTime<=lunchOverDinnerStartMilli){
            currentMenu = lunchMenu;
            title.setText("Lunch Menu");
            for(int i=0; i<lunchMenu.size();i++) rootMenu.addView(this.insertMenuItem(i, lunchMenu));
        }

        else if(currentTime>=lunchOverDinnerStartMilli&&currentTime<=dinnerOverMilli){
            currentMenu=dinnerMenu;
            for(int i=0; i<dinnerMenu.size();i++) rootMenu.addView(this.insertMenuItem(i, dinnerMenu));
            title.setText("Dinner Menu");
        }else{
            title.setText("Closing now");
        }





    }

    public void onClick(View view){

        ArrayList<String> qtys= new ArrayList<String>();
        ArrayList<String> selectedItems = new ArrayList<String>();
        ArrayList<String> prices = new ArrayList<String>();

        for(int i=0; i<addbtns.size();i++){
            TextView itemqty = itemqtys.get(i);
            int qty = Integer.parseInt(itemqty.getText().toString());
            if(view.getId()==addbtns.get(i).getId()){


                qty++;
                itemqty.setText(String.valueOf(qty));


            }else if(view.getId()==reducebtns.get(i).getId()){

                if(qty>0) qty--;
                itemqty.setText(String.valueOf(qty));

            }

            if(qty>0){
                qtys.add(String.valueOf(qty));
                selectedItems.add(currentMenu.get(i).name);
                prices.add(String.valueOf(currentMenu.get(i).price));
            }


        }

        if(qtys.size()>=0) this.onButtonPressed(qtys,selectedItems,prices);

    }





}
