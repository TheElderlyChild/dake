package com.application.dake.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.application.dake.R;
import com.application.dake.models.OrderItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemSelectFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "param1";
    private static final String ARG_DESCRIPTION = "param2";
    private static final String ARG_PRICE = "param3";
    private static final String ARG_RESTAURANT_ID = "param4";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // TODO: Rename and change types of parameters
    private String name;
    private String description;
    private String price;
    private String restaurantID;
    private int amount;
    private TextView textName;
    private TextView textDescription;
    private TextView textPrice;
    private TextView textAmount;
    private Button plusItem;
    private Button minusItem;
    private Button btnCancel;
    private Button btnAddCart;

    public MenuItemSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuItemSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuItemSelectFragment newInstance(String param1, String param2, String param3, String param4) {
        MenuItemSelectFragment fragment = new MenuItemSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, param1);
        args.putString(ARG_DESCRIPTION, param2);
        args.putString(ARG_PRICE, param3);
        args.putString(ARG_RESTAURANT_ID, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            description = getArguments().getString(ARG_DESCRIPTION);
            price = getArguments().getString(ARG_PRICE);
            restaurantID = getArguments().getString(ARG_RESTAURANT_ID);
            amount=1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_item_select, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textName=view.findViewById(R.id.textName);
        textDescription=view.findViewById(R.id.textDescription);
        textPrice=view.findViewById(R.id.textPrice);
        textAmount= view.findViewById(R.id.textAmount);
        plusItem= view.findViewById(R.id.plusItem);
        minusItem = view.findViewById(R.id.minusItem);
        btnCancel =view.findViewById(R.id.btnCancel);
        btnAddCart =view.findViewById(R.id.btnAddCart);

        textName.setText(name);
        textDescription.setText(description);
        textPrice.setText(price);
        textAmount.setText(String.valueOf(amount));

        plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount+=1;
                textAmount.setText(String.valueOf(amount));
            }
        });

        minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount<=0){
                    return;
                }
                amount-=1;
                textAmount.setText(String.valueOf(amount));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddToCart
                if(amount!=0) {
                    CollectionReference cartRef = db.collection("customer").document(mAuth.getUid())
                            .collection("cart");
                    cartRef.document().set(new OrderItem(name, amount, Double.valueOf(price),restaurantID));
                }
                dismiss();
            }
        });
    }
}