package com.dwzq.zhyy.util;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.dwsc.duap.internal.pojo.AccKh;
import com.dwsc.duap.internal.pojo.AccModule;
import com.dwsc.duap.internal.pojo.AccSvc;
import com.dwsc.duap.internal.pojo.AccTerminal;
import com.dwsc.duap.internal.service.RCGatewayService;
import com.dwsc.duap.internal.service.UserDataService;

import java.util.*;

import com.dwsc.duap.internal.service.UserNotifyService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用接口的客户端初始化
 */
public class UserDataClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserDataClientUtil.class);
	public static UserDataService.Iface userDataService = null;
    public static UserNotifyService.Iface userNotifyService = null;
	public static RCGatewayService.Iface rCGatewayService = null;
    public static ReferenceConfig<UserNotifyService.Iface> userNotifyServiceReference = null;
    public static ReferenceConfig<RCGatewayService.Iface> rCGatewayServiceReference = null;
    /**
     * 初始化UserDataService接口
     */
    public static void userDataServiceInitConfig() {
		logger.info("开始初始化UserDataService");
		try {
			ApplicationConfig application = new ApplicationConfig();
			application.setName("user-acsctl-consumer");
			RegistryConfig registry = new RegistryConfig();
			registry.setProtocol("zookeeper");
//			registry.setAddress(ApolloUtils.ZOOKEEPER_URL);
			registry.setAddress("192.250.107.148:2181,192.250.107.149:2181");
			ReferenceConfig<UserDataService.Iface> reference = new ReferenceConfig();
			reference.setApplication(application);
			reference.setRegistry(registry);
			reference.setInterface(UserDataService.Iface.class);
			userDataService = (UserDataService.Iface)reference.get();
		} catch (Exception e) {
			logger.error("UserDataService接口初始化异常："+e.getMessage());
			e.printStackTrace();
		}
	}

    /**
     * 初始化UserNotifyService接口
     */
    public static void userNotifyServiceInitConfig() {
		logger.info("开始初始化UserNotifyService.");
		try {
			ApplicationConfig application = new ApplicationConfig();
			application.setName("user-acsctl-consumer");
			RegistryConfig registry = new RegistryConfig();
			registry.setProtocol("zookeeper");
			registry.setAddress(ApolloUtils.ZOOKEEPER_URL);
            userNotifyServiceReference = new ReferenceConfig();
            userNotifyServiceReference.setApplication(application);
            userNotifyServiceReference.setRegistry(registry);
            userNotifyServiceReference.setInterface(UserNotifyService.Iface.class);
            userNotifyServiceRetryGet(userNotifyServiceReference);
		} catch (Exception e) {
			logger.error("UserNotifyService接口初始化异常："+e.getMessage());
			e.printStackTrace();
		}
    }
    public static void userNotifyServiceRetryGet(ReferenceConfig<UserNotifyService.Iface> reference) {
        try {
            userNotifyService = (UserNotifyService.Iface)reference.get();
        } catch (Exception e) {
            logger.error("UserNotifyService接口初始化异常："+e.getMessage());
            e.printStackTrace();
        }
    }
	/**
	 * 风控相关接口类初始化
	 */
	public static void rCGatewayServiceInitConfig() {
		logger.info("开始初始化RCGatewayService.");
		try {
			ApplicationConfig application = new ApplicationConfig();
			application.setName("user-acsctl-consumer");
			RegistryConfig registry = new RegistryConfig();
			registry.setProtocol("zookeeper");
			registry.setAddress(ApolloUtils.ZOOKEEPER_URL);
            rCGatewayServiceReference = new ReferenceConfig();
            rCGatewayServiceReference.setApplication(application);
            rCGatewayServiceReference.setRegistry(registry);
            rCGatewayServiceReference.setInterface(RCGatewayService.Iface.class);
//			rCGatewayServiceReference.setUrl(PropertiesConfig.zkUrl);
            rCGatewayServiceRetryGet(rCGatewayServiceReference);
		} catch (Exception e) {
			logger.error("RCGatewayService接口初始化异常："+e.getMessage());
			e.printStackTrace();
		}
	}
    public static void rCGatewayServiceRetryGet(ReferenceConfig<RCGatewayService.Iface> reference) {
        try {
            rCGatewayService = (RCGatewayService.Iface)reference.get();
        } catch (Exception e) {
            logger.error("RCGatewayService接口初始化异常："+e.getMessage());
            e.printStackTrace();
        }
    }
	 public static void main(String[] args) {
		//测试userDataService接口

		userDataServiceInitConfig();
		try {
//            System.out.println(userDataService.heartbeat());
			//测试getSvcs
			List<Integer> var3 = new ArrayList<>();
//			var3.add(466);

			List<AccSvc> ls3 = userDataService.getSvcs(var3);
			System.out.println("ls3 = " + ls3);
			System.out.println("-----------------------------------------------------------------");
//			//测试getKhs
//			List<Integer> var1 = new ArrayList<>();
////			var1.add(31);
//			List<AccKh>  ls = userDataService.getKhs(var1);
//			System.out.println("ls = " + ls);

			//测试updateKh
//			int khID = 22;
//			AccTerminal terminal =  new AccTerminal();
//			List< AccModule > modList = new ArrayList<>();
//			AccModule accModule = new AccModule();
//			accModule.setDigest("oooo");
//			accModule.setSize(100);
//			accModule.setName("222");
//			accModule.setEnable(1);
//			accModule.setRelativeDir("2");
//			modList.add(accModule);
//			AccModule accModule2 = new AccModule();
//			accModule2.setDigest("gggg");
//			accModule2.setSize(111);
//			accModule2.setName("333");
//			accModule2.setEnable(0);
//			accModule2.setRelativeDir("1");
//			modList.add(accModule2);
//			int rs = userDataService.updateKh(khID,terminal,modList);
//			System.out.println("rs = " + rs);

			//测试updateKhData
//			AccKh accKh = new  AccKh();
//			accKh.setKhID(22);
//			accKh.setForceCheck(0);
//			accKh.setToGtType(1);
//			Map<String, String>  map = userDataService.updateKhData(accKh);
//			System.out.println("map.toString() = " + map.toString());

		} catch (TException var3) {
			var3.printStackTrace();
		}
	}
}
