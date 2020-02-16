package com.example.myfirstdatabase;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public  class  MyDBHandler extends AppCompatActivity{
TextView a,b,c,d;
Button btn;
DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.historypassenger);
           Toast.makeText(MyDBHandler.this, "Firebase Connection Success", Toast.LENGTH_LONG).show();

           a=(TextView)findViewById(R.id.name1);
           b=(TextView)findViewById(R.id.ID1);
           c=(TextView)findViewById(R.id.Arrival1);
           d=(TextView)findViewById(R.id.Departure1);
           btn=(Button)findViewById(R.id.button2);
           btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  // FirebaseDatabase database = FirebaseDatabase.getInstance();
                  // DatabaseReference myRef = database.getReference("message");
                 //  Log.e("Db ref",myRef.toString());
                   reff= FirebaseDatabase.getInstance().getReference();
                 //  Toast.makeText(MyDBHandler.this, "initialisation executed", Toast.LENGTH_LONG).show();
                  // Log.e("JSON",reff.toString());
                   reff.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override

                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Toast.makeText(MyDBHandler.this, "On data change executed", Toast.LENGTH_LONG).show();
                           String user=dataSnapshot.child("child").child("ID").getValue().toString();

                           int ctr=1;
                          int  i=0;
                           while(ctr!=-1)
                           {
                               String us1=dataSnapshot.child("child"+i).child("ID").getValue().toString();
                               if(us1.compareTo(user)==0)
                               {
                                   String name1=dataSnapshot.child("child"+i).child("Name").getValue().toString();
                                   Log.e("Name ",name1);
                                   String ID1=dataSnapshot.child("child"+i).child("ID").getValue().toString();
                                   String Arrival1=dataSnapshot.child("child"+i).child("Arrival").getValue().toString();
                                   String Departure1=dataSnapshot.child("child"+i).child("Departure").getValue().toString();
                                   Toast.makeText(MyDBHandler.this, name1+"not found", Toast.LENGTH_LONG).show();
                                   a.setText(name1);
                                   b.setText(ID1);
                                   c.setText(Arrival1);
                                   d.setText(Departure1);
                                   break;
                               }
                               else{
                               i++;}
                           }


                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
           });

    }

}
