package com.huskyshare.backend.web;

import com.huskyshare.backend.entity.Demand;
import com.huskyshare.backend.entity.Product;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.service.DemandService;
import com.huskyshare.backend.service.LoginTokenService;
import com.huskyshare.backend.service.ProductService;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import javax.xml.transform.Result;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
   @Autowired
   private UserService userService;

   @Autowired
   private LoginTokenService loginTokenService;

   @Autowired
   private TokenGenerator tokenGenerator;

   @Autowired
   private ProductService productService;

   @Autowired
   private DemandService demandService;

   /**
    * 访问网站的根目录
    */
   @RequestMapping("/")
   public ModelAndView index(HttpServletRequest request, Model model) {
      ModelAndView modelAndView = new ModelAndView("index");
      handleLoginState(request, model);
      return modelAndView;
   }

   /**
    * 访问注册界面
    * 如果用户已登录，则会自动被跳转至根目录
    */
   @RequestMapping(value = "/register", method = RequestMethod.GET)
   public ModelAndView registerForm(HttpServletRequest request, Model model) {
      ModelAndView modelAndView = new ModelAndView("register");
      if (handleLoginState(request, model) != null)
         modelAndView.setViewName("redirect:/");
      return modelAndView;
   }

   /**
    * 提交注册表单
    * 若注册成功则跳转至登录界面
    * 若注册失败则返回msg到前端
    */
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public ModelAndView registerPost(@ModelAttribute("user") User user, Model model,
                                    HttpServletRequest request) {

      // TODO server-side validate user input
      ModelAndView modelAndView = new ModelAndView("login");
      if (handleLoginState(request, model) != null) {
         modelAndView.setViewName("redirect:/");
         return modelAndView;
      }
      String msg = userService.register(user);
      if (msg.equals("SUCCESS")) {
         model.addAttribute("msg", "REGISTER_SUCCESS");
         model.addAttribute("email", user.getEmail());

         return modelAndView;
      }
      modelAndView.setViewName("register");
      model.addAttribute("msg", msg);

      return modelAndView;
   }

   /**
    * 访问登录界面
    * 若已有登录token则跳转至根目录
    */
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public ModelAndView loginForm(HttpServletRequest request, Model model) {
      ModelAndView modelAndView = new ModelAndView("login");
      if (handleLoginState(request, model) != null) {
         modelAndView.setViewName("redirect:/");
         return modelAndView;
      }

      return modelAndView;
   }

   /**
    * 提交登录表单
    * 若账户/密码不匹配则返回msg到前端
    * 若已有登录token则跳转至根目录
    */
   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public ModelAndView loginPost(@ModelAttribute("user") User user, Model model,
                                 HttpServletRequest request) {
      ModelAndView modelAndView = new ModelAndView("login");
      if (handleLoginState(request, model) != null) {
         modelAndView.setViewName("redirect:/");
         return modelAndView;
      }

      String msg = userService.login(user);
      if (msg.equals("SUCCESS")) {
         user = userService.findUserByEmail(user.getEmail());
         String userToken = loginTokenService.searchUser(user);
         if (userToken == null) {
            userToken = tokenGenerator.generateToken();
            loginTokenService.saveToken(userToken, user);
         }
         request.getSession().setAttribute("token", userToken);

         modelAndView.setViewName("redirect:/");
         return modelAndView;
      }
      model.addAttribute("loginMsg", msg);

      return modelAndView;
   }

   /**
    * 注销当前用户
    */
   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public ModelAndView logoutForm(HttpServletRequest request, Model model) {
      ModelAndView modelAndView = new ModelAndView("redirect:/");
      handleLoginState(request, model);
      if (loginTokenService.removeToken(request.getSession().getAttribute("token"))) {
         request.getSession().setAttribute("token", null);
         return modelAndView;
      }

      return modelAndView;
   }

   @RequestMapping(value = "/profile", method = RequestMethod.GET)
   public ModelAndView profileForm(HttpServletRequest request, Model model) {
      ModelAndView modelAndView = new ModelAndView("redirect:/login");
      handleLoginState(request, model);
      User user = handleLoginState(request, model);
      if (user != null) {
         List<Product> productList = productService.getAllProduct();
         List<Product> userProductList = new ArrayList<>();
         for(Product product:productList){
            if(product.getSeller().equals(user))
               userProductList.add(product);
         }
         List<Demand> demandList = demandService.getAllDemand();
         List<Demand> userDemandList = new ArrayList<>();
         for(Demand demand:demandList){
            if(demand.getSeller().equals(user))
               userDemandList.add(demand);
         }

         modelAndView.addObject("productList", userProductList);
         modelAndView.addObject("demandList", userDemandList);
         modelAndView.addObject("user", user);
         modelAndView.setViewName("profile");
         return modelAndView;
      }

      return modelAndView;
   }

   // 判断用户是否拥有login token，并返回登陆的用户对象，若无则返回null
   // 同时若用户拥有login token则会将用户名username返回至前端
   private User handleLoginState(HttpServletRequest request, Model model) {
      User user = loginTokenService.searchToken(request.getSession().getAttribute("token"));
      if (user != null) {
         model.addAttribute("username", user.getUsername());
      }
      return user;
   }

}
