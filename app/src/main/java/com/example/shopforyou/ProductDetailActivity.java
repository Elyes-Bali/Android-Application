package com.example.shopforyou;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProductDetailActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        initData();
        initRecylerView();
        btnOpenDialog=findViewById(R.id.btnOpenDialog1);
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
                Dialog dialog=new Dialog(ProductDetailActivity.this);
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
                            Toast.makeText(ProductDetailActivity.this, "Please Fill Product Name", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")) {
                            des = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(ProductDetailActivity.this, "Please Fill Product Details", Toast.LENGTH_SHORT).show();
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

        userList.add(new Person(R.drawable.ph1,"IPhone","Price:2200TND"));
        userList.add(new Person(R.drawable.ph2,"Samsung","Price:890TND"));
        userList.add(new Person(R.drawable.ph3,"Samsung","Price:1950TND"));
        userList.add(new Person(R.drawable.ph4,"Infinix","Price:790TND"));
        userList.add(new Person(R.drawable.ph5,"IPhone","Price:3480TND"));
        userList.add(new Person(R.drawable.ph6,"Samsung","Price:2080TND"));


    }

    private void initRecylerView() {
        setOnClickListner();

        recyclerView=findViewById(R.id.rcview1);

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
        Dialog dialog = new Dialog(ProductDetailActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        TextView module = dialog.findViewById(R.id.mod);
        TextView credit = dialog.findViewById(R.id.cred);
        TextView desc = dialog.findViewById(R.id.desc);
        Button b = dialog.findViewById(R.id.ok);
        switch (pos){
            case 0:{
                module.setText("Name:IPhone");
                credit.setText("Price:2200TND");
                desc.setText("Avec iOS 16, vous pouvez personnaliser votre écran verrouillé de façons inédites. Détourez une partie de votre photo pour la mettre en avant. Suivez l’évolution de vos anneaux Activité. Et voyez en direct les informations de vos apps préférées.");
                break;
            }
            case 1:{
                module.setText("Name:Samsung");
                credit.setText("Price:890TND");
                desc.setText("Description :La vie ne ralentit pas, faites une pause pour vous ressourcer et quelque chose vous manquera très vite. La batterie s'adapte intelligemment à la façon dont vous utilisez votre téléphone, vous pouvez donc l'utiliser pendant plus de 24 heures. La charge ultra-rapide de 25 W pour le Galaxy S22 et la charge ultra-rapide de 45 W pour le Galaxy S22+ sont certifiées conformes à la norme USB-IF");
                break;
            }
            case 2:{
                module.setText("Name:Samsung");
                credit.setText("Price:1950TND");
                desc.setText("Description :La vie ne ralentit pas, faites une pause pour vous ressourcer et quelque chose vous manquera très vite. La batterie s'adapte intelligemment à la façon dont vous utilisez votre téléphone, vous pouvez donc l'utiliser pendant plus de 24 heures. La charge ultra-rapide de 25 W pour le Galaxy S22 et la charge ultra-rapide de 45 W pour le Galaxy S22+ sont certifiées conformes à la norme USB-IF");
                break;
            }
            case 3:{
                module.setText("Name:Infinix");
                credit.setText("Price:790TND");
                desc.setText("Description :Processeur de jeu MediaTek Helio G96 Ultra\n" +
                        "\n" +
                        "Écran FHD+Ultra Fluide 120 Hz 6,95\"\n" +
                        "\n" +
                        "Caméra ultra-nuit 64MP\n" +
                        "\n" +
                        "Zoom téléscope X30\n" +
                        "\n" +
                        "Batterie 5000mAh + Super Charge 33W\n" +
                        "\n" +
                        "Kit de jeu de monstre\n" +
                        "\n" +
                        "La collaboration multi-écrans");
                break;
            }
            case 4:{
                module.setText("Name:IPhone");
                credit.setText("Price:3480TND");
                desc.setText("Description :Avec iOS 16, vous pouvez personnaliser votre écran verrouillé de façons inédites. Détourez une partie de votre photo pour la mettre en avant. Suivez l’évolution de vos anneaux Activité. Et voyez en direct les informations de vos apps préférées.");
                break;
            }
            case 5:{
                module.setText("Name:Samsung");
                credit.setText("Price:2080TND");
                desc.setText("Description :La vie ne ralentit pas, faites une pause pour vous ressourcer et quelque chose vous manquera très vite. La batterie s'adapte intelligemment à la façon dont vous utilisez votre téléphone, vous pouvez donc l'utiliser pendant plus de 24 heures. La charge ultra-rapide de 25 W pour le Galaxy S22 et la charge ultra-rapide de 45 W pour le Galaxy S22+ sont certifiées conformes à la norme USB-IF");
                break;
            }}
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { dialog.dismiss();
                Intent i=new Intent(ProductDetailActivity.this,FifthProductsActivity.class);
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
                Intent i= new Intent(ProductDetailActivity.this,SecondProductsActivity.class);
                startActivity(i);
                break;}
            case R.id.it2:{
                Intent c= new Intent(ProductDetailActivity.this,ProductDetailActivity.class);
                startActivity(c);
                break;}

            case R.id.it3:{
                Intent d= new Intent(ProductDetailActivity.this,ThirdProductsActivity.class);
                startActivity(d);
                break;}
            case R.id.it4:{
                Intent e= new Intent(ProductDetailActivity.this,ForthProductsActivity.class);
                startActivity(e);
                break;}
        }
        return true;
    };

}