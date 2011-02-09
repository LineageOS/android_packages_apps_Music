package com.android.music;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MusicSettingsActivity extends PreferenceActivity {

    static final String KEY_ENABLE_GESTURES = "enable_gestures";
    static final String KEY_ENABLE_HAPTIC_FEEDBACK = "enable_haptic_feedback";
    static final String KEY_HAS_CUSTOM_GESTURES = "has_custom_gestures";
    //This key has the gesture entry name (E.g. PAUSE) appended to it before use
    static final String KEY_HAS_CUSTOM_GESTURE_XXX = "has_custom_gesture_";
    //Used to signal that a previously loaded gesture library should be freshened
    static final String KEY_STALE_GESTURES = "stale_gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setResult(RESULT_OK);
    }
}
