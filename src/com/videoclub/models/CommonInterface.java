package com.videoclub.models;

import java.util.HashMap;

public interface CommonInterface<T> {

    public T constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException;
    
    public void updateSelf(T loaded) throws NoSuchFieldException, SecurityException;
}
