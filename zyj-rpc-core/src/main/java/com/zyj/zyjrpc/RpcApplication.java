package com.zyj.zyjrpc;

import com.zyj.zyjrpc.config.RpcConfig;
import com.zyj.zyjrpc.constant.RpcConstant;
import com.zyj.zyjrpc.utils.ConfigUtils;

public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig rpcConfig) {
        RpcApplication.rpcConfig = rpcConfig;
    }

    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class , RpcConstant.DEFAULT_CONFIG_PRCFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig() {
        if(rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if(rpcConfig == null) {
                    init();
                }
            }
        }
        return RpcApplication.rpcConfig;
    }
}