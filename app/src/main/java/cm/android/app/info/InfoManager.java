package cm.android.app.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

public class InfoManager {

    private static final Logger logger = LoggerFactory.getLogger(InfoManager.class);

    private ExecutorService executor = null;

    private InfoManager() {
    }

    public static InfoManager getInstance() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {

        private static final InfoManager INSTANCE = new InfoManager();
    }

    //开启线程采集数据，采集完成上传至server
//    public void start() {
//        if (executor == null) {
//            executor = ThreadUtil.newCachedThreadPool();
//        }
//        executor.execute(new DeviceInfoCollector());
//    }
//
//    public void stop() {
//        executor.shutdownNow();
//        executor = null;
//    }
}
