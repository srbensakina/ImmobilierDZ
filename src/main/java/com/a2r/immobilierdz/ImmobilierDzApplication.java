package com.a2r.immobilierdz;

import com.a2r.immobilierdz.utils.NgrokUtil;
import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ImmobilierDzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmobilierDzApplication.class, args);
        NgrokUtil.setupNgrok();
    /*    final NgrokClient ngrokClient = new NgrokClient.Builder().build();
        final CreateTunnel createTunnel = new CreateTunnel.Builder()
                .withAddr(8080)
                .build();
        final Tunnel tunnel = ngrokClient.connect(createTunnel);
        System.out.println("i am tunnel uri" + tunnel.getPublicUrl() ); */
    }

}
