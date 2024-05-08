package com.favouriteless.enchanted.common.init.registry.util;

import java.util.function.Supplier;

public class RegistryDescriptor<T> {

    private final String name;
    private final Supplier<T> supplier;

    protected RegistryDescriptor(String name, Supplier<T> supplier) {
        this.name = name;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public Supplier<T> getSupplier() {
        return supplier;
    }

}