package org.micro.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author louis
 * <p>
 * Date: 2020/1/2
 * Description:
 */
@Data
@AllArgsConstructor
@Builder
public class KeyValue<K, V> {

    private K key;

    private V value;

}
