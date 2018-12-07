package com.example.delllatitude.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ChildEventListener, FirebaseAuth.AuthStateListener{

    EditText etMess;
    Button btnSend;
    RecyclerView rv;
    ArrayList<Message> messageArrayList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Message message = dataSnapshot.getValue(Message.class);
        messageArrayList.add(0, message);
        recyclerAdapter.notifyItemInserted(0);
//
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() == null) {

            Intent loginIntent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build())).build();
            startActivity(loginIntent);

        } else {

            etMess = findViewById(R.id.et);
            btnSend = findViewById(R.id.btn);
            recyclerAdapter = new RecyclerAdapter(this, messageArrayList);
            rv = findViewById(R.id.rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
            rv.setLayoutManager(linearLayoutManager);
            rv.setAdapter(recyclerAdapter);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            final DatabaseReference rootRef = firebaseDatabase.getReference();
//        final DatabaseReference childRef = rootRef.child("test");
//            FirebaseDatabase.getInstance().getReference().child("Chats").


            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String enteredMessage = etMess.getText().toString();
//                Task newTask = new Task(enteredName, "The current Time is ",
//                        String.valueOf(System.currentTimeMillis()),false);
                    Message newMessage = new Message(enteredMessage);
//                childRef.push().setValue(newTask);
                    rootRef.push().setValue(newMessage);
                }
            });

//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

            rootRef.addChildEventListener(this);

        }
    }
}
