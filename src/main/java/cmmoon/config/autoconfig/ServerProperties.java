package cmmoon.config.autoconfig;

import cmmoon.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "server")
// Bean Post Processor 만들어진 빈 오브젝트를 통해 조작할 작업 차리
public class ServerProperties {

    private String contextPath;

    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
