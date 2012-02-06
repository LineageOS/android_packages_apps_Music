package com.android.music;

import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {
    private SharedPreferences mPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        mPreferences = context.getSharedPreferences(MusicSettingsActivity.PREFERENCES_FILE,
                context.MODE_PRIVATE);
        if (mPreferences.getBoolean(MusicSettingsActivity.KEY_ENABLE_A2DP_AUTOPLAY, false)) {
            if(BluetoothHeadset.ACTION_STATE_CHANGED.equals(intent.getAction())){
                int btHeadsetState = intent.getIntExtra(BluetoothHeadset.EXTRA_STATE, BluetoothHeadset.RESULT_FAILURE);
                if(btHeadsetState == BluetoothHeadset.STATE_CONNECTED) {
                    Intent i = new Intent(context, MediaPlaybackService.class);
                    i.setAction(MediaPlaybackService.SERVICECMD);
                    i.putExtra(MediaPlaybackService.CMDNAME, MediaPlaybackService.CMDTOGGLEPAUSE);
                    context.startService(i);
                }
            }
        }
    }
}
