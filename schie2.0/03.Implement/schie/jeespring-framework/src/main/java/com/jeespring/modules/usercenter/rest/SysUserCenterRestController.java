/**
 * * Copyright &copy; 2015-2020 <a href="https://gitee.com/JeeHuangBingGui/JeeSpring">JeeSpring</a> All rights reserved..
 */
package com.jeespring.modules.usercenter.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeespring.common.persistence.Page;
import com.jeespring.common.web.AbstractBaseController;
import com.jeespring.common.utils.StringUtils;
import com.jeespring.modules.usercenter.entity.SysUserCenter;
import com.jeespring.modules.usercenter.service.SysUserCenterService;
import org.springframework.web.bind.annotation.RestController;
import com.jeespring.common.web.Result;
import com.jeespring.common.web.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心Controller
 * 
 * @author 黄炳桂
 * @version 2017-12-12
 */
@RestController
@RequestMapping(value = "/rest/usercenter/sysUserCenter")
@Api(value = "用户中心接口", description = "用户中心接口")
public class SysUserCenterRestController extends AbstractBaseController {

    @Autowired
    private SysUserCenterService sysUserCenterService;

    /**
     * 用户中心信息
     */
    @RequestMapping(value = { "get" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ApiOperation(value = "用户中心信息(Content-Type为text/html)", notes = "用户中心信息(Content-Type为text/html)")
    @ApiImplicitParam(name = "id", value = "用户中心id", required = false, dataType = "String", paramType = "query")
    public Result get(@RequestParam(required = false) String id) {
        SysUserCenter entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = sysUserCenterService.get(id);
        }
        if (entity == null) {
            entity = new SysUserCenter();
        }
        Result result = ResultFactory.getSuccessResult();
        result.setResultObject(entity);
        return result;
    }

    @RequestMapping(value = { "get/json" }, method = { RequestMethod.POST })
    @ApiOperation(value = "用户中心信息(Content-Type为application/json)", notes = "用户中心信息(Content-Type为application/json)")
    @ApiImplicitParam(name = "id", value = "用户中心id", required = false, dataType = "String", paramType = "body")
    public Result getJson(@RequestBody(required = false) String id) {
        SysUserCenter entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = sysUserCenterService.get(id);
        }
        if (entity == null) {
            entity = new SysUserCenter();
        }
        Result result = ResultFactory.getSuccessResult();
        result.setResultObject(entity);
        return result;
    }

    /**
     * 用户中心列表(不包含页信息)
     */
    // RequiresPermissions("usercenter:sysUserCenter:findList")
    @RequestMapping(value = { "findList" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ApiOperation(value = "用户中心列表(不包含页信息)(Content-Type为text/html)", notes = "用户中心列表(不包含页信息)(Content-Type为text/html)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "query")
    public Result findList(SysUserCenter sysUserCenter, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        List<SysUserCenter> list = sysUserCenterService.findList(sysUserCenter);
        Result result = ResultFactory.getSuccessResult();
        result.setResultObject(list);
        return result;
    }

    @RequestMapping(value = { "findList/json" }, method = { RequestMethod.POST })
    @ApiOperation(value = "用户中心列表(不包含页信息)(Content-Type为application/json)", notes = "用户中心列表(不包含页信息)(Content-Type为application/json)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "body")
    public Result findListJson(@RequestBody SysUserCenter sysUserCenter, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<SysUserCenter> list = sysUserCenterService.findList(sysUserCenter);
        Result result = ResultFactory.getSuccessResult();
        result.setResultObject(list);
        return result;
    }

    /**
     * 用户中心列表(包含页信息)
     */
    // RequiresPermissions("usercenter:sysUserCenter:list")
    @RequestMapping(value = { "list" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ApiOperation(value = "用户中心列表(包含页信息)(Content-Type为text/html)", notes = "用户中心列表(包含页信息)(Content-Type为text/html)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "query")
    public Result list(SysUserCenter sysUserCenter, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Page<SysUserCenter> page = sysUserCenterService.findPage(new Page<SysUserCenter>(request, response),
                sysUserCenter);
        Result result = ResultFactory.getSuccessResult();
        result.setResultObject(page);
        return result;
    }

    @RequestMapping(value = { "list/json" }, method = { RequestMethod.POST })
    @ApiOperation(value = "用户中心列表(包含页信息)(Content-Type为application/json)", notes = "用户中心列表(包含页信息)(Content-Type为application/json)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "body")
    public Result listJson(@RequestBody SysUserCenter sysUserCenter, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        Page<SysUserCenter> page = sysUserCenterService.findPage(new Page<SysUserCenter>(sysUserCenter.getPageNo(),
                sysUserCenter.getPageSize(), sysUserCenter.getOrderBy()), sysUserCenter);
        Result result = ResultFactory.getSuccessResult();
        result.setResultObject(page);
        return result;
    }

    /**
     * 用户中心获取列表第一条记录
     */
    // RequiresPermissions("usercenter:sysUserCenter:listFrist")
    @RequestMapping(value = { "listFrist" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ApiOperation(value = "用户中心获取列表第一条记录(Content-Type为text/html)", notes = "用户中心获取列表第一条记录(Content-Type为text/html)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "query")
    public Result listFrist(SysUserCenter sysUserCenter, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Page<SysUserCenter> page = sysUserCenterService.findPage(new Page<SysUserCenter>(request, response),
                sysUserCenter);
        Result result = ResultFactory.getSuccessResult();
        if (!page.getList().isEmpty()) {
            result.setResultObject(page.getList().get(0));
        } else {
            result = ResultFactory.getErrorResult("没有记录！");
        }
        return result;
    }

    @RequestMapping(value = { "listFrist/json" }, method = { RequestMethod.POST })
    @ApiOperation(value = "用户中心获取列表第一条记录(Content-Type为application/json)", notes = "用户中心获取列表第一条记录(Content-Type为application/json)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "body")
    public Result listFristJson(@RequestBody SysUserCenter sysUserCenter, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        Page<SysUserCenter> page = sysUserCenterService.findPage(new Page<SysUserCenter>(sysUserCenter.getPageNo(),
                sysUserCenter.getPageSize(), sysUserCenter.getOrderBy()), sysUserCenter);
        Result result = ResultFactory.getSuccessResult();
        if (!page.getList().isEmpty()) {
            result.setResultObject(page.getList().get(0));
        } else {
            result = ResultFactory.getErrorResult("没有记录！");
        }
        return result;
    }

    /**
     * 保存用户中心
     */
    // RequiresPermissions(value={"usercenter:sysUserCenter:add","usercenter:sysUserCenter:edit"},logical=Logical.OR)
    @RequestMapping(value = "save", method = { RequestMethod.POST, RequestMethod.GET })
    @ApiOperation(value = "保存用户中心(Content-Type为text/html)", notes = "保存用户中心(Content-Type为text/html)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "query")
    public Result save(SysUserCenter sysUserCenter, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, sysUserCenter)) {
            return ResultFactory.getErrorResult("数据验证失败");
        }
        sysUserCenterService.save(sysUserCenter);
        Result result = ResultFactory.getSuccessResult("保存用户中心成功");
        return result;
    }

    @RequestMapping(value = "save/json", method = { RequestMethod.POST })
    @ApiOperation(value = "保存用户中心(Content-Type为application/json)", notes = "保存用户中心(Content-Type为application/json)")
    @ApiImplicitParam(name = "sysUserCenter", value = "用户中心", dataType = "SysUserCenter", paramType = "body")
    public Result saveJson(@RequestBody SysUserCenter sysUserCenter, Model model,
            RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, sysUserCenter)) {
            return ResultFactory.getErrorResult("数据验证失败");
        }
        sysUserCenterService.save(sysUserCenter);
        Result result = ResultFactory.getSuccessResult("保存用户中心成功");
        return result;
    }

}
