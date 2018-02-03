package com.retail.productdetails.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect class to support logging.
 * 
 * @author Joyappan
 */
@Component
@Aspect
public class ProductLoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductLoggingAspect.class);

	/**
	 * Log the method name and parameters while entering and exiting the method
	 * 
	 * @param ProceedingJoinPoint
	 * @return Object
	 */
	@Around("execution(* com.retail.productdetails..*(..))")
	public Object aroundMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
		if (LOGGER.isInfoEnabled()) {

			StringBuilder logMsg = new StringBuilder();
			logMsg.append("Entered: ").append(joinPoint.getSignature());

			logMsg.append(" params: ");
			Object[] args = joinPoint.getArgs();

			if (args != null) {

				int count = 0;
				for (Object arg : args) {
					count++;
					logMsg.append(arg);
					if (count < args.length) {
						logMsg.append(",");
					}
				}
			}
			LOGGER.info(logMsg.toString());
		}

		Object object = (Object) joinPoint.proceed(joinPoint.getArgs());
		return object;
	}

}