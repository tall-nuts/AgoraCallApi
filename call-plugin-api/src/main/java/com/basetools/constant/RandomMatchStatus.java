package com.basetools.constant;

import androidx.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 随机匹配状态
 */
@IntDef({
        RandomMatchStatus.MATCH_START,
        RandomMatchStatus.MATCH_SUCCESS,
        RandomMatchStatus.MATCH_FAILURE
})
@Retention(RetentionPolicy.SOURCE)
public @interface RandomMatchStatus {
    int MATCH_START = 0;
    int MATCH_SUCCESS = 1;
    int MATCH_FAILURE = -1;
}
