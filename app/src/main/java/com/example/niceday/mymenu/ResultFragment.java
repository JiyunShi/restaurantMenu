package com.example.niceday.mymenu;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.Math.round;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String> qtys;
    private ArrayList<String> names;
    private ArrayList<String> prices;
    private TextView resultTxt;
    private LinearLayout resultMain;
    private OnFragmentInteractionListener mListener;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_result, container,false);
        resultTxt = (TextView) _view.findViewById(R.id.resultTxt);
        resultMain = (LinearLayout) _view.findViewById(R.id.resultMain);

        if (getArguments() != null) {
            qtys = getArguments().getStringArrayList("qtys");
            names = getArguments().getStringArrayList("names");
            prices = getArguments().getStringArrayList("prices");

            if(qtys.size()==0)resultTxt.setText("No item selected");
            else {

                resultTxt.setText("Bill Details");
                buildResult(qtys,names,prices);

            };

        }else{
            resultTxt.setText("No item selected");
        }
        // Inflate the layout for this fragment




        return _view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }



    public void buildResult(ArrayList<String> qtys, ArrayList<String> items, ArrayList<String> prices){
        double totalPrice = 0;
        for(int i=0; i<qtys.size();i++){

            LinearLayout resultItemLine =  new LinearLayout(getContext());

            resultItemLine.setOrientation(LinearLayout.HORIZONTAL);
            resultItemLine.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            TextView itemName =new TextView(getContext());
            TextView itemPrice = new TextView(getContext());

            itemName.setTextColor(Color.parseColor("#ffffff"));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT,2.5f);
            lp.setMargins(10,0,0,0);
            itemName.setLayoutParams(lp);
            itemName.setGravity(Gravity.LEFT);

            itemPrice.setTextColor(Color.parseColor("#ffffff"));
            itemPrice.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));
            itemPrice.setGravity(Gravity.RIGHT);

            String[] name = items.get(i).split(" ");
            String shortName = name[(name.length-2)]+" "+name[name.length-1];
            itemName.setText(shortName);
            itemPrice.setText("--$"+prices.get(i));

            resultItemLine.addView(itemName);
            resultItemLine.addView(itemPrice);


            TextView itemQty = new TextView(getContext());
            itemQty.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            itemQty.setGravity(Gravity.RIGHT);
            itemQty.setText("x "+qtys.get(i));

            resultMain.addView(resultItemLine);
            resultMain.addView(itemQty);

            totalPrice += Double.parseDouble(prices.get(i))*Double.parseDouble(qtys.get(i));

        }

        View divider1 = new View(getContext());
        divider1.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1));
        divider1.setBackgroundColor(Color.parseColor("#f0f8ff"));

        TextView Subtotal = new TextView(getContext());
        Subtotal.setTextColor(Color.parseColor("#ffffff"));
        Subtotal.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        Subtotal.setGravity(Gravity.CENTER);
        Subtotal.setText("subtotal: $"+String.valueOf((double)round(totalPrice*1000)/1000));

        resultMain.addView(divider1);
        resultMain.addView(Subtotal);


        TextView tax = new TextView(getContext());

        tax.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tax.setGravity(Gravity.RIGHT);
        tax.setText("+GST: $"+String.valueOf((double)round(totalPrice*0.05*1000)/1000)+"\n+PST: $"+String.valueOf((double)round(totalPrice*0.07*1000)/1000));

        TextView total = new TextView(getContext());
        total.setTextColor(Color.parseColor("#ffffff"));
        total.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        total.setGravity(Gravity.RIGHT);
        total.setText("Total: $"+String.valueOf((double)round(totalPrice*1.12*1000)/1000));

        View divider2 = new View(getContext());
        divider2.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1));
        divider2.setBackgroundColor(Color.parseColor("#f0f8ff"));

        resultMain.addView(tax);
        resultMain.addView(divider2);
        resultMain.addView(total);

    }








}
