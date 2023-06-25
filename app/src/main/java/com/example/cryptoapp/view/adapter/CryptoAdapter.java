package com.example.cryptoapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoapp.databinding.ItemCryptoBinding;
import com.example.cryptoapp.model.remote.crypto.Crypto;

import java.util.ArrayList;
import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder> {

    List<Crypto> cryptoList = new ArrayList<>();

    private OnItemClickListener listener = null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.listener = onItemClickListener;
    }
    public void updateList(List<Crypto> newList){

        CryptoDiffCallback callback = new CryptoDiffCallback(cryptoList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        cryptoList = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public CryptoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CryptoViewHolder(
                ItemCryptoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoViewHolder holder, int position) {

        holder.bind(cryptoList.get(position));
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }



    public class CryptoViewHolder  extends RecyclerView.ViewHolder {
        private ItemCryptoBinding binding;

        public CryptoViewHolder(ItemCryptoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Crypto crypto){
            binding.tvId.setText(String.valueOf(crypto.rank));
            binding.tvCryptoC.setText(crypto.idn);
            binding.getRoot().setOnClickListener(view -> {
                if (listener != null){
                    listener.onItemClick(crypto);
                }
            });
        }
    }

    public class CryptoDiffCallback extends DiffUtil.Callback{


        public List<Crypto> oldList;
        public List<Crypto> newList;
        public CryptoDiffCallback(List<Crypto> oldList, List<Crypto> newList) {
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
            Crypto oldCrypto = oldList.get(oldItemPosition);
            Crypto newCrypto = newList.get(newItemPosition);

            return oldCrypto.rank == newCrypto.rank;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Crypto oldCrypto = oldList.get(oldItemPosition);
            Crypto newCrypto = newList.get(newItemPosition);

            return oldCrypto.rank == newCrypto.rank &&
                    oldCrypto.idn.equals(newCrypto.idn)&&
                    oldCrypto.name.equals(newCrypto.name)&&
                    oldCrypto.symbol.equals(newCrypto.symbol);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Crypto crypto);
    }
}
