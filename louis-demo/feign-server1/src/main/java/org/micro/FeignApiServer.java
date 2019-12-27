package org.micro;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.micro.feign.FeignApi;

import java.util.List;
import java.util.Map;

/**
 * @author John·Louis
 * @date create in 2019/12/21
 * description:
 */
public class FeignApiServer implements FeignApi {
    private static Map<String, List<String>> map;
    static {
        List<String> list1 = Lists.newArrayList("a", "b", "c" );
        List<String> list2 = Lists.newArrayList("d", "e", "f", "g");
        List<String> list3 = Lists.newArrayList( "h", "i", "j", "k");
        List<String> list4 = Lists.newArrayList( "l", "m", "n", "o");
       map= Maps.newConcurrentMap();
        map.put("a", list1);
        map.put("b", list2);
        map.put("c", list3);
        map.put("d", list4);

    }

    @Override
    public List<String> findByName(String name) {
        return map.get(name);
    }

    @Override
    public String findOne(String name) {
        return map.get(name).stream().findAny().orElse("数据不存在");
    }
}
