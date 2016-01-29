package com.zp.commons.error;

public class BusinessException extends RuntimeException{

	public ErrorCode getError() {
		return error;
	}


	public void setError(ErrorCode error) {
		this.error = error;
	}


	private ErrorCode error;
	public BusinessException(ErrorCode i) {
		super();
		setError(i);
	}
	
	public BusinessException(ErrorCode e, Throwable t){
		super(e.getCodeMsg(), t);
		setError(e);
	}
	
	public BusinessException(ErrorCode e , String errorMsg , Throwable t){
		e = e.addMsg(errorMsg);
		setError(e);
	}

	
	public BusinessException(ErrorCode e, String errorMsg) {
		e = e.addMsg(errorMsg);
		setError(e);
	}


	private static final long serialVersionUID = -2996312694401870967L;
	
}