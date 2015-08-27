package com.kevinmost.typedpreferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class StringPreference extends BaseTypedPreference<String> {
  StringPreference(@NonNull String key, @Nullable OnPreferenceChangedListener<String>
      onPreferenceChangedListener, @NonNull String defaultValue) {
    super(key, onPreferenceChangedListener, defaultValue);
  }

  @Override
  protected String get(SharedPreferences preferences) {
    return preferences.getString(key, defaultValue);
  }

  @Override
  protected void set(SharedPreferences.Editor editor, String value) {
    super.set(editor, value);
    editor.putString(key, value);
  }
}
