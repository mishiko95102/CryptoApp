package com.example.cryptoapp.view.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoapp.databinding.ItemMessageBinding;
import com.example.cryptoapp.model.remote.chat.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {


    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> messages = new ArrayList<>();




    public void updateList(List<Message> newList){
        MessageDiffCallback callback = new MessageDiffCallback(messages, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        messages = newList;
        diffResult.dispatchUpdatesTo(this);
   }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(
                ItemMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private ItemMessageBinding binding;
        public MessageViewHolder(ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Message message){
            binding.tvUserEmail.setText(message.userEmail);
            binding.tvUserMessage.setText(message.message);
            binding.tvUserMessageDateTime.setText(message.dataTime);
        }
    }

    public class MessageDiffCallback extends DiffUtil.Callback{

        public List<Message> oldList;
        public List<Message> newList;

        public MessageDiffCallback(List<Message> oldList, List<Message> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Message oldMessage =oldList.get(oldItemPosition);
            Message newMessage = newList.get(newItemPosition);
            return oldMessage.dataTime == newMessage.dataTime;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Message oldMessage =oldList.get(oldItemPosition);
            Message newMessage = newList.get(newItemPosition);
            return oldMessage.dataTime == newMessage.dataTime &&
                    oldMessage.userEmail.equals(newMessage.userEmail)&&
                    oldMessage.message.equals(newMessage.message);
        }
    }

}
