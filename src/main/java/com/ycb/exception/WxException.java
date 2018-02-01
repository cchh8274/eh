package com.ycb.exception;

/**
 * 微信工具类的异常类
 * 
 * @author chenghui
 *
 */
public class WxException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -602107773923507591L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "WxException [message=" + message + "]";
	}

	public WxException(String message) {
		super();
		this.message = message;
	}

	private WxException() {
		super();
	}

	private volatile static WxException wxSingle;

	public static synchronized WxException getWxException() {
		if (wxSingle == null) {
			synchronized (WxException.class) {
				if(wxSingle ==null){
					wxSingle = new WxException();		
				}
			}
		}
		return wxSingle;
	}

}
