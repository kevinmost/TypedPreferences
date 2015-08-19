package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class StringPreference extends BaseTypedPreference<String> {
  public StringPreference(@NonNull String key, @Nullable OnPreferenceSetListener<String>
      onPreferenceSetListener, @NonNull String defaultValue) {
    super(key, onPreferenceSetListener, defaultValue);
  }

  @Override
  public String get(SharedPreferences preferences) {
    return preferences.getString(key, defaultValue);
  }

  @Override
  public void set(SharedPreferences.Editor editor, String value) {
    super.set(editor, value);
    editor.putString(key, value);
  }
}
