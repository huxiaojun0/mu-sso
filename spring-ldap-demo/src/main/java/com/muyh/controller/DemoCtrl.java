package com.muyh.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class DemoCtrl {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String dologin(HttpServletRequest request, Model model){
        String msg = "";
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userName);
        System.out.println(password);
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {

                return "redirect:/user/student";
            } else {
                return "login";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
            System.out.println(msg);
        }
        return "login";
    }
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }

    @RequestMapping(value = "/user/admin", method = RequestMethod.GET)
    public String admin(){
        return "success";
    }

    @RequestMapping(value = "/user/student", method = RequestMethod.GET)
    public String student(){
        return "success";
    }

    @RequestMapping(value = "/user/teacher", method = RequestMethod.GET)
    public String teacher(){
        return "success";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized(){
        return "unauthorized";
    }

}
