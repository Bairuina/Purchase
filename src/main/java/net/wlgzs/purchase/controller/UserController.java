package net.wlgzs.purchase.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.entity.User;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
@Api(value = "用户管理接口", tags = "用户管理接口信息", description = "用户相关接口")
public class UserController extends BaseController {

    /**
     * 添加一个用户
     *
     * @param user 用户实体类
     * @return
     */
    @ApiOperation(value = "添加一个用户")
    @ApiImplicitParam(name = "user", value = "用户实体类", required = true)
    @RequestMapping(value = "/insertUser", method = RequestMethod.PUT)
    public Result insertUser(User user) {
        if (iUserService.checkUser(user.getUserName(), user.getUserPhone())) {
            if (iUserService.insertUser(user)) {
                return new Result(ResultCode.SUCCESS, "添加成功！");
            }
            return new Result(ResultCode.FAIL, "添加失败！");
        }
        return new Result(ResultCode.FAIL, "用户名或手机号重复！");
    }

    /**
     * 查询所有用户(搜索userList)
     *
     * @param model
     * @param findName 用户名
     * @param current  当前页
     * @param limit    每页的条数
     * @return
     */
    @ApiOperation(value = "查询所有用户(搜索)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "findName", value = "用户名"),
            @ApiImplicitParam(paramType = "path", name = "current",
                    value = "当前页(跟在地址后面)", required = true),
            @ApiImplicitParam(name = "limit", value = "每页的条数(默认为8)")
    })
    @GetMapping("/userList/{current}")
    public ModelAndView userList(Model model,
                                 @RequestParam(value = "findName", defaultValue = "") String findName,
                                 @PathVariable("current") Integer current,
                                 @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Result result = iUserService.userList(findName, current, limit);
        List<User> userList = (List<User>) result.getData();

        //总页数
        model.addAttribute("TotalPages", result.getPages());
        //当前页数
        model.addAttribute("Number", result.getCurrent());
        model.addAttribute("userList", userList);
        model.addAttribute("findName", findName);
        return new ModelAndView("userList");
    }

    /**
     * 按id删除用户
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "按id删除用户")
    @ApiImplicitParam(paramType = "path", name = "userId",
            value = "用户id", required = true)
    @RequestMapping(value = "deleteUser/{userId}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable("userId") Integer userId) {
        Result result = iUserService.deleteUser(userId);
        return result;
    }

    public Result modifyUser(User user) {
        Result result = iUserService.modifyUser(user);
        return result;
    }

}
