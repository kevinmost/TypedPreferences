package com.kevinmost.typedpreferences;

import java.util.Collections;
import java.util.Set;

public class PreferenceUtil {

  @SuppressWarnings("unchecked")
  static <T> TypedPreference<T> from(String key,
                                     OnPreferenceChangedListener<T> listener,
                                     T defaultValue,
                                     Preferences preferencesToRegisterTo) {
    final Class<?> clazz = defaultValue.getClass();
    final TypedPreference<T> preference;
    // Oh god I'm sorry
    if (clazz.isAssignableFrom(String.class)) {
      preference = (TypedPreference<T>) new StringPreference(key, (OnPreferenceChangedListener<String>) listener, (String) defaultValue);
    } else if (clazz.isAssignableFrom(Boolean.class)) {
      preference = (TypedPreference<T>) new BooleanPreference(key, (OnPreferenceChangedListener<Boolean>) listener, (Boolean) defaultValue);
    } else if (clazz.isAssignableFrom(Integer.class)) {
      preference = (TypedPreference<T>) new IntegerPreference(key, (OnPreferenceChangedListener<Integer>) listener, (Integer) defaultValue);
    } else if (clazz.isAssignableFrom(Float.class)) {
      preference = (TypedPreference<T>) new FloatPreference(key, (OnPreferenceChangedListener<Float>) listener, (Float) defaultValue);
    } else if (clazz.isAssignableFrom(Long.class)) {
      preference = (TypedPreference<T>) new LongPreference(key, (OnPreferenceChangedListener<Long>) listener, (Long) defaultValue);
    } else if (clazz.isAssignableFrom(Set.class)) {
      final Set castValue = (Set) defaultValue;
      final Set<String> checkedDefaultValue;
      if (castValue.isEmpty()) {
        checkedDefaultValue = Collections.emptySet();
      } else {
        if (castValue.iterator().next() instanceof String) {
          checkedDefaultValue = castValue;
        } else {
          throw new IllegalArgumentException("defaultValue given was a set, but set didn't contain elements of type String");
        }
      }
      preference = (TypedPreference<T>) new StringSetPreference(key, (OnPreferenceChangedListener<Set<String>>) listener, checkedDefaultValue);
    } else {
      throw new IllegalArgumentException("defaultValue given was of type " + clazz.getName());
    }

    if (preferencesToRegisterTo != null) {
      preferencesToRegisterTo.registerPreference(preference);
    }

    return preference;
  }
}
