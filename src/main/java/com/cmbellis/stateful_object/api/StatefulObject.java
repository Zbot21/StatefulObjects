package com.cmbellis.stateful_object.api;

import java.util.Enumeration;
import java.util.function.Supplier;

/**
 * Created by bellisc on 8/11/16.
 */
public interface StatefulObject<T extends StatefulObject, E extends Enumeration> {
    String getStateName();
    void injectEvent(E event);
    void addStateTransition(E event, Supplier<T> state);
    void addStateTransition(E event, T state);

}
