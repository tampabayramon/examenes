package org.kp4tr.exams.delegate;

import org.kp4tr.exams.layers.IServiceRequest;
import org.kp4tr.exams.layers.IServiceResponse;

public interface IDelegate {

	public IServiceResponse processRequest(IServiceRequest request)
			throws Exception;
}
