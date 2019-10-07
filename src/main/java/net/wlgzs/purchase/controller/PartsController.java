package net.wlgzs.purchase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.service.IPartsService;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@RestController
@RequestMapping("/parts")
@Slf4j
public class PartsController extends BaseController {
    @Resource
    private IPartsService partsService;

    //根据品目编号查询配件
    @RequestMapping(value = "/partsListBypmbh/{pmbh}")
    public ModelAndView partsListBypmbh(Model model, @PathVariable("pmbh") String pmbh){
        QueryWrapper<Parts> queryWrapper=new QueryWrapper<Parts>();
        queryWrapper.eq("pmbh",pmbh);
        List<Parts> list= partsService.list(queryWrapper);
        model.addAttribute("Partslist",list);
        return new ModelAndView();
    }


}
