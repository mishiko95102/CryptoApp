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
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.cryptoapp.databinding.FragmentCryptoDetailsBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// API - დან წამოღებული ლინკის განთავსება youtubeplayer -ში
public class CryptoDetailsFragment extends Fragment {

    private FragmentCryptoDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        addVideoInYoutubePlayer();
    }

    private void addVideoInYoutubePlayer(){
        String youtube = getArguments().getString("youtube");
        String youtubeId = getYoutubeIdFromUrl(youtube);

        binding.youtubeVideoPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if(youtubeId!=null){
                    youTubePlayer.loadVideo(youtubeId, 0);
                }
                else {
                   binding.youtubeVideoPlayer.setVisibility(View.GONE);
                   binding.tvNoVideo.setText("NO VIDEO");
                }
            }
        });
    }
    public String getYoutubeIdFromUrl(String url) {
        String pattern = "(?i)^.*(?:youtu\\.be\\/|v\\/|u/\\w/|embed\\/|watch\\?v=)([\\w-]{10,12}).*$";
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private void init(){
        String description = getArguments().getString("description");
        binding.tvDescription.setText(description);
        String name = getArguments().getString("name");
        binding.tvName.setText(name);
        String started_at = getArguments().getString("started_at");
        binding.tvStartedAt.setText(started_at);
        String symbol = getArguments().getString("symbol");
        binding.tvSymbol.setText(symbol);
        String development_status = getArguments().getString("development_status");
        binding.tvDevelopmentStatus.setText(development_status);
        binding.btnBack.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(CryptoDetailsFragmentDirections.actionCryptoDetailsFragmentToCryptoApiFragment());
        });
        String website = getArguments().getString("website");
        binding.tvWebsite.setOnClickListener(view -> {
            String url = website;
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(url));
            startActivity(web);
        });

        String logo = getArguments().getString("logo");
        Glide.with(binding.imageview)
                .load(logo)
                .override(150,150)
                .into(binding.imageview);


    }
}
