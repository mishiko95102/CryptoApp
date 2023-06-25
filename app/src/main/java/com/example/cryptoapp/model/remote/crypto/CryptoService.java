package com.example.cryptoapp.model.remote.crypto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CryptoService {
    @GET("coins")
    Single<List<Crypto>> getAllCrypto();

    @GET("coins/{coin}")
    Single<Crypto> getCryptoByID(@Path("coin") String coin);
}
