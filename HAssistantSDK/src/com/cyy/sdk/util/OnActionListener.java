package com.cyy.sdk.util;

public interface OnActionListener {

	public void onActionSuccess(int actionId,ResponseParam ret);
	
	public void onActionFailed(int actionId, int httpStatus);
	
	public void onActionException(int actionId, String exception);
	
}
