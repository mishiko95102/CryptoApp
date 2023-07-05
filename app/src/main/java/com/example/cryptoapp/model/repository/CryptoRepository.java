package com.example.cryptoapp.model.repository;

import com.example.cryptoapp.model.remote.crypto.Crypto;
import com.example.cryptoapp.model.remote.retrofit.RetrofitInstance;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

// ყველა კრიპტოს წამოღება
public class CryptoRepository {

    // ყველა კრიპტო
    public Single<List<Crypto>> getAllCrypto(){
        return RetrofitInstance.getCryptoService().getAllCrypto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // კონკრეტული კრიპტო ინფორმაცია ID - ის მიხედვით
    public Single<Crypto> getCryptoByID(String coin){
        return RetrofitInstance.getCryptoByIdService().getCryptoByID(coin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
