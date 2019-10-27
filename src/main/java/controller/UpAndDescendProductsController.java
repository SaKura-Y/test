package controller;

import entity.Productinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceimpl.ProductinfoServiceImpl;

import java.util.List;

@RestController
public class UpAndDescendProductsController {
    @Autowired
    ProductinfoServiceImpl psi;

    @RequestMapping("/showProducts")
    public List<Productinfo> showProducts(){
        return psi.selectAll();
    }

    @RequestMapping("/upProduct")
    public int upProduct(@RequestParam Integer pid){
        return psi.upProduct(pid);
    }

    @RequestMapping("/descendProduct")
    public int descendProduct(@RequestParam Integer pid){
        return psi.descendProduct(pid);
    }


}
