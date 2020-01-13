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

package com.viewshine.nb.heatmap.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.viewshine.nb.heatmap.mapper.SignalLevelMapper;
import com.viewshine.nb.heatmap.mapper.entity.SignalLevel;
import com.viewshine.nb.heatmap.service.SignalLevelService;
import com.viewshine.nb.heatmap.web.pv.SignalLevelPV;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author jingjing wang
 * @Date 2020/1/8
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignalLevelServiceImpl implements SignalLevelService {

    final SignalLevelMapper signalLevelMapper;

    @Override
    public int insertSignalLevel(SignalLevelPV pv, Integer operator) {
        int result = -1;
        try {
            SignalLevel signallevel = pv.truc();
            signallevel.setOperator(operator);
            int temp = consideration(
                    signallevel.getSnr(), signallevel.getRspr());
            signallevel.setSignals(temp);
            result = signalLevelMapper.insert(signallevel);
        } catch (Exception e) {
            log.error(">>>>>>>>>> insertSignalLevel err");
        }
        return result;

    }

    @Override
    public List<SignalLevel> getSignalLevelByCity(String city) {
        return signalLevelMapper.selectList(Wrappers.<SignalLevel>lambdaQuery().
                ge(SignalLevel::getCity, city));
    }

    /**
     * 根据snr 和 rspr 计算出信号等级。
     *
     * 信号等级
     *
     * 差：   SINR≤-30,    RSRP≤-1100
     * 良：-30<SINR<70,    -1100<RSRP<-800
     * 优：    SINR≥70,    RSRP≥-800
     *
     *   两个值按照差的那个来定性
     *
     *
     * @param snr
     * @param rspr
     * @return
     */
    private static int consideration(int snr, int rspr) {
        int sinrs = 0;
        int rsrps = 0;

        if (snr >= 70) {
            sinrs = sinrs | 4;
        } else if (snr > -30) {
            sinrs = sinrs | 2;
        } else {
            sinrs = sinrs | 1;
        }

        if (rspr >= -800) {
            rsrps = rsrps | 4;
        } else if (snr > -1100) {
            rsrps = rsrps | 2;
        } else {
            rsrps = rsrps | 1;
        }

        int temp = (rsrps | sinrs);

        int result = 0;
        for (int i = 0; i < 3; i++) {
            if (((temp >> i) & 1) > 0) {
                result = Integer.valueOf(i);
                break;
            }
        }

        return result;
    }
}
