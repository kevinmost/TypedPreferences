package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class IntegerPreference extends BaseTypedPreference<Integer> {

  public IntegerPreference(@NonNull String key, @Nullable OnPreferenceSetListener<Integer>
      onPreferenceSetListener, @NonNull Integer defaultValue) {
    super(key, onPreferenceSetListener, defaultValue);
  }

  @Override
  public Integer get(SharedPreferences preferences) {
    return preferences.getInt(key, defaultValue);
  }

  @Override
  public void set(SharedPreferences.Editor editor, Integer value) {
    super.set(editor, value);
    editor.putInt(key, value);
  }
}
