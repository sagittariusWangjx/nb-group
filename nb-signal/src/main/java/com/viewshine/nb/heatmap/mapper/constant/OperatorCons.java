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

package com.viewshine.nb.heatmap.mapper.constant;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @Description
 * @Author jingjing wang
 * @Date 2020/1/8
 */
public class OperatorCons {

    public static final Integer CHINA_TELECOM = 1;
    public static final Integer CHINA_MOBILE = 2;

    public static Map<Integer, String> ACCOUNT_EFFECTIVENESS_MAP = new ImmutableMap.Builder<Integer, String>()
            .put(CHINA_TELECOM, "中国电信")
            .put(CHINA_MOBILE, "中国移动")
            .build();
}
