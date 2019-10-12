package net.wlgzs.purchase.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.base.BaseController;
import net.wlgzs.purchase.util.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author HYStar
 * @Date 2019/10/11 20:36
 */
@RestController
@RequestMapping("/login")
@Api(value = "登录管理接口", tags = "登录管理接口信息", description = "登录相关接口")
public class LoginController extends BaseController {

    /**
     * 去登录
     * @return
     */
    @ApiOperation(value = "去登录")
    @RequestMapping(value = "/toLogin")
    public ModelAndView toLogin() {
        return new ModelAndView("login");
    }

    /**
     * 登录
     * @param model model
     * @param request
     * @param userName 用户名或手机号
     * @param password 密码
     * @return
     */
    @ApiOperation(value = "登录(登录失败返回页面login)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名或手机号"),
            @ApiImplicitParam(name = "password",value = "密码")
    })
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public Result userLogin(Model model, HttpServletRequest request, String userName, String password) {
        Result result = iUserService.login(request, userName, password);
        return result;
    }

}
