package com.example.cryptoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cryptoapp.databinding.FragmentSignupBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {

    private FragmentSignupBinding binding;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners(){
        binding.btnSignUp.setOnClickListener(view -> {
            signUp();
        });
        binding.btnBack.setOnClickListener(view -> {
            Navigation.findNavController(view).popBackStack();
        });
        binding.tvSignIn.setOnClickListener(view -> {
            Navigation.findNavController(view).popBackStack();
        });
    }

    private void signUp(){
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String repeatPassword = binding.etRepeatPassword.getText().toString();

        if(password.equals(repeatPassword)){
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Navigation.findNavController(binding.getRoot()).popBackStack();
                }
                else {
                    Snackbar.make(binding.getRoot(), "Can't Create A New Account", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Snackbar.make(binding.getRoot(), "Password Don't Match", Snackbar.LENGTH_SHORT).show();
        }
    }
}
