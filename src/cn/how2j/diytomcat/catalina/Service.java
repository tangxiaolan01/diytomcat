package cn.how2j.diytomcat.catalina;

import cn.how2j.diytomcat.util.ServerXMLUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.log.LogFactory;

import java.util.List;

public class Service {
    private String name;
    private Engine engine;
    private Server server;
    private List<Connector> connectors;

    public Service(Server server){
        this.name = ServerXMLUtil.getServiceName();
        this.engine = new Engine(this);
        this.server = server;
        this.connectors = ServerXMLUtil.getConnector(this);
    }

    public Engine getEngine(){
        return engine;
    }

    private void init(){
        TimeInterval timeInterval = DateUtil.timer();
        for(Connector c: connectors)
            c.init();
        LogFactory.get().info("Initialization processed in {} ms",timeInterval.intervalMs());
        for(Connector c: connectors)
            c.start();
    }

    public void start(){
        init();
    }
}
