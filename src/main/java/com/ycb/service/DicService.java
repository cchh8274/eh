package com.ycb.service;

import com.ycb.entity.Dic;
import com.ycb.util.ReturnJson;

public interface DicService {

	Dic queryDic();

	ReturnJson upDic(Dic dic);


}
