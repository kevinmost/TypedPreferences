package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class BooleanPreference extends BaseTypedPreference<Boolean> {

  BooleanPreference(@NonNull String key, @Nullable OnPreferenceSetListener<Boolean>
      onPreferenceSetListener, @NonNull Boolean defaultValue) {
    super(key, onPreferenceSetListener, defaultValue);
  }

  @Override
  public Boolean get(SharedPreferences preferences) {
    return preferences.getBoolean(key, defaultValue);
  }

  @Override
  public void set(SharedPreferences.Editor editor, Boolean value) {
    super.set(editor, value);
    editor.putBoolean(key, value);
  }
}
