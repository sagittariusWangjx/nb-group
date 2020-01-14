package com.viewshine.omp.utils;

import java.util.Map;

/**
 * Created by shixj on 2017/9/7.
 */
public class MapUtil {
    public static <K,V> V get(Map<K,V> map,K k){
          if(map.containsKey(k))
              return map.get(k);
          else return null;
    }
}
