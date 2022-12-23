package com.example.shopforyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ForthProductsActivity extends AppCompatActivity {

    private ItemAdapter.RecyclerViewClickListener listener;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Person>userList;
    ItemAdapter adapter;
    private SearchView searchView;
    FloatingActionButton btnOpenDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth_products);
        initData();
        initRecylerView();
        recyclerView=findViewById(R.id.rcview);
        btnOpenDialog=findViewById(R.id.btnOpenDialog);
        searchView=findViewById(R.id.searchView1);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(ForthProductsActivity.this);
                dialog.setContentView(R.layout.add_dialog);

                EditText edtName= dialog.findViewById(R.id.edtName);
                EditText edtNumber=dialog.findViewById(R.id.edtNumber);
                Button btnAction=dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",des="";


                        if (!edtName.getText().toString().equals("")){
                             name= edtName.getText().toString();
                        }else {
                            Toast.makeText(ForthProductsActivity.this, "Please Fill Product Name", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")) {
                             des = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(ForthProductsActivity.this, "Please Fill Product Details", Toast.LENGTH_SHORT).show();
                        }
                        userList.add(new Person(R.drawable.logoapp,name,des));

                        adapter.notifyItemInserted(userList.size()-1);

                        recyclerView.scrollToPosition(userList.size()-1);

                        dialog.dismiss();
                    }

                });
                dialog.show();

            }
        });



    }



    private void initData() {

        userList = new ArrayList<>();

        userList.add(new Person(R.drawable.el1,"HeadPhone","Amazing Quality"));
        userList.add(new Person(R.drawable.el2,"HeadPhone","Amazing Quality"));
        userList.add(new Person(R.drawable.el3,"Monitor","Full Hd Monitor"));
        userList.add(new Person(R.drawable.el4,"Monitor","4k Monitor"));
        userList.add(new Person(R.drawable.el5,"Keyboard+Mouse","RGB Colors"));
        userList.add(new Person(R.drawable.el6,"Keyboard+Mouse","RGB Colors"));


    }

    private void initRecylerView() {
        setOnClickListner();

    recyclerView=findViewById(R.id.rcview);

    layoutManager=new LinearLayoutManager(this);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    adapter= new ItemAdapter(userList,listener);
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();

    }

    private void setOnClickListner() {
        listener=new ItemAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                switch (position){

                    default :{
                      showMyDialog(position);
                    }
                }

            }
        };
    }

    private void fileList(String text) {
        List<Person> filteredList=new ArrayList<>();
        for (Person item : userList){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList(filteredList);
        }
    }




    private AdapterView.OnItemClickListener mMesageClickHandler=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Materiel"+position);
            showMyDialog(position);
        }
    };
    private void showMyDialog(int pos) {
        Dialog dialog = new Dialog(ForthProductsActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        TextView module = dialog.findViewById(R.id.mod);
        TextView credit = dialog.findViewById(R.id.cred);
        TextView desc = dialog.findViewById(R.id.desc);
        Button b = dialog.findViewById(R.id.ok);
        switch (pos){
            case 0:{

                module.setText("Name:HeadPhone");
                credit.setText("Price:180TND");
                desc.setText("Description : Best Quality Ever ");
                break;
            }
            case 1:{

                module.setText("Name:HeadPhone");
                credit.setText("Price:150TND");
                desc.setText("Description : Best Quality Ever");
                break;
            }
            case 2:{

                module.setText("Name:Monitor");
                credit.setText("Price:1250TND");
                desc.setText("Description : Best Quality Ever Full HD ");
                break;
            }
            case 3:{

                module.setText("Name:Monitor");
                credit.setText("Price:990TND");
                desc.setText("Description : Best Quality Ever 4K ");
                break;
            }
            case 4:{

                module.setText("Name:Keyboard+Mouse");
                credit.setText("Price:190TND");
                desc.setText("Description :Best Performance Ever");
                break;
            }
            case 5:{

                module.setText("Name:Keyboard+Mouse");
                credit.setText("Price:290TND");
                desc.setText("Description :Best Performance Ever");
                break;
            }

        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { dialog.dismiss();
                Intent i=new Intent(ForthProductsActivity.this,FifthProductsActivity.class);
                i.putExtra("Module",module.getText().toString());
                i.putExtra("credit",credit.getText().toString());
                i.putExtra("desc",desc.getText().toString());
                i.putExtra("image",userList.get(pos).getImage());
                startActivity(i);
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.it1:{
                Intent i= new Intent(ForthProductsActivity.this,SecondProductsActivity.class);
                startActivity(i);
                break;}
            case R.id.it2:{
                Intent c= new Intent(ForthProductsActivity.this,ProductDetailActivity.class);
                startActivity(c);
                break;}

            case R.id.it3:{
                Intent d= new Intent(ForthProductsActivity.this,ThirdProductsActivity.class);
                startActivity(d);
                break;}
            case R.id.it4:{
                Intent e= new Intent(ForthProductsActivity.this,ForthProductsActivity.class);
                startActivity(e);
                break;}
        }
        return true;
    };




}