package com.didi.soda.andux.reducer;

import com.didi.soda.andux.action.Actions;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.Reducer;

/**
 * Created by lijiang on 17/07/2018.
 */

public class VisivilityFilterReducer implements Reducer<VisivilityFilter> {

  @Override
  public VisivilityFilter reduce(VisivilityFilter state, Action action) {
    if (Actions.TOGGLE_TODO.equals(action.getType())) {
      return ((VisivilityFilter) action.getPayload());
    }
    return state;
  }
}
