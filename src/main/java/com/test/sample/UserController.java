package com.test.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	 @GetMapping("/user")
	    public String showHomePage() {
	       return "homePage";
	    }
	 
	 @PostMapping("/user/splitSDK")
	    public String loadSplitSDK( Model model) {
		 model.addAttribute("message", "");
	       return "splitClient";
	    }
	 
	 @PostMapping("/user/validate")
	    public String validateRequest(@RequestParam(name = "key") String username,@RequestParam(name = "token") String token ,Model model) {
		 
			if(jwtTokenUtil.validateToken(token, username))
			{
		        String message = "Generated Key Verified  successfully ! </br> Key="+username;
		        model.addAttribute("message", message);
		       
		    }
			else
			{
				String message = "Generated Key NOT Verified  successfully !";
		        model.addAttribute("message", message);
			}
			 
			 return "splitClient";
	 }
	 
	 @GetMapping("/user/generate")
	 @ResponseBody
	 public String createAuthenticationToken(@RequestParam(name = "key") String username) throws Exception {
			final String token = jwtTokenUtil.generateToken(username);
			return token;
		}
}
