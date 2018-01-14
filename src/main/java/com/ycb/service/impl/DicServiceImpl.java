package com.ycb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.DicMapper;
import com.ycb.service.DicService;
import com.ycb.util.ReturnJson;
@Service
public class DicServiceImpl implements DicService {
	@Autowired
	private DicMapper  dicMapper;
	 
	@Override
	public com.ycb.entity.Dic queryDic() {
		return dicMapper.selectByPrimaryKey(1);
	}

	@Override
	public ReturnJson upDic(com.ycb.entity.Dic dic) {
		dic.setId(1);
		ReturnJson rj = new ReturnJson();
        int i =   dicMapper.updateByPrimaryKey(dic);
        if (i>0){
            rj.setMsg("修改成功!");
            rj.setSuccess(true);
        }else{
            rj.setSuccess(false);
            rj.setMsg("修改失败，请联系管理员!");
        }
        return rj;
	}

}
