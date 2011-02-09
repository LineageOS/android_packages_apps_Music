package com.android.music;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MusicSettingsActivity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    static final String KEY_ENABLE_GESTURES = "enable_gestures";
    static final String KEY_ENABLE_HAPTIC_FEEDBACK = "enable_haptic_feedback";
    static final String KEY_HAS_CUSTOM_GESTURES = "has_custom_gestures";
    //This key has the gesture entry name (E.g. PAUSE) appended to it before use
    static final String KEY_HAS_CUSTOM_GESTURE_XXX = "has_custom_gesture_";

    static final String ACTION_ENABLE_GESTURES_CHANGED = "com.android.music.enablegestureschanged";
    static final String ACTION_GESTURES_CHANGED = "com.android.music.gestureschanged";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_ENABLE_GESTURES)) {
            Intent intent = new Intent(ACTION_ENABLE_GESTURES_CHANGED);
            sendBroadcast(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
