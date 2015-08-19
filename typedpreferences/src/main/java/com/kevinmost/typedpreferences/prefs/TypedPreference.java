package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;

public interface TypedPreference<T> {
  T get();
  void set(T value);

  final class Builder<T> {
    private String key;
    private OnPreferenceSetListener<T> onPreferenceSetListener;
    private T defaultValue;


    public Builder<T> setDefaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public Builder<T> setOnPreferenceSetListener(OnPreferenceSetListener<T> onPreferenceSetListener) {
      this.onPreferenceSetListener = onPreferenceSetListener;
      return this;
    }

    public Builder<T> setKey(String key) {
      this.key = key;
      return this;
    }

    public TypedPreference<T> build() {
      return PreferenceUtil.from(key, onPreferenceSetListener, defaultValue);
    }
  }
}
