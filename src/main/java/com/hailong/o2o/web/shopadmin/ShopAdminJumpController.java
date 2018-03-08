package com.hailong.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminJumpController {
	@RequestMapping(value="/shopoperation",method={RequestMethod.GET})
	public String shopoperation(){
		return "shop/shopoperation";
	}

}
