package com.common.base;
 

@FunctionalInterface
public interface Action {
	public abstract void run() throws ServiceException, Exception;
}