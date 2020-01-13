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

package com.viewshine.nb.heatmap.web.pv;

import com.viewshine.nb.heatmap.mapper.entity.SignalLevel;
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
public class SignalLevelPV {

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

    public SignalLevel truc(){
        return SignalLevel.builder().
                lat(this.lat).
                lng(this.lng).
                signals(this.signals).
                address(this.address).
                city(this.city).
                remake(this.remake).
                district(this.district).
                province(this.province).
                csq(this.csq).
                ecl(this.ecl).
                rspr(this.rspr).
                csq(this.csq).
                snr(this.snr).
                build();
    }

}
