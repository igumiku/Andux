package com.didi.soda.jadux;


import com.didi.soda.jadux.function.MiddlewareInnerFunction;

import io.reactivex.functions.BiFunction;

public class ComposeMiddleware {
    public static BiFunction<MiddlewareInnerFunction, MiddlewareInnerFunction, MiddlewareInnerFunction> compose =
            (f, g) -> (MiddlewareInnerFunction) source -> f.apply(g.apply(source));
}
