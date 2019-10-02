package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.entity.User;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
     *
     * @param user 用户实体类
     * @return
     */
    @ApiOperation(value = "添加一个用户")
    @ApiImplicitParam(name = "user", value = "用户实体类", required = true)
    @RequestMapping(value = "/insertUser")
    public Result insertUser(User user) {
        if (iUserService.checkUser(user.getUserName(), user.getUserPhone())) {
            if (iUserService.insertUser(user)) {
                return new Result(ResultCode.SUCCESS, "添加成功！");
            }
            return new Result(ResultCode.FAIL, "添加失败！");
        }
        return new Result(ResultCode.FAIL, "用户名或手机号重复！");
    }

    //后台查询所有用户(搜索)
    @GetMapping("/UserList/{current}")
    public ModelAndView UserList(Model model,
                                 @RequestParam(value = "findName", defaultValue = "") String findName,
                                 @PathVariable("current") Integer current,
                                 @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Result result = iUserService.UserList(findName, current, limit);
        List<User> userList = (List<User>) result.getData();

        model.addAttribute("TotalPages", result.getPages());//总页数
        model.addAttribute("Number", result.getCurrent());//当前页数
        model.addAttribute("userList", userList);
        model.addAttribute("findName", findName);
        return new ModelAndView("admin/adminUser");
    }

}
