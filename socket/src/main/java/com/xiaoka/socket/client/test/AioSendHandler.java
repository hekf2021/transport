/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiaoka.socket.client.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AioSendHandler implements CompletionHandler
    <Integer,ByteBuffer>
{ 
    private AsynchronousSocketChannel socket; 
 
    public AioSendHandler(AsynchronousSocketChannel socket) { 
        this.socket = socket; 
    } 

    @Override
    public void completed(Integer i, ByteBuffer buf) {
        if (i > 0) {
            System.out.println("bbbbbbbbbbbbbbbb");
            socket.write(buf, buf, this); 
        } else if (i == -1) { 
            try { 
                System.out.println("对端断线:" + socket.getRemoteAddress().toString()); 
                buf = null; 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
        
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("cancelled");
        //exc.printStackTrace();
        if(exc instanceof IOException){

        }
    }
    
}
