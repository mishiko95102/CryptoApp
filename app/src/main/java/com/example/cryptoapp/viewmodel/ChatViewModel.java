package com.example.cryptoapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cryptoapp.model.remote.chat.Message;
import com.example.cryptoapp.view.adapter.MessageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatViewModel extends ViewModel {

    DatabaseReference mdb = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<List<Message>> messageLiveData = new MutableLiveData<>();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user;



    public void sendMessage(String messagee){
        user = auth.getCurrentUser();
        String dateTime = new SimpleDateFormat("dd-MM-yy HH:mma").format(Calendar.getInstance().getTime());
        mdb.child("Messages").push().setValue(new Message(user.getEmail(), messagee, dateTime)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }


    public void receiveMessages(){
        mdb.child("Messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Message> messages = new ArrayList<>();

                for (DataSnapshot messageSnapshot : snapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    messages.add(message);
                }


                MessageAdapter adapter = new MessageAdapter(messages);
                messageLiveData.setValue(messages);
                adapter.updateList(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            };
        });
    }




    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
