package com.kevinmost.typedpreferences.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kevinmost.typedpreferences.OnPreferenceChangedListener;

final class BooleanPreference extends BaseTypedPreference<Boolean> {

  BooleanPreference(@NonNull String key, @Nullable OnPreferenceChangedListener<Boolean>
      onPreferenceChangedListener, @NonNull Boolean defaultValue) {
    super(key, onPreferenceChangedListener, defaultValue);
  }

  @Override
  protected Boolean get(SharedPreferences preferences) {
    return preferences.getBoolean(key, defaultValue);
  }

  @Override
  protected void set(SharedPreferences.Editor editor, Boolean value) {
    super.set(editor, value);
    editor.putBoolean(key, value);
  }
}
