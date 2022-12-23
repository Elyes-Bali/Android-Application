package com.example.shopforyou;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
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





public class SecondProductsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_second_products);
        initData();
        initRecylerView();

        btnOpenDialog=findViewById(R.id.btnOpenDialog2);
        searchView=findViewById(R.id.searchView2);
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
                Dialog dialog=new Dialog(SecondProductsActivity.this);
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
                            Toast.makeText(SecondProductsActivity.this, "Please Fill Product Name", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")) {
                            des = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(SecondProductsActivity.this, "Please Fill Product Details", Toast.LENGTH_SHORT).show();
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

        userList.add(new Person(R.drawable.hp1,"HP LAPTOP","Intel N 4M Cache-8G-1T-Windows"));
        userList.add(new Person(R.drawable.hp2,"HP LAPTOP","HP15-I3 11éme Gén"));
        userList.add(new Person(R.drawable.hp3,"HP LAPTOP","HP15-AMD 3020"));
        userList.add(new Person(R.drawable.hp4,"HP LAPTOP","HP15-Intel N-8Gb"));
        userList.add(new Person(R.drawable.hp5,"HP LAPTOP","HP15-N4120 4M"));
        userList.add(new Person(R.drawable.hp6,"HP LAPTOP","Pc Portable I5-11éme"));

        userList.add(new Person(R.drawable.dell1,"DELL LAPTOP","DELL Vostro 3400-I3 11G"));
        userList.add(new Person(R.drawable.dell2,"DELL LAPTOP","DELL Vostro 3500-i3 11G"));
        userList.add(new Person(R.drawable.dell3,"DELL LAPTOP","DELL Portable-Vostro3510-i5-11éme"));
        userList.add(new Person(R.drawable.dell4,"DELL LAPTOP","DELL PC Portable-Inspir3500 -I7 10G"));
        userList.add(new Person(R.drawable.dell5,"DELL LAPTOP","DELL PC Portable-VOSTRO 3500-i3-15.6"));
        userList.add(new Person(R.drawable.dell6,"DELL LAPTOP","DELL Pc Vostro 3400-i3 11éme-4G"));

        userList.add(new Person(R.drawable.len1,"LENOVO LAPTOP","Lenovo Pc-V15-AMD Radeon"));
        userList.add(new Person(R.drawable.len2,"LENOVO LAPTOP","Lenovo Pc-IP3-N4020-1T"));
        userList.add(new Person(R.drawable.len3,"LENOVO LAPTOP","Lenovo IP 3 AMD athlon 3050U"));
        userList.add(new Person(R.drawable.len4,"LENOVO LAPTOP","Lenovo PC portable-IDEAPAD 3"));
        userList.add(new Person(R.drawable.len5,"LENOVO LAPTOP","Lenovo PC V15-IGL-4Go"));
        userList.add(new Person(R.drawable.len6,"LENOVO LAPTOP","Lenovo Pc Portable V15 N4020"));


    }

    private void initRecylerView() {
        setOnClickListner();

        recyclerView=findViewById(R.id.rcview2);

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
        Dialog dialog = new Dialog(SecondProductsActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        TextView module = dialog.findViewById(R.id.mod);
        TextView credit = dialog.findViewById(R.id.cred);
        TextView desc = dialog.findViewById(R.id.desc);
        Button b = dialog.findViewById(R.id.ok);
        switch (pos){
            case 0:{
                module.setText("Name:HP LAPTOP");
                credit.setText("Price:1200TND");
                desc.setText("Description :Restez connecté à ce qui compte le plus avec une batterie longue durée et un écran à bord étroit, le tout dans un design élégant et portable. Construit pour optimiser votre productivité et vous divertir où que vous soyez, l’ordinateur portable HP 39,6 cm (15,6 pouces) offre des performances fiables et un large écran vous permettant de transférer, de surfer et d’enchaîner les tâches rapidement du matin au soir.Windows 10 Edition Familiale ou d'autres systèmes d'exploitation sont disponibles");
                break;
            }
            case 1:{
                module.setText("Name:HP LAPTOP");
                credit.setText("Price:1190TND");
                desc.setText("Description :Restez connecté à ce qui compte le plus avec une batterie longue durée et un écran à bord étroit, le tout dans un design élégant et portable. Construit pour optimiser votre productivité et vous divertir où que vous soyez, l’ordinateur portable HP 39,6 cm (15,6 pouces) offre des performances fiables et un large écran vous permettant de transférer, de surfer et d’enchaîner les tâches rapidement du matin au soir.Windows 10 Edition Familiale ou d'autres systèmes d'exploitation sont disponibles");
                break;
            }
            case 2:{
                module.setText("Name:HP LAPTOP");
                credit.setText("Price:550TND");
                desc.setText("Description :Restez connecté à ce qui compte le plus avec une batterie longue durée et un écran à bord étroit, le tout dans un design élégant et portable. Construit pour optimiser votre productivité et vous divertir où que vous soyez, l’ordinateur portable HP 39,6 cm (15,6 pouces) offre des performances fiables et un large écran vous permettant de transférer, de surfer et d’enchaîner les tâches rapidement du matin au soir.Windows 10 Edition Familiale ou d'autres systèmes d'exploitation sont disponibles");
                break;
            }
            case 3:{
                module.setText("Name:HP LAPTOP");
                credit.setText("Price:590TND");
                desc.setText("Description :Restez connecté à ce qui compte le plus avec une batterie longue durée et un écran à bord étroit, le tout dans un design élégant et portable. Construit pour optimiser votre productivité et vous divertir où que vous soyez, l’ordinateur portable HP 39,6 cm (15,6 pouces) offre des performances fiables et un large écran vous permettant de transférer, de surfer et d’enchaîner les tâches rapidement du matin au soir.Windows 10 Edition Familiale ou d'autres systèmes d'exploitation sont disponibles");
                break;
            }
            case 4:{
                module.setText("Name:HP LAPTOP");
                credit.setText("Price:480TND");
                desc.setText("Description :Restez connecté à ce qui compte le plus avec une batterie longue durée et un écran à bord étroit, le tout dans un design élégant et portable. Construit pour optimiser votre productivité et vous divertir où que vous soyez, l’ordinateur portable HP 39,6 cm (15,6 pouces) offre des performances fiables et un large écran vous permettant de transférer, de surfer et d’enchaîner les tâches rapidement du matin au soir.Windows 10 Edition Familiale ou d'autres systèmes d'exploitation sont disponibles");
                break;
            }
            case 5:{
                module.setText("Name:HP LAPTOP");
                credit.setText("Price:680TND");
                desc.setText("Description :Restez connecté à ce qui compte le plus avec une batterie longue durée et un écran à bord étroit, le tout dans un design élégant et portable. Construit pour optimiser votre productivité et vous divertir où que vous soyez, l’ordinateur portable HP 39,6 cm (15,6 pouces) offre des performances fiables et un large écran vous permettant de transférer, de surfer et d’enchaîner les tâches rapidement du matin au soir.Windows 10 Edition Familiale ou d'autres systèmes d'exploitation sont disponibles");
                break;
            }
            case 6:{
                module.setText("Name:DELL LAPTOP");
                credit.setText("Price:720TND");
                desc.setText("Description :Mince, élégant et sophistiqué, le PC portable DELL Vostro 3400 vous offre la puissance et la mobilité dont vous avez besoin. Son écran de 14\" à cadre mince offrent un excellent confort d'utilisation. Le PC portable DELL Vostro 3400 (N6004VN3400EMEA01) offre d'excellentes performances et un fonctionnement rapide grâce à son processeur Intel Core i3 11è Génération, ses 4 Go de mémoire DDR4 et son Disque Dur de 1 To HDD ");
                break;
            }
            case 7:{
                module.setText("Name:DELL LAPTOP");
                credit.setText("Price:900TND");
                desc.setText("Description :Mince, élégant et sophistiqué, le PC portable DELL Vostro 3400 vous offre la puissance et la mobilité dont vous avez besoin. Son écran de 14\" à cadre mince offrent un excellent confort d'utilisation. Le PC portable DELL Vostro 3400 (N6004VN3400EMEA01) offre d'excellentes performances et un fonctionnement rapide grâce à son processeur Intel Core i3 11è Génération, ses 4 Go de mémoire DDR4 et son Disque Dur de 1 To HDD ");
                break;
            }
            case 8:{
                module.setText("Name:DELL LAPTOP");
                credit.setText("Price:740.10TND");
                desc.setText("Description :Mince, élégant et sophistiqué, le PC portable DELL Vostro 3400 vous offre la puissance et la mobilité dont vous avez besoin. Son écran de 14\" à cadre mince offrent un excellent confort d'utilisation. Le PC portable DELL Vostro 3400 (N6004VN3400EMEA01) offre d'excellentes performances et un fonctionnement rapide grâce à son processeur Intel Core i3 11è Génération, ses 4 Go de mémoire DDR4 et son Disque Dur de 1 To HDD ");
                break;
            }
            case 9:{
                module.setText("Name:DELL LAPTOP");
                credit.setText("Price:490TND");
                desc.setText("Description :Mince, élégant et sophistiqué, le PC portable DELL Vostro 3400 vous offre la puissance et la mobilité dont vous avez besoin. Son écran de 14\" à cadre mince offrent un excellent confort d'utilisation. Le PC portable DELL Vostro 3400 (N6004VN3400EMEA01) offre d'excellentes performances et un fonctionnement rapide grâce à son processeur Intel Core i3 11è Génération, ses 4 Go de mémoire DDR4 et son Disque Dur de 1 To HDD ");
                break;
            }
            case 10:{
                module.setText("Name:DELL LAPTOP");
                credit.setText("Price:620TND");
                desc.setText("Description :Mince, élégant et sophistiqué, le PC portable DELL Vostro 3400 vous offre la puissance et la mobilité dont vous avez besoin. Son écran de 14\" à cadre mince offrent un excellent confort d'utilisation. Le PC portable DELL Vostro 3400 (N6004VN3400EMEA01) offre d'excellentes performances et un fonctionnement rapide grâce à son processeur Intel Core i3 11è Génération, ses 4 Go de mémoire DDR4 et son Disque Dur de 1 To HDD ");
                break;
            }
            case 11:{
                module.setText("Name:DELL LAPTOP");
                credit.setText("Price:650TND");
                desc.setText("Description :Mince, élégant et sophistiqué, le PC portable DELL Vostro 3400 vous offre la puissance et la mobilité dont vous avez besoin. Son écran de 14\" à cadre mince offrent un excellent confort d'utilisation. Le PC portable DELL Vostro 3400 (N6004VN3400EMEA01) offre d'excellentes performances et un fonctionnement rapide grâce à son processeur Intel Core i3 11è Génération, ses 4 Go de mémoire DDR4 et son Disque Dur de 1 To HDD ");
                break;
            }
            case 12:{
                module.setText("Name:LENOVO LAPTOP");
                credit.setText("Price:800.40TND");
                desc.setText("Description :A la fois robuste et performant, le PC portable Lenovo V15-IIL est conçu pour vous permettre de travailler dans de bonnes conditions. Avec sa conception robuste et son écran à cadre mince, il offre un juste milieu entre confort et mobilité. L'ordinateur portable Lenovo V15 offre de bonnes performances de travail au quotidien.");
                break;
            }
            case 13:{
                module.setText("Name:LENOVO LAPTOP");
                credit.setText("Price:390.90TND");
                desc.setText("Description :A la fois robuste et performant, le PC portable Lenovo V15-IIL est conçu pour vous permettre de travailler dans de bonnes conditions. Avec sa conception robuste et son écran à cadre mince, il offre un juste milieu entre confort et mobilité. L'ordinateur portable Lenovo V15 offre de bonnes performances de travail au quotidien.");
                break;
            }
            case 14:{
                module.setText("Name:LENOVO LAPTOP");
                credit.setText("Price:480.20TND");
                desc.setText("Description :A la fois robuste et performant, le PC portable Lenovo V15-IIL est conçu pour vous permettre de travailler dans de bonnes conditions. Avec sa conception robuste et son écran à cadre mince, il offre un juste milieu entre confort et mobilité. L'ordinateur portable Lenovo V15 offre de bonnes performances de travail au quotidien.");
                break;
            }
            case 15:{
                module.setText("Name:LENOVO LAPTOP");
                credit.setText("Price:450.10TND");
                desc.setText("Description :A la fois robuste et performant, le PC portable Lenovo V15-IIL est conçu pour vous permettre de travailler dans de bonnes conditions. Avec sa conception robuste et son écran à cadre mince, il offre un juste milieu entre confort et mobilité. L'ordinateur portable Lenovo V15 offre de bonnes performances de travail au quotidien.");
                break;
            }
            case 16:{
                module.setText("Name:LENOVO LAPTOP");
                credit.setText("Price:685.10TND");
                desc.setText("Description :A la fois robuste et performant, le PC portable Lenovo V15-IIL est conçu pour vous permettre de travailler dans de bonnes conditions. Avec sa conception robuste et son écran à cadre mince, il offre un juste milieu entre confort et mobilité. L'ordinateur portable Lenovo V15 offre de bonnes performances de travail au quotidien.");
                break;
            }
            case 17:{
                module.setText("Name:LENOVO LAPTOP");
                credit.setText("Price:584.90TND");
                desc.setText("Description : A la fois robuste et performant, le PC portable Lenovo V15-IIL est conçu pour vous permettre de travailler dans de bonnes conditions. Avec sa conception robuste et son écran à cadre mince, il offre un juste milieu entre confort et mobilité. L'ordinateur portable Lenovo V15 offre de bonnes performances de travail au quotidien.");
                break;
            }


        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { dialog.dismiss();
                Intent i=new Intent(SecondProductsActivity.this,FifthProductsActivity.class);
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
                Intent i= new Intent(SecondProductsActivity.this,SecondProductsActivity.class);
                startActivity(i);
                break;}
            case R.id.it2:{
                Intent c= new Intent(SecondProductsActivity.this,ProductDetailActivity.class);
                startActivity(c);
                break;}

            case R.id.it3:{
                Intent d= new Intent(SecondProductsActivity.this,ThirdProductsActivity.class);
                startActivity(d);
                break;}
            case R.id.it4:{
                Intent e= new Intent(SecondProductsActivity.this,FifthProductsActivity.class);
                startActivity(e);
                break;}
        }
        return true;
    };
}