package com.example.collegeapp.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    private RecyclerView noticeRecycler;
    private ProgressBar pd;
    private ArrayList<NoticeData> list;
    private NoticeAdapter ad;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_notice, container, false);


        noticeRecycler = view.findViewById(R.id.noticeRecycler);
        pd = view.findViewById(R.id.pd);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot s : snapshot.getChildren()) {
                    NoticeData data = s.getValue(NoticeData.class);
                    list.add(data);
                }


                ad = new NoticeAdapter(getContext(), list);
                ad.notifyDataSetChanged();

                pd.setVisibility(View.GONE);

                noticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                noticeRecycler.setHasFixedSize(true);
                noticeRecycler.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        return view;


    }
}