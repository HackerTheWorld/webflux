/* ====================================================================================================
 * Project Name     [rsocket]
 * File Name        [com.rsocket.vo.MessageVo.java]
 * Creation Date    [2021-07-14]
 *
 * Copyright© 2021 瑞声科技[AAC Technologies Holdings] All Rights Reserved
 *
 * ====================================================================================================
 * Change Log
 * ====================================================================================================
 * 2021-07-14     潘凌云      [Init] .
 * ==================================================================================================== */
package com.web.flux.vo;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author <a href="mailto:panlingyun@aactechnologies.com">潘凌云</a>
 * @version 1.0.0
 * @since jdk 1.8
 */
public class MessageVo implements Serializable {

    private static final long serialVersionUID = 3786010263098567267L;
    private String from;
    private String to;
    private String message;

    public String getFrom() {
        return this.from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

