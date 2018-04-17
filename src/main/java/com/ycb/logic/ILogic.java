package com.ycb.logic;
/**
 * 公共接口 
 * @author chenghui
 *
 * @param <T>
 */
public interface ILogic<T> {
		
	public  ResultEnum  exec(T param) throws Exception;
}
