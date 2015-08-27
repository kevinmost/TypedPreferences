package com.kevinmost.typedpreferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class LongPreference extends BaseTypedPreference<Long> {
  LongPreference(@NonNull String key, @Nullable OnPreferenceChangedListener<Long>
      onPreferenceChangedListener, @NonNull Long defaultValue) {
    super(key, onPreferenceChangedListener, defaultValue);
  }

  @Override
  protected Long get(SharedPreferences preferences) {
    return preferences.getLong(key, defaultValue);
  }

  @Override
  protected void set(SharedPreferences.Editor editor, Long value) {
    super.set(editor, value);
    editor.putLong(key, value);
  }
}
