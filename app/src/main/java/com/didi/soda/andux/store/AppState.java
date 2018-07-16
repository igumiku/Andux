package com.didi.soda.andux.store;

import com.didi.soda.andux.model.Todo;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.State;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijiang on 17/07/2018.
 */

public class AppState extends State {

  private VisivilityFilter visivilityFilter = VisivilityFilter.SHOW_ALL;

  private List<Todo> todos = new ArrayList<>();
}
