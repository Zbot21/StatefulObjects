package com.cmbellis.stateful_object.impl;

import com.cmbellis.stateful_object.api.StatefulObject;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by bellisc on 8/11/16.
 */
public abstract class AbstractStatefulObject<T extends AbstractStatefulObject, E extends Enumeration>
        implements StatefulObject<T, E> {

    private Map<E, Supplier<T>> stateTransitions = new HashMap<>();
    private T currentState;

    protected AbstractStatefulObject(T currentState) {
        this.currentState = currentState;
    }

    @Override
    public void injectEvent(E event) {
        currentState = Optional.ofNullable(stateTransitions.get(event)).map(Supplier::get).orElse(currentState);
    }

    @Override
    public void addStateTransition(E event, Supplier<T> stateSupplier) {
        stateTransitions.put(event, stateSupplier);
    }

    @Override
    public void addStateTransition(E event, T state) {
        stateTransitions.put(event, () -> state);
    }

    protected T getDelegate() {
        return currentState;
    }
}
