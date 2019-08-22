package com.example.android.bakingapp.fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Recipe;
import com.example.android.bakingapp.RecipeDetailsActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeStepsFragment extends Fragment {

    private static final String APP_NAME = "BakingApp";
    private static int sStepIndex;
    private static int sLast;

    private Button mPreviousBtn;
    private Button mNextBtn;
    private TextView mDescriptionTv;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private MediaSource mMediaSource;
    private Recipe mCurrentRecipe;


    public RecipeStepsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_steps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewsAndVariables();
        setDescriptionView();
        setMediaSource();
        setButtonEnable();
        setTitleText();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }



    private void initViewsAndVariables() {
        mCurrentRecipe = RecipeDetailsActivity.getCurrentRecipe();
        sStepIndex = RecipeDetailsActivity.getStepIndex();
        sLast = mCurrentRecipe.getSteps().size() - 1;

        mDescriptionTv = getActivity().findViewById(R.id.tv_description);
        mPlayerView = getActivity().findViewById(R.id.player_view);

        mPreviousBtn = getActivity().findViewById(R.id.btn_previous);
        mPreviousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sStepIndex--;
                setDescriptionView();
                setMediaSource();
                setButtonEnable();
                setTitleText();
            }
        });

        mNextBtn = getActivity().findViewById(R.id.btn_next);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sStepIndex++;
                setDescriptionView();
                setMediaSource();
                setButtonEnable();
                setTitleText();
            }
        });

    }

    private void setButtonEnable() {
        if(sStepIndex == 0) {
            mPreviousBtn.setActivated(false);
            mPreviousBtn.setClickable(false);
            mNextBtn.setActivated(true);
            mNextBtn.setClickable(true);
        } else if(sStepIndex == sLast) {
            mPreviousBtn.setActivated(true);
            mPreviousBtn.setClickable(true);
            mNextBtn.setActivated(false);
            mNextBtn.setClickable(false);
        } else {
            mPreviousBtn.setActivated(true);
            mPreviousBtn.setClickable(true);
            mNextBtn.setActivated(true);
            mNextBtn.setClickable(true);
        }
    }

    private void setDescriptionView() {
        String description = mCurrentRecipe.getSteps().get(sStepIndex).getDescription();
        mDescriptionTv.setText(description);
    }

    private void setTitleText() {
        String title = mCurrentRecipe.getSteps().get(sStepIndex).getShortDescription();
        getActivity().setTitle(title);
    }

    private void setMediaSource() {
        releaseMediaPlayer();
        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
        mPlayerView.setPlayer(mPlayer);
        String videoUrl = mCurrentRecipe.getSteps().get(sStepIndex).getVideoURL();
        String thumbnailUrl = mCurrentRecipe.getSteps().get(sStepIndex).getThumbnailURL();
        if(videoUrl != null && !videoUrl.isEmpty()) {
            mMediaSource = createMediaSource(videoUrl);
            mPlayer.prepare(mMediaSource);
            mPlayerView.setVisibility(View.VISIBLE);
        } else if(thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            mMediaSource = createMediaSource(thumbnailUrl);
            mPlayer.prepare(mMediaSource);
            mPlayerView.setVisibility(View.VISIBLE);
        } else {
            mPlayerView.setVisibility(View.GONE);
        }
    }

    private MediaSource createMediaSource(String url) {
        return new ProgressiveMediaSource
                .Factory(new DefaultDataSourceFactory(getContext(),
                        Util.getUserAgent(getContext(), APP_NAME)))
                .createMediaSource(Uri.parse(url));
    }

    private void releaseMediaPlayer() {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

}
