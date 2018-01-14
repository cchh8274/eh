package com.ycb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.entity.Dic;
import com.ycb.service.DicService;
import com.ycb.util.ReturnJson;

@Controller
@RequestMapping("dic")
public class DicController {
	@Autowired
	private DicService dicService;

	// 跳转到区域页面
	@RequestMapping("toDic")
	public String toArea() {
		return "/dic/showDic";
	}

	// 分页展示
	@RequestMapping(value = "queryDic", method = RequestMethod.POST)
	@ResponseBody
	public Dic queryDic() {
		return dicService.queryDic();
	}

	// 添加
	@RequestMapping(value = "upDic", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson upDic(Dic dic) {
		return dicService.upDic(dic);
	}

	// 查询所有的区域
	@RequestMapping(value = "selectDic", method = RequestMethod.POST)
	@ResponseBody
	public Dic selectDic() {
		return dicService.queryDic();
	}

}
