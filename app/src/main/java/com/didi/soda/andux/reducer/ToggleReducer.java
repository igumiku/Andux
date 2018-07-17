package com.didi.soda.andux.reducer;

import com.didi.soda.andux.action.VisivilityAction;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.Reducer;

/**
 * Created by lijiang on 17/07/2018.
 */

public class ToggleReducer implements Reducer<VisivilityFilter>{


    @Override
    public VisivilityFilter reduce(VisivilityFilter visivilityFilter, Action action) {
        if(action instanceof VisivilityAction){
            return ((VisivilityAction) action).getPayload();
        }
        return visivilityFilter;
    }
}
