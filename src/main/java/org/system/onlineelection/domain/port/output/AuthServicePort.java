package org.system.onlineelection.domain.port.output;



public interface AuthServicePort {
    void getPerson(String username, String password);
}
