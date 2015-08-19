package com.kevinmost.typedpreferences.prefs;

public interface OnPreferenceSetListener<T> {
  void onPreferenceSet(T newValue);
}
