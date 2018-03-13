package ch.makery.address.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
    name = "servers"
)
public class ServerListWrapper {
    private List<ServerData> servers;

    public ServerListWrapper() {
    }

    @XmlElement(
        name = "server"
    )
    public List<ServerData> getServers() {
        return this.servers;
    }

    public void setServers(List<ServerData> servers) {
        this.servers = servers;
    }
}
