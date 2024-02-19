package com.taskone.members.base;

import java.util.Collection;

public interface BaseService<T> {
	public abstract Collection<T> all();
	public abstract T item(Long id);
	public abstract T create(T data);
	public abstract void update(Long id, T data);
	public abstract void delete(Long id);
}
