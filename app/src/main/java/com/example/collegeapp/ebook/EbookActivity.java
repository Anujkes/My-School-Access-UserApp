package com.example.collegeapp.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter ebookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);


        ebookRecycler=findViewById(R.id.ebookRecycler);
        reference= FirebaseDatabase.getInstance().getReference().child("Pdf");

        getData();






    }

    private void getData() {


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for(DataSnapshot s: snapshot.getChildren())
                {
                   EbookData data=s.getValue(EbookData.class);
                   list.add(data);

                }
                ebookRecycler.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                ebookAdapter=new EbookAdapter(EbookActivity.this,list);
                ebookRecycler.setAdapter(ebookAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}