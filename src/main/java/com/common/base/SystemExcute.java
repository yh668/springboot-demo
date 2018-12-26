package com.common.base;

import java.util.concurrent.Callable;

public class SystemExcute {
	public static void ExcuteServiceException(Action func) throws ServiceException, Exception {
		func.run();
	}

	public static <T> T ExcuteServiceException(Callable<T> func) throws ServiceException, Exception {
		return func.call();
	}
}
