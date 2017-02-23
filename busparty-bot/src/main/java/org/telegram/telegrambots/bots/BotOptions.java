package org.telegram.telegrambots.bots;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Configurations for the Bot
 * @date 21 of July of 2016
 */
public class BotOptions {
    private String proxyHost;
    private int proxyPort;
    private String proxyUser;
    private String proxyPasswd;

    public BotOptions() {
    }

    public String getProxyPasswd() {
        return proxyPasswd;
    }

    public void setProxyPasswd(String proxyPasswd) {
        this.proxyPasswd = proxyPasswd;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public boolean hasProxy() {
        return proxyHost != null && !proxyHost.isEmpty() && proxyPort > 0;
    }
}
