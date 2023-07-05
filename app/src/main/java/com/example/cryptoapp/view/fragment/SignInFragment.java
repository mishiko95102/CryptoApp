package com.example.cryptoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cryptoapp.databinding.FragmentSigninBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

// შესვლა აქაუნთზე
public class SignInFragment extends Fragment {

    private FragmentSigninBinding binding;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSigninBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners(){
        binding.tvForgotPassword.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(SignInFragmentDirections.actionSignInFragmentToRecoveryFragment());
        });
        binding.tvSignUp.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment());
        });
        binding.btnSignIn.setOnClickListener(view -> {
            signIn();
        });
    }

    private void signIn(){
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Navigation.findNavController(binding.getRoot()).navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment());
            }
            else {
                Snackbar.make(binding.getRoot(), "Can't Sign In", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
