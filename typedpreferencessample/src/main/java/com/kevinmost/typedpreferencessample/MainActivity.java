package com.kevinmost.typedpreferencessample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.kevinmost.typedpreferences.OnPreferenceChangedListener;
import com.kevinmost.typedpreferences.Preferences;
import com.kevinmost.typedpreferences.preference.TypedPreference;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initTypedAttributes();
  }

  private void initTypedAttributes() {
    final Preferences preferences = new Preferences(this);
    final TypedPreference<Integer> secondsElapsed = new TypedPreference.Builder<Integer>()
        .setKey("Seconds elapsed")
        .setDefaultValue(0)
        .setOnPreferenceChangedListener(new OnPreferenceChangedListener<Integer>() {
          @Override
          public void onPreferenceChanged(Integer newValue) {
            Toast.makeText(MainActivity.this,
                "10 seconds have passed, the value is now " + newValue,
                Toast.LENGTH_SHORT)
                .show();
          }
        })
        .setRegisterTo(preferences)
        .build();

    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      public void run() {
        secondsElapsed.set(secondsElapsed.get() + 10);
        secondsElapsed.getRegisteredPreferences().commitChanges();
        handler.postDelayed(this, 10000);
      }
    }, 10000);
  }
}
