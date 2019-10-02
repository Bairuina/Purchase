package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 添加一个用户
     * @param user 用户实体类
     * @return
     */
    @ApiOperation(value = "添加一个用户")
    public ModelAndView insertUser(User user){

        iUserService.insertUser(user);
        return new ModelAndView("");
    }


}
