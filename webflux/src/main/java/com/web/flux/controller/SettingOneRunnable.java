/* ====================================================================================================
 * Project Name     [flux]
 * File Name        [com.web.flux.controller.SettingOneRunnable.java]
 * Creation Date    [2021-08-17]
 *
 * Copyright© 2021 瑞声科技[AAC Technologies Holdings] All Rights Reserved
 *
 * ====================================================================================================
 * Change Log
 * ====================================================================================================
 * 2021-08-17     潘凌云      [Init] .
 * ==================================================================================================== */
package com.web.flux.controller;

import com.web.flux.vo.MessageVo;
import reactor.core.publisher.MonoSink;

/**
 * <p></p>
 *
 * @author <a href="mailto:panlingyun@aactechnologies.com">潘凌云</a>
 * @version 1.0.0
 * @since jdk 1.8
 */
public class SettingOneRunnable implements Runnable{

    private MonoSink monoSink;
    private Long time;
    private MessageVo messageVo;

    public SettingOneRunnable(MonoSink monoSink, MessageVo messageVo, Long time){
        this.monoSink = monoSink;
        this.time = time;
        this.messageVo = messageVo;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monoSink.success(messageVo);
    }
}

