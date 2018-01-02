package com.ycb.model;
/**
 * 
 * @author chenghui
 *
 */
public class ResponseBean {
		private  String code;
		
		private  String message;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public ResponseBean(String code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		public ResponseBean() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "ResponseBean [code=" + code + ", message=" + message + "]";
		}
		
}
