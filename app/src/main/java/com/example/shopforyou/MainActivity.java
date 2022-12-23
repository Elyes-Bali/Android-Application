package com.example.shopforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageSlider imageSlider;

    GridView gridView ,grid1;
    String[] mod = new String[]{"LAPTOP","PHONES","ELECTRO-NIC","ACCESS-ORIES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider =findViewById(R.id.image_slider);

        gridView = findViewById(R.id.grid);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mod);
        gridView.setAdapter(adapter);

        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.bg1,null));
        imageList.add(new SlideModel(R.drawable.ph1,null));
        imageList.add(new SlideModel(R.drawable.acc1,null));
        imageList.add(new SlideModel(R.drawable.el1,null));
        imageSlider.setImageList(imageList);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent a = new Intent(MainActivity.this,SecondProductsActivity.class);
                        startActivity(a);
                        break;
                    }
                    case 1:{
                        Intent a = new Intent(MainActivity.this,ProductDetailActivity.class);
                        startActivity(a);
                        break;
                    }
                    case 2:{
                        Intent a = new Intent(MainActivity.this,ThirdProductsActivity.class);
                        startActivity(a);
                        break;
                    }
                    case 3:{
                        Intent a = new Intent(MainActivity.this,ForthProductsActivity.class);
                        startActivity(a);
                        break;
                    }
                }

            }
        });
        grid1=findViewById(R.id.list1);
        ArrayList<Person> arrayList=new ArrayList<>();

        arrayList.add(new Person(R.drawable.hp1,"HP LAPTOP","Quantity:6"));
        arrayList.add(new Person(R.drawable.hp2,"LENOVO LAPTOP","Quantity:6"));
        arrayList.add(new Person(R.drawable.el1,"Headphones","Quantity:2"));
        arrayList.add(new Person(R.drawable.el2,"Headphones","Quantity:2"));
        arrayList.add(new Person(R.drawable.acc1,"Phone Holder","Quantity:2"));
        arrayList.add(new Person(R.drawable.acc2,"Phone Holder","Quantity:2"));
        arrayList.add(new Person(R.drawable.ph1,"IPhone","Quantity:2"));
        arrayList.add(new Person(R.drawable.ph2,"Samsung","Quantity:3"));
        PersonAdapter personAdapter = new PersonAdapter(this,R.layout.list_row,arrayList);
        grid1.setAdapter(personAdapter);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.it1:{
                Intent i= new Intent(MainActivity.this,SecondProductsActivity.class);
                startActivity(i);
                break;}
            case R.id.it2:{
                Intent c= new Intent(MainActivity.this,ProductDetailActivity.class);
                startActivity(c);
                break;}

            case R.id.it3:{
                Intent d= new Intent(MainActivity.this,ThirdProductsActivity.class);
                startActivity(d);
                break;}
            case R.id.it4:{
                Intent e= new Intent(MainActivity.this,ForthProductsActivity.class);
                startActivity(e);
                break;}

            default:
                Toast.makeText(this, "there is no item selected", Toast.LENGTH_SHORT).show();
        }
        return true;
    };
}