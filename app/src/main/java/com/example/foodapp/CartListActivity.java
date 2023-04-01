package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Adaptor.CartListAdaptor;
import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.example.foodapp.TinyDB.ManagementCart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    TextView totalFeeTxt , taxTxt , deliveryTxt, totalTxt, emptyTxt;
    private  double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }
private void bottomNavigation(){
    FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
    LinearLayout homeBtn = findViewById(R.id.homeBtn);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(CartListActivity.this,CartListActivity.class));
        }
    });
    homeBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(CartListActivity.this, MainActivity.class));
        }
    });
}
    private void initView() {
        recyclerView=findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
        recyclerView=findViewById(R.id.cartView);

    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();

            }

        });

        recyclerView.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percentTax=0.82;
        double delivery=10;

        double tax = Math.round(managementCart.getTotalFee()*percentTax+100)/100.0;
        double total = Math.round(managementCart.getTotalFee()+tax+delivery*100)/100.0;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100.0;
totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$" + tax);



    }
}