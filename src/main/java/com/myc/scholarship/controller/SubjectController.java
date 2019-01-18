package com.myc.scholarship.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-01-17
 */
@Controller
@RequestMapping("/subject")
@Api(value = "课目管理",description = "科目")
public class SubjectController implements Serializable {

}

