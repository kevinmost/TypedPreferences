package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class FloatPreference extends BaseTypedPreference<Float> {
  public FloatPreference(@NonNull String key, @Nullable OnPreferenceSetListener<Float>
      onPreferenceSetListener, @NonNull Float defaultValue) {
    super(key, onPreferenceSetListener, defaultValue);
  }

  @Override
  public Float get(SharedPreferences preferences) {
    return preferences.getFloat(key, defaultValue);
  }

  @Override
  public void set(SharedPreferences.Editor editor, Float value) {
    super.set(editor, value);
    editor.putFloat(key, value);
  }
}
