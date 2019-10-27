package controller;

import entity.Userinfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceimpl.UserinfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class LoginAndRegisterController {
    @Autowired
    UserinfoServiceImpl usi;

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String check, HttpServletRequest req){
    HttpSession s=req.getSession();
    s.setAttribute("user",username);


    if(check.equals("true")){
        Userinfo ui=new Userinfo();
        ui.setUsername(username);
        ui.setPassword(password);
        HttpSession session=req.getSession();
        session.setAttribute("info",ui);

        Userinfo ui2=usi.selectByUsername(username);
        if(ui2!=null){
        if(DigestUtils.md5Hex(password.getBytes()).equals(ui2.getPassword())){
            return "yes";
        }else{
            return "no";//密码错误
        }

        }else{
            return "nothing";//用户不存在
        }

    }else{
        HttpSession session=req.getSession();
        session.removeAttribute("info");
        Userinfo ui2=usi.selectByUsername(username);
        if(ui2!= null){
            if(DigestUtils.md5Hex(password.getBytes()).equals(ui2.getPassword())){
                return "yes";
            }else{
                return "no";//密码错误
            }
        }else{
            return "nothing";//用户不存在
        }

    }

    }






    @RequestMapping("/selectByUsername")
    public String register(@RequestParam String username,@RequestParam String password,@RequestParam String email){
    Userinfo ui=usi.selectByUsername(username);
    if(ui!=null){
        return "no";
    }else{
        Userinfo u=new Userinfo();
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(date);

        u.setUsername(username);
        u.setPassword(DigestUtils.md5Hex(password.getBytes()));
        u.setEmail(email);
        u.setRegisterTime(time);

        if(usi.insertSelective(u)>0){
            return "yes";
        }else{
            return "error";
        }

    }

    }

}
