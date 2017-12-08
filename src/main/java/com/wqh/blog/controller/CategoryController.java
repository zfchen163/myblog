package com.wqh.blog.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.wqh.blog.annotation.Permission;
import com.wqh.blog.domain.Category;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.CategoryService;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author wanqh
 * @date 2017/12/08 11:25
 * @description: 分类控制器
 */
@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类，只有admin用户可以访问该方法
     * @param category
     * @param bindingResult
     * @return
     */
    @PostMapping("/category/insert")
    @Permission
    public ResultVo insert(@Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            categoryService.save(category);
        }catch (Exception exception){
            throw new BusinessException(ResultEnum.ARTICLE_IS_DELETE);
        }
        return ResultVOUtil.success();
    }
}