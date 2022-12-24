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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FifthProductsActivity extends AppCompatActivity {
    TextView t1,t2,t3;
    ImageView im1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_products);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        t1=findViewById(R.id.textView1);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        btn2=findViewById(R.id.add1);
        im1=findViewById(R.id.imageView2);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();
        String s= i.getStringExtra("Module");
        String x= i.getStringExtra("credit");
        String c= i.getStringExtra("desc");
        t1.setText(s);
        t2.setText(x);
        t3.setText(c);
        if (extras != null) {

            int img = extras.getInt("image");

            im1.setImageResource(img);
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FifthProductsActivity.this, "Coming Soon... ", Toast.LENGTH_SHORT).show();
            }
        });


    }






    private AdapterView.OnItemClickListener mMesageClickHandler=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Materiel"+position);
            showMyDialog(position);
        }
    };
    private void showMyDialog(int pos) {
        Dialog dialog = new Dialog(FifthProductsActivity.this);
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
                Intent i= new Intent(FifthProductsActivity.this,SecondProductsActivity.class);
                startActivity(i);
                break;}
            case R.id.it2:{
                Intent c= new Intent(FifthProductsActivity.this,ProductDetailActivity.class);
                startActivity(c);
                break;}

            case R.id.it3:{
                Intent d= new Intent(FifthProductsActivity.this,ThirdProductsActivity.class);
                startActivity(d);
                break;}
            case R.id.it4:{
                Intent e= new Intent(FifthProductsActivity.this,FifthProductsActivity.class);
                startActivity(e);
                break;}
        }
        return true;
    };
}