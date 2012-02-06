/*
 * Copyright (C) 2011 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.music;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class MusicSettingsActivity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    static final String KEY_ENABLE_FOCUS_LOSS_DUCKING = "enable_focus_loss_ducking";
    static final String KEY_DUCK_ATTENUATION_DB = "duck_attenuation_db";
    static final String KEY_ENABLE_GESTURES = "enable_gestures";
    static final String KEY_ENABLE_HAPTIC_FEEDBACK = "enable_haptic_feedback";
    static final String KEY_HAS_CUSTOM_GESTURES = "has_custom_gestures";
    //This key has the gesture entry name (E.g. PAUSE) appended to it before use
    static final String KEY_HAS_CUSTOM_GESTURE_XXX = "has_custom_gesture_";
    static final String KEY_WIDGET_TRANSPARENCY = "widget_transparency";

    static final String KEY_ENABLE_A2DP_AUTOPLAY = "enable_a2dp_autoplay";

    static final String DEFAULT_DUCK_ATTENUATION_DB = "8";

    static final String ACTION_ENABLE_GESTURES_CHANGED = "com.android.music.enablegestureschanged";
    static final String ACTION_GESTURES_CHANGED = "com.android.music.gestureschanged";

    static final String PREFERENCES_FILE = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(PREFERENCES_FILE);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_ENABLE_GESTURES)) {
            Intent intent = new Intent(ACTION_ENABLE_GESTURES_CHANGED);
            sendBroadcast(intent);
        } else if (key.equals(KEY_WIDGET_TRANSPARENCY)) {
            updateWidgetsForProvider(new ComponentName(this, MediaAppWidgetProvider4x1.class));
            updateWidgetsForProvider(new ComponentName(this, MediaAppWidgetProvider4x2.class));
        }
    }

    private void updateWidgetsForProvider(ComponentName provider) {
        final AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = manager.getAppWidgetIds(provider);
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            intent.setComponent(provider);
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
