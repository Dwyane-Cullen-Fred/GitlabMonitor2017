package org.Util;

import org.bean.ContributionAggregation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MapUtil {

    /**
     *
     * @param map1
     * @param map2
     * @param <K>
     */
    public static <K> Map<K, Integer> mapAdd(Map<K, Integer> map1, Map<K, Integer> map2) {
        Map<K, Integer> resultMap = new HashMap<>();
        Set<K> set1 = map1.keySet();
        Set<K> set2 = map2.keySet();
        for (K k : set1) {
            int v = 0;
            if (set2.contains(k)) {
                v = map1.get(k) + map2.get(k);
                set2.remove(k);
            } else {
                v = map1.get(k);
            }
            resultMap.put(k, v);
        }
        for (K k : set2) {
            resultMap.put(k, map2.get(k));
        }

        return resultMap;
    }

    /**
     *
     * @param maps : parameter should be pass as
     *             (all code, front code, end code, contribution from others, Contribution to other)
     * @return
     */
    public static Map<String, ContributionAggregation> mapMerge(Map<String, Integer> ...maps) {
        HashMap<String, ContributionAggregation> resultMap = new HashMap<>();

        if (maps.length != 5) return resultMap;

        Set<String> studentSet = new HashSet<>();
        for (Map<String, Integer> map : maps) {
            for (String key : map.keySet()) {
                if (!studentSet.contains(key)){
                    studentSet.add(key);
                }
            }
        }

        for (String student : studentSet) {
            int allCode = maps[0].get(student) == null ? 0 : maps[0].get(student);
            int frontCode = maps[1].get(student) == null ? 0 : maps[1].get(student);
            int endCode = maps[2].get(student) == null ? 0 : maps[2].get(student);
            int contributionFromOthers = maps[3].get(student) == null ? 0 : maps[3].get(student);
            int contributionToOthers = maps[4].get(student) == null ? 0 : maps[4].get(student);

            if (allCode == 0) continue;

            ContributionAggregation c = new ContributionAggregation(student, allCode, frontCode, endCode,
                    contributionFromOthers, contributionToOthers);
            resultMap.put(student, c);
        }

        return resultMap;
    }
}
