package com.didi.soda.andux.reducer;

import com.didi.soda.andux.action.VisivilityAction;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.Reducer;
import com.didi.soda.jadux.State;

/**
 * Created by lijiang on 17/07/2018.
 */

public class VisivilityFilterReducer implements Reducer<VisivilityFilter> {

  @Override
  public VisivilityFilter reduce(VisivilityFilter state, Action action) {
    if(action instanceof VisivilityAction){
      return ((VisivilityAction) action).getPayload();
    }
    return state;
  }
}
