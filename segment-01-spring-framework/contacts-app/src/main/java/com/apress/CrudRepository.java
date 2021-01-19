package com.apress;

public interface CrudRepository<T>{

    Iterable<T> findAll();

}
