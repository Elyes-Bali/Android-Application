package com.example.shopforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



public class ThirdProductsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_third_products);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        initData();
        initRecylerView();

        btnOpenDialog=findViewById(R.id.btnOpenDialog4);
        searchView=findViewById(R.id.searchView3);
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
                Dialog dialog=new Dialog(ThirdProductsActivity.this);
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
                            Toast.makeText(ThirdProductsActivity.this, "Please Fill Product Name", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")) {
                            des = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(ThirdProductsActivity.this, "Please Fill Product Details", Toast.LENGTH_SHORT).show();
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

        userList.add(new Person(R.drawable.acc1,"Phone Holder","Amazing design"));
        userList.add(new Person(R.drawable.acc2,"Phone Holder","Amazing Colors"));
        userList.add(new Person(R.drawable.acc3,"EarPods","Quality Sounds"));
        userList.add(new Person(R.drawable.acc4,"Phone Case","IPhone cases"));
        userList.add(new Person(R.drawable.acc5,"EarPods","Amazing Sounds"));
        userList.add(new Person(R.drawable.acc6,"Charger","New Design"));


    }

    private void initRecylerView() {
        setOnClickListner();

        recyclerView=findViewById(R.id.rcview3);

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
        Dialog dialog = new Dialog(ThirdProductsActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        TextView module = dialog.findViewById(R.id.mod);
        TextView credit = dialog.findViewById(R.id.cred);
        TextView desc = dialog.findViewById(R.id.desc);
        Button b = dialog.findViewById(R.id.ok);
        switch (pos){
            case 0:{

                module.setText("Name:Phone Holder");
                credit.setText("Price:70TND");
                desc.setText("Description :Amazing design");
                break;
            }
            case 1:{

                module.setText("Name:Phone Holder");
                credit.setText("Price:40TND");
                desc.setText("Description :Amazing Colors");
                break;
            }
            case 2:{

                module.setText("Name:EarPods");
                credit.setText("Price:200TND");
                desc.setText("Description :Quality Sounds");
                break;
            }
            case 3:{

                module.setText("Name:Phone Case");
                credit.setText("Price:50TND");
                desc.setText("Description :IPhone cases");
                break;
            }
            case 4:{

                module.setText("Name:EarPods");
                credit.setText("Price:150TND");
                desc.setText("Description :Amazing Sounds");
                break;
            }
            case 5:{

                module.setText("Name:Charger");
                credit.setText("Price:20TND");
                desc.setText("Description :New Design");
                break;
            }

        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { dialog.dismiss();
                Intent i=new Intent(ThirdProductsActivity.this,FifthProductsActivity.class);
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
                Intent i= new Intent(ThirdProductsActivity.this,SecondProductsActivity.class);
                startActivity(i);
                break;}
            case R.id.it2:{
                Intent c= new Intent(ThirdProductsActivity.this,ProductDetailActivity.class);
                startActivity(c);
                break;}

            case R.id.it3:{
                Intent d= new Intent(ThirdProductsActivity.this,ThirdProductsActivity.class);
                startActivity(d);
                break;}
            case R.id.it4:{
                Intent e= new Intent(ThirdProductsActivity.this,ForthProductsActivity.class);
                startActivity(e);
                break;}
        }
        return true;
    };
}