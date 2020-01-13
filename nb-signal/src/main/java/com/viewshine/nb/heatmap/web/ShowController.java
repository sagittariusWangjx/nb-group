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
import com.viewshine.nb.heatmap.mapper.entity.SignalLevel;
import com.viewshine.nb.heatmap.service.SignalLevelService;
import com.viewshine.nb.heatmap.web.pv.GaodeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description 地图页面展现
 * @Author jingjing wang
 * @Date 2020/1/8
 */
@Slf4j
@Controller
public class ShowController {

    @Autowired
    private SignalLevelService signalLevelService;


    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", "hello thymeleaf");//添加一个值为"hello thymeleaf"的hello变量到视图

        List<SignalLevel> sl = signalLevelService.getSignalLevelByCity("杭州市");
        List<GaodeModel> heatmapData = Lists.newArrayList();
        sl.stream().forEach((signalLevel) -> {
            heatmapData.add(GaodeModel.builder().build().truc(signalLevel));
        });

        model.addAttribute("datatable", sl);
        model.addAttribute("heatmapData", heatmapData);
        return "heatmap";//在templates下寻找hello.html
    }

}
