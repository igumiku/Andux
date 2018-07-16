package com.didi.soda.jadux.utils;

import java.util.Random;

/**
 * These are private action types reserved by Redux.
 * For any unknown actions, you must return the current state.
 * If the current state is undefined, you must return the initial state.
 * Do not reference these action types directly in your code.
 */
public enum ActionTypes {
    INIT,REPLACE,PROBE_UNKNOWN_ACTION
}
