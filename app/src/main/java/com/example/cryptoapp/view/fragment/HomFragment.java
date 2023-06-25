package com.example.cryptoapp.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cryptoapp.databinding.FragmentHomBinding;

public class HomFragment extends Fragment {
    private FragmentHomBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners(){
        binding.tvWhatIsCryptoCurrency.setOnClickListener(view -> {
            String url = "https://www.kaspersky.com/resource-center/definitions/what-is-cryptocurrency";
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(url));
            startActivity(web);
        });
        binding.tvWhatIsBitcoin.setOnClickListener(view -> {
            String url = "https://www.investopedia.com/terms/b/bitcoin.asp";
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(url));
            startActivity(web);
        });

        binding.tvWhatIsEthereum.setOnClickListener(view -> {
            String url = "https://www.techtarget.com/whatis/definition/Ethereum";
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(url));
            startActivity(web);
        });
        binding.tvWhatIsTether.setOnClickListener(view -> {
            String url = "https://www.forbes.com/advisor/investing/cryptocurrency/what-is-tether-usdt/";
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(url));
            startActivity(web);
        });
    }


}
