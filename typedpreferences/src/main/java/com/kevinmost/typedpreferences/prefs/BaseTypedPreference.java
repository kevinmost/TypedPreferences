package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

abstract class BaseTypedPreference<T> implements TypedPreference<T> {

  @NonNull
  protected final String key;

  @Nullable
  protected final OnPreferenceSetListener<T> onPreferenceSetListener;

  @NonNull
  protected final T defaultValue;

  public BaseTypedPreference(@NonNull String key,
                             @Nullable OnPreferenceSetListener<T> onPreferenceSetListener,
                             @NonNull T defaultValue) {
    this.key = key;
    this.onPreferenceSetListener = onPreferenceSetListener;
    this.defaultValue = defaultValue;
  }
}
