package com.example.cryptoapp.model.remote.retrofit;

import com.example.cryptoapp.model.remote.crypto.CryptoService;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// აქ იქმნება რეტროფიტი რასაც გადაეცემა ლინკი
public class RetrofitInstance {

    public static Retrofit getCryptoRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.coinpaprika.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    // რეტროფიტს გადავცემთ სერვისს (მთლიან ინფორმაციას იღებს)
    public static CryptoService getCryptoService(){
        return getCryptoRetrofit().create(CryptoService.class);
    }

    // აქ კი გადავცემთ რეტროფიტს კონკრეტული id-is მიხედვით
    public static CryptoService getCryptoByIdService(){
        return getCryptoRetrofit().create(CryptoService.class);
    }
}
