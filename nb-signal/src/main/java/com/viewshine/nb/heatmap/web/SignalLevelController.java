/**
 * Copyright (c) 2018-2028, Jingjing Wang (wjj821269@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viewshine.nb.heatmap.web;

import com.google.common.collect.Lists;
import com.viewshine.nb.heatmap.mapper.constant.OperatorCons;
import com.viewshine.nb.heatmap.mapper.entity.SignalLevel;
import com.viewshine.nb.heatmap.service.SignalLevelService;
import com.viewshine.nb.heatmap.web.model.RespModel;
import com.viewshine.nb.heatmap.web.pv.GaodeModel;
import com.viewshine.nb.heatmap.web.pv.SignalLevelPV;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author jingjing wang
 * @Date 2020/1/8
 */
@RestController
@Slf4j
public class SignalLevelController {

    @Autowired
    private SignalLevelService signalLevelService;

    @ApiOperation(value = "增加电信信息检测信息", notes = "通过网络接口进行上传")
    @PostMapping("/addChinaTelecomSignalInfo")
    public RespModel<Integer> addChinaTelecomSignalInfo(@RequestBody SignalLevelPV slevel) {
        Integer result = signalLevelService.insertSignalLevel(slevel, OperatorCons.CHINA_TELECOM);
        return RespModel.<Integer>builder()
                .errCode(result > 0 ? 202 : 500)
                .msg(result > 0 ? "成功" : "失败")
                .t(result).build();
    }

    @ApiOperation(value = "增加移动信息检测信息", notes = "通过网络接口进行上传")
    @PostMapping("/addChinaMobileSignalInfo")
    public RespModel<Integer> addChinaMobileSignalInfo(@RequestBody SignalLevelPV slevel) {
        Integer result = signalLevelService.insertSignalLevel(slevel, OperatorCons.CHINA_MOBILE);
        return RespModel.<Integer>builder()
                .errCode(result > 0 ? 202 : 500)
                .msg(result > 0 ? "成功" : "失败")
                .t(result).build();
    }

    @ApiOperation(value = "获取城市的信号信息", notes = "用于热力图展示")
    @GetMapping("/getSignalLevelInfoByCity/{city}")
    public List<GaodeModel> getSignalLevelInfoByCity(String city) {
        List<SignalLevel> sl = signalLevelService.getSignalLevelByCity(city);
        List<GaodeModel> gaodeModels = Lists.newArrayList();
        sl.stream().forEach((signalLevel) -> {
            gaodeModels.add(GaodeModel.builder().build().truc(signalLevel));
        });
        return gaodeModels;
    }

}
