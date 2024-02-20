package com.taskone.members.base;

import java.util.List;

public interface BaseService<T> {
	public abstract List<T> all();
	public abstract T item(Long id);
	public abstract T create(T data);
	public abstract void update(Long id, T data);
	public abstract void delete(Long id);
}
