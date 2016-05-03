package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/joinform")
	public String joinform() {
		return "/user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVO vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel()); // Map return

			return "/user/joinform"; // forwarding
		}

		userService.join(vo);
		model.addAttribute("user_id", vo.getUser_id());
//		return "/user/joinsuccess";
		return "redirect:/blog/create";
	}

	@RequestMapping("/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam(value = "user_id", required = true, defaultValue = "") String user_id) {

		UserVO vo = userService.getUserId(user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo == null);

		return map;
	}
}
