package com.huskyshare.backend.restapi;

import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.service.ProductService;
import com.huskyshare.backend.utils.EmailHandler;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    EmailHandler emailHandler;

    /*@RequestMapping(value = "/rest/v1.0/product", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresRoles("USER")
    public ResponseBean postProduct(@RequestParam(value = "title")String title,
                                    @RequestParam(value = "descroption")String description,
                                    @RequestParam(value = "childTitle", required = false, defaultValue = "")String childTitle,
                                    @RequestParam(value = "price")int price,
                                    @RequestParam(value = "quantity")int quantity,
                                    @RequestParam(value = "delayed", required = false, defaultValue = "0") boolean delayed,
                                    @RequestParam(value = "delayedTime", required = false, defaultValue = "0") long delayedTime,
                                    )
                                    ){

    }*/
}
