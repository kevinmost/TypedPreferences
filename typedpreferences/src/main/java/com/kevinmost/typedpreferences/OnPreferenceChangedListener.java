package com.kevinmost.typedpreferences;

public interface OnPreferenceChangedListener<T> {
  void onPreferenceChanged(T newValue);
}
