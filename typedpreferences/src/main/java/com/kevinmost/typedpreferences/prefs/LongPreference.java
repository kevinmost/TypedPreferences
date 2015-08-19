package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class LongPreference extends BaseTypedPreference<Long> {
  LongPreference(@NonNull String key, @Nullable OnPreferenceSetListener<Long>
      onPreferenceSetListener, @NonNull Long defaultValue) {
    super(key, onPreferenceSetListener, defaultValue);
  }

  @Override
  public Long get(SharedPreferences preferences) {
    return preferences.getLong(key, defaultValue);
  }

  @Override
  public void set(SharedPreferences.Editor editor, Long value) {
    super.set(editor, value);
    editor.putLong(key, value);
  }
}
