/* ====================================================================================================
 * Project Name     [flux]
 * File Name        [com.web.flux.AnsyMetter.java]
 * Creation Date    [2021-05-27]
 *
 * Copyright© 2021 瑞声科技[AAC Technologies Holdings] All Rights Reserved
 *
 * ====================================================================================================
 * Change Log
 * ====================================================================================================
 * 2021-05-27     潘凌云      [Init] .
 * ==================================================================================================== */
package com.web.flux;

import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.Callable;

/**
 * <p></p>
 *
 * @author <a href="mailto:panlingyun@aactechnologies.com">潘凌云</a>
 * @version 1.0.0
 * @since jdk 1.8
 */
public class AnsyMetter {

    public static void main(String[] strs) throws InterruptedException {
        AsyncListenableTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        ListenableFuture<String> st = taskExecutor.submitListenable(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                throw new Exception("error");
                return "TEST CALL";
            }
        });
        st.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                System.out.println("call back::"+result);
            }
        });


        while (true){
            Thread.sleep(1000);
        }
    }

}

