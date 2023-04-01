package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.Adaptor.CategoryAdaptor;
import com.example.foodapp.Adaptor.PopularAdaptor;
import com.example.foodapp.Domian.CategoryDomain;
import com.example.foodapp.Domian.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add (new CategoryDomain ("Pizza","cat_1"));
        category.add (new CategoryDomain ("Burger","cat_2"));
        category.add (new CategoryDomain ("HotDog","cat_3"));
        category.add (new CategoryDomain ("Drink","cat_4"));
        category.add (new CategoryDomain ("Donut","cat_5"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);


    }
    private void recyclerViewPopular(){

        LinearLayoutManager  linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza","pop_1","slice Pepperoni , mozzarella cheese, fresh oregano,ground black pepper,pizza sauce",9.76));
        foodList.add(new FoodDomain("cheese Burger","pop_2","beef, Gouda cheese , Special sauce,Lettuce tomato",8.79));
        foodList.add(new FoodDomain("Vegetable pizza","pop_3","olive oil , Pitted kalaMata, Cherry tomatoes,fresh oregano, basil",8.52));

        adapter2= new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);


    }
}