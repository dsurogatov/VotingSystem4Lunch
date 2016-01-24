package org.dsu.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class ServiceLogAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogAspect.class);

	@Around("execution(* org.dsu.service..*.*(..))")
	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

		String serviceName = getServiceName(joinPoint);
		LOGGER.info("Beginning service: " + serviceName);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object retVal = null;
		Throwable ex = null;
		try {
			retVal = joinPoint.proceed();
		} catch (Throwable e) {
			ex = e;
		}

		stopWatch.stop();
		
		String logMessage = serviceName + " execution time: " + stopWatch.getTotalTimeMillis() + " ms.";
		if (ex != null) {
			LOGGER.info("Exception in service: " + logMessage + " Exception is: " + ex.getMessage());
			if (ex instanceof VotingSystemException) {
				throw ex;
			} else if (ex instanceof Exception)  {
				throw new VotingSystemException(ExceptionType.INTERNAL_ERROR, (Exception)ex);
			} else { // SEVERE EXCEPTION
				throw ex;
			}
		} else {
			LOGGER.info("Ending service: " + logMessage);
		}
		return retVal;
	}

	private String getServiceName(ProceedingJoinPoint joinPoint) {
		StringBuilder serviceName = new StringBuilder();
		serviceName.append(joinPoint.getTarget().getClass().getName());
		serviceName.append(".");
		serviceName.append(joinPoint.getSignature().getName());
		serviceName.append("(");

		// append args
		if (LOGGER.isDebugEnabled()) {
			Object[] args = joinPoint.getArgs();
			for (int i = 0; i < args.length; i++) {
				serviceName.append(args[i]).append(",");
			}
			if (args.length > 0) {
				serviceName.deleteCharAt(serviceName.length() - 1);
			}
		}
		serviceName.append(")");
		return serviceName.toString();
	}

}