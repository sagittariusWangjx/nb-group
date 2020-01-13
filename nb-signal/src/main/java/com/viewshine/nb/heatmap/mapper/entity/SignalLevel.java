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

package com.viewshine.nb.heatmap.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.viewshine.nb.heatmap.mapper.constant.OperatorCons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author jingjing wang
 * @Date 2020/1/8
 */

@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignalLevel {
    /**
     * id;主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String lng;
    /**
     *
     */
    private String lat;
    /**
     *
     */
    private Integer signals;
    /**
     *
     */
    private String remake;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String province;
    /**
     *
     */
    private String city;
    /**
     *
     */
    private String district;
    /**
     *
     */
    private Integer snr;
    /**
     *
     */
    private Integer rspr;
    /**
     *
     */
    private Integer ecl;
    /**
     *
     */
    private Integer csq;

    /**
     * 运营商
     */
    private Integer operator;

    @TableField(exist = false)
    private String operatorShow;


    @TableField(exist = false)
    private String signalsShow;

    public String getOperatorShow() {
        return OperatorCons.ACCOUNT_EFFECTIVENESS_MAP.get(this.operator);
    }

    final static String[] SIGNAL_LEVEL = {"差", "中", "优"};

    final static Integer[] SIGNAL_LEVEL_NUMBS = {1, 49, 98};

    public Integer getSignals() {
        return SIGNAL_LEVEL_NUMBS[this.signals];
    }

    public String getSignalsShow() {
        return SIGNAL_LEVEL[this.signals];
    }

}
