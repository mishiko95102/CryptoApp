package com.example.cryptoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cryptoapp.databinding.FragmentRecoveryBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryFragment extends Fragment {

    private FragmentRecoveryBinding binding;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecoveryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners(){
        binding.btnResetPassword.setOnClickListener(view -> {
            resetPassword();
        });
        binding.btnBack.setOnClickListener(view -> {
            Navigation.findNavController(view).popBackStack();
        });
    }
    private void resetPassword(){
        String email = binding.etEmail.getText().toString();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Navigation.findNavController(binding.getRoot()).popBackStack();
                Snackbar.make(binding.getRoot(), "Check Email", Snackbar.LENGTH_SHORT).show();
            }
            else {
                Snackbar.make(binding.getRoot(), "Error", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
