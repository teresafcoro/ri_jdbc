package uo.ri.cws.application.business.util;

import uo.ri.cws.application.business.BusinessException;

public interface Command<T> {

	T execute() throws BusinessException;
}
