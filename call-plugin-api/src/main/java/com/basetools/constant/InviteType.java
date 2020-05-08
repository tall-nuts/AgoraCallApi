package com.basetools.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 邀请类型
 * @author gaopengfei
 */
@IntDef({
        InviteType.SEND,
        InviteType.RECEIVED_INVITE,
        InviteType.RECEIVED_SYS_INVITE
})
@Retention(RetentionPolicy.SOURCE)
public @interface InviteType {
    /** 发起邀请 */
   int SEND = 0;
    /** 收到邀请 */
   int RECEIVED_INVITE = 1;
    /** 收到系统策略邀请 */
   int RECEIVED_SYS_INVITE = 2;
}
