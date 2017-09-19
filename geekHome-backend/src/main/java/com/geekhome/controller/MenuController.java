package com.geekhome.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.Menu;
import com.geekhome.entity.dao.MenuDao;
import com.geekhome.entity.service.MenuService;

@RestController
@RequestMapping("menu")
public class MenuController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
    private MenuDao menuDao;

	@RequiresPermissions("menu:index")
	@RequestMapping(value = "/menuPage")
	public ModelAndView menuPage() {
		ArrayList<Menu> menuLists = new ArrayList<>();
		List<Menu> menus = menuService.getChildMenuList(menuLists, 0L);
		ModelAndView view = new ModelAndView("/view/system/menuPage");
		view.addObject("menus", menus);
		return view;
	}
	
	
	@RequiresPermissions("menu:allMenu")
	@RequestMapping(value = "/menuList")
	public void menuList(HttpServletRequest request) {
		ArrayList<Menu> menuLists = new ArrayList<>();
		List<Menu> menus = menuService.getChildMenuList(menuLists, 0L);
		request.setAttribute("menus", menus);
	}
	

	@RequiresPermissions("menu:sort")
	@RequestMapping(value = "/updateOrder", method = { RequestMethod.POST })
	public ExecuteResult<Boolean> updateOrder(Long id, Integer sort) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			menuDao.updateOrder(id,sort);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequiresPermissions("menu:save")
	@RequestMapping("saveMenu")
	public ExecuteResult<Boolean> saveMenu(@RequestBody Menu menu) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			menuService.saveMenu(menu);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequiresPermissions("menu:getMenu")
	@RequestMapping("getMenu")
	public ExecuteResult<Menu> getMenu(Long id) {
		final ExecuteResult<Menu> result = new ExecuteResult<>();
		try {
			Menu menu = menuDao.findOne(id);
			result.setData(menu);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}

}
