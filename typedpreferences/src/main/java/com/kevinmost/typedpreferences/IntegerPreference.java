package com.kevinmost.typedpreferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class IntegerPreference extends BaseTypedPreference<Integer> {

  IntegerPreference(@NonNull String key, @Nullable OnPreferenceChangedListener<Integer>
      onPreferenceChangedListener, @NonNull Integer defaultValue) {
    super(key, onPreferenceChangedListener, defaultValue);
  }

  @Override
  protected Integer get(SharedPreferences preferences) {
    return preferences.getInt(key, defaultValue);
  }

  @Override
  protected void set(SharedPreferences.Editor editor, Integer value) {
    super.set(editor, value);
    editor.putInt(key, value);
  }
}
