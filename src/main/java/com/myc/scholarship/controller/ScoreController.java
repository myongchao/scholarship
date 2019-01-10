package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.Score;
import com.myc.scholarship.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-01-10
 */
@RestController
@RequestMapping("/score")
@Api(description = "分数")
public class ScoreController implements Serializable {

    @Autowired
    private ScoreService scoreService;

    @ApiOperation(value = "添加分数记录")
    @PostMapping(value = "/add")
    public Score add(@RequestBody Score score){
        scoreService.insert(score);
        return score;
    }

    @ApiOperation(value = "修改分数")
    @PostMapping(value = "/edit")
    public Boolean edit(@RequestBody Score score){
        return scoreService.updateById(score);
    }

    @ApiOperation(value = "查询分数列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public List<Score> list(){
        List<Score> scores = scoreService.selectList(new EntityWrapper<Score>());
        return scores;
    }

}

