package com.android.music;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class FolderSelector extends Activity
{
	EditText textEdit;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.folderpicker);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT);

        textEdit = (EditText)findViewById(R.id.edit_browse_folder);
        String s = MusicUtils.getStringPref(this, "favoritefolder", "/mnt/sdcard");
        textEdit.setText(s);
        
        ((Button) findViewById(R.id.set)).setOnClickListener(mListener);
        
        ((Button) findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    
    @Override
    public void onSaveInstanceState(Bundle outcicle) {
        outcicle.putString("favoritefolder", textEdit.getText().toString());
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }
    
    private View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
        	String s = textEdit.getText().toString();
        	MusicUtils.setStringPref(FolderSelector.this, "favoritefolder", s);
            setResult(RESULT_OK);
            finish();
        }
    };
}