package fr.bmartel.youtubetv.media;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.bmartel.youtubetv.model.VideoQuality;

/**
 * The first screen of the rental wizard. Gives the user the choice between renting the movie in SD
 * or HD quality.
 */
public class WizardExample1stStepFragment extends WizardExampleBaseStepFragment {

    public static final String TAG = "WizardExample1st";
    private final static String QUALITY_LIST_KEY = "qualityList";

    private List<VideoQuality> mVideoQualities = new ArrayList<>();

    @NonNull
    @Override
    public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
        GuidanceStylist.Guidance guidance = new GuidanceStylist.Guidance("Select a video quality", "", "", null);
        return guidance;
    }

    @Override
    public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {

        mVideoQualities = (List<VideoQuality>) getArguments().getSerializable(QUALITY_LIST_KEY);
        for (int i = 0; i < mVideoQualities.size(); i++) {
            Log.i(TAG, "mVideoQualities : " + i + " : " + mVideoQualities.get(i).getValue());
        }

        for (VideoQuality quality : mVideoQualities) {
            GuidedAction action = new GuidedAction.Builder(getActivity())
                    .id(quality.getIndex())
                    .title(quality.getValue())
                    .editable(false)
                    .description("")
                    .build();
            actions.add(action);
        }
    }

    @Override
    public void onGuidedActionClicked(GuidedAction action) {
        //GuidedStepFragment fragment = WizardExample2ndStepFragment.build(rentHd, this);
        //add(getFragmentManager(), fragment);
    }

    public static Fragment newInstance(List<VideoQuality> availableQualities) {
        WizardExample1stStepFragment fragment = new WizardExample1stStepFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUALITY_LIST_KEY, (Serializable) availableQualities);
        fragment.setArguments(bundle);
        return fragment;
    }
}
