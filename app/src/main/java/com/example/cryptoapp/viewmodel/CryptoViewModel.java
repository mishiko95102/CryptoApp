package com.example.cryptoapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cryptoapp.model.remote.crypto.Crypto;
import com.example.cryptoapp.model.repository.CryptoRepository;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class CryptoViewModel extends ViewModel {

    private CompositeDisposable disposables = new CompositeDisposable();
    public CryptoRepository cryptoRepository = new CryptoRepository();
    public MutableLiveData<List<Crypto>> cryptoLiveData = new MutableLiveData<>();
    public MutableLiveData<Crypto> cryptoByIDLiveData  = new MutableLiveData<>();


    public void getCrypto(){
        disposables.add(
                cryptoRepository.getAllCrypto().subscribe(
                        cryptos -> {
                            cryptoLiveData.setValue(cryptos);
                        }
                )
        );
    }

    public void getCryptoByID(String coin){
        disposables.add(
                cryptoRepository.getCryptoByID(coin).subscribe(
                        crypto -> {
                            cryptoByIDLiveData.setValue(crypto);
                        }
                )
        );
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
