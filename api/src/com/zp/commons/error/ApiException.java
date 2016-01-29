package com.zp.commons.error;

public class ApiException extends RuntimeException {
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 3790487312698277234L;
	private ErrorCode code;

	public ErrorCode getCode() {
		return code;
	}
	
	public ApiException(ErrorCode code){
		super(code.getCodeMsg());
		this.code = code;
	}
}
