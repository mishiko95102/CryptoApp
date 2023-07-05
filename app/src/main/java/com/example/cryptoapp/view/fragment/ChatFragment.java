package com.example.cryptoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cryptoapp.databinding.FragmentChatBinding;
import com.example.cryptoapp.view.adapter.MessageAdapter;
import com.example.cryptoapp.viewmodel.ChatViewModel;


public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;

    private ChatViewModel chatViewModel = new ChatViewModel();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

        setListeners();
        receiveMessages();
    }

    private void init(){
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

    }
    // მესიჯის გაგზავნა DATABASE - ში
    private void setListeners(){
        binding.btnSendMessage.setOnClickListener(view -> {
            String messagee = binding.etMessageBox.getEditText().getText().toString();
            chatViewModel.sendMessage(messagee);
            binding.etMessageBox.getEditText().setText("");

        });
    }
    // მესიჯის წამოღება DATABASE -დან
    private void receiveMessages(){
        chatViewModel.receiveMessages();
        setObservers();
    }

    private void setObservers(){
        chatViewModel.messageLiveData.observe(getViewLifecycleOwner(), messages -> {
            MessageAdapter adapter = new MessageAdapter(messages);
            binding.rvMessage.setAdapter(adapter);
        });
    }
}

