package org.schic.modules.usercenter.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import org.schic.common.utils.DateUtils;
import org.schic.common.config.Global;
import org.schic.common.persistence.Page;
import org.schic.common.web.AbstractBaseController;
import org.schic.common.utils.StringUtils;
import org.schic.common.utils.excel.ExportExcel;
import org.schic.common.utils.excel.ImportExcel;
import org.schic.modules.usercenter.entity.SysUserCenter;
import org.schic.modules.usercenter.service.SysUserCenterService;
/**
 * 
 * @Description: 用户中心Controller
 * @author Caiwb
 * @date 2019年5月6日 下午2:46:46
 */
@Controller
@RequestMapping(value = "${adminPath}/usercenter/sysUserCenter")
public class SysUserCenterController extends AbstractBaseController {

	@Autowired
	private SysUserCenterService sysUserCenterService;

	@ModelAttribute
	public SysUserCenter get(@RequestParam(required = false) String id) {
		SysUserCenter entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = sysUserCenterService.get(id);
		}
		if (entity == null) {
			entity = new SysUserCenter();
		}
		return entity;
	}

	/**
	 * 用户中心列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(SysUserCenter sysUserCenter, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SysUserCenter> page = sysUserCenterService.findPage(
				new Page<SysUserCenter>(request, response), sysUserCenter);
		model.addAttribute("page", page);
		return "modules/usercenter/sysUserCenterList";
	}

	/**
	 * 用户中心列表页面
	 */
	@RequestMapping(value = {"select"})
	public String select(SysUserCenter sysUserCenter,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<SysUserCenter> page = sysUserCenterService.findPage(
				new Page<SysUserCenter>(request, response), sysUserCenter);
		model.addAttribute("page", page);
		return "modules/usercenter/sysUserCenterSelect";
	}

	/**
	 * 查看，增加，编辑用户中心表单页面
	 */
	@RequestMapping(value = "form")
	public String form(SysUserCenter sysUserCenter, Model model) {
		model.addAttribute("sysUserCenter", sysUserCenter);
		return "modules/usercenter/sysUserCenterForm";
	}

	/**
	 * 保存用户中心
	 */
	@RequestMapping(value = "save")
	public String save(SysUserCenter sysUserCenter, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysUserCenter)) {
			return form(sysUserCenter, model);
		}
		sysUserCenterService.save(sysUserCenter);
		addMessage(redirectAttributes, "保存用户中心成功");
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 删除用户中心
	 */
	@RequestMapping(value = "delete")
	public String delete(SysUserCenter sysUserCenter,
			RedirectAttributes redirectAttributes) {
		sysUserCenterService.delete(sysUserCenter);
		addMessage(redirectAttributes, "删除用户中心成功");
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 删除用户中心（逻辑删除，更新del_flag字段为1,在表包含字段del_flag时，可以调用此方法，将数据隐藏）
	 */
	@RequestMapping(value = "deleteByLogic")
	public String deleteByLogic(SysUserCenter sysUserCenter,
			RedirectAttributes redirectAttributes) {
		sysUserCenterService.deleteByLogic(sysUserCenter);
		addMessage(redirectAttributes, "逻辑删除用户中心成功");
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 批量删除用户中心
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			sysUserCenterService.delete(sysUserCenterService.get(id));
		}
		addMessage(redirectAttributes, "删除用户中心成功");
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 批量删除用户中心（逻辑删除，更新del_flag字段为1,在表包含字段del_flag时，可以调用此方法，将数据隐藏）
	 */
	@RequestMapping(value = "deleteAllByLogic")
	public String deleteAllByLogic(String ids,
			RedirectAttributes redirectAttributes) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			sysUserCenterService.deleteByLogic(sysUserCenterService.get(id));
		}
		addMessage(redirectAttributes, "删除用户中心成功");
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 导出excel文件
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(SysUserCenter sysUserCenter,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户中心" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<SysUserCenter> page = sysUserCenterService.findPage(
					new Page<SysUserCenter>(request, response, -1),
					sysUserCenter);
			new ExportExcel("用户中心", SysUserCenter.class)
					.setDataList(page.getList()).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户中心记录失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 导入Excel数据
	
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysUserCenter> list = ei.getDataList(SysUserCenter.class);
			for (SysUserCenter sysUserCenter : list) {
				sysUserCenterService.save(sysUserCenter);
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户中心记录");
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户中心失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

	/**
	 * 下载导入用户中心数据模板
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户中心数据导入模板.xlsx";
			List<SysUserCenter> list = Lists.newArrayList();
			new ExportExcel("用户中心数据", SysUserCenter.class, 1).setDataList(list)
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath()
				+ "/usercenter/sysUserCenter/?repage";
	}

}