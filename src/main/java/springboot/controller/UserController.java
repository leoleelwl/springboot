package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springboot.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	public ModelAndView getUsers(){
		ModelAndView mav =  new ModelAndView();
		mav.addObject("userList",this.userService.getUsers());
		mav.setViewName("/user");
		return mav;
	}
}
