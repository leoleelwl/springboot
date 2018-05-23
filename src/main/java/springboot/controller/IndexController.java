package springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springboot.entity.User;



@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping(value={"","/index"})
	public ModelAndView doIndex(){
		ModelAndView mav =  new ModelAndView();
		mav.addObject("username","你好，Freemarker！");
		mav.addObject("userList",this.prepareUserList());
		mav.setViewName("/index");
		return mav;
	}
	
	
	
    /**
     * 准备用户数据
     * @return 用户数据
     */
    private List<User> prepareUserList(){
        List<User> list = new ArrayList<User>();
        for(int i=0;i<5;i++){
            User item = new User();
            item.setName("用户"+i);
            item.setAge(i+10+"");
            list.add(item);
        }
        return list;
    }
}
