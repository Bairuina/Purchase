package net.wlgzs.purchase.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 王耀兴
 * @since 2019-10-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parameters {
    private String cssm;   //键

    private String csz;    //值
}
