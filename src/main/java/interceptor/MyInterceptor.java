package interceptor;

import entity.WebInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import serviceimpl.WebInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    WebInfoServiceImpl wsi;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String now=sdf.format(date);
        WebInfo wi=wsi.selectByVisitTime(now);
        if (wi!=null) {
            wsi.update(wi);
        }else {
            //此时wi为空，需要创建一个对象并将数据放入wi对象中
            wi=new WebInfo();
            wi.setVisittime(now);
            wi.setVisittimes(1);
            //将wi放入wsi中
            wsi.insert(wi);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
