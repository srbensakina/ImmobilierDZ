package com.a2r.immobilierdz.utils;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NgrokUtil {

    private static String ngrokUrl;

    public static String getNgrokUrl() {
        return ngrokUrl;
    }
    public static void setNgrokUrl(String url) {
        ngrokUrl = url;
    }

    public static void setupNgrok() {
        final NgrokClient ngrokClient = new NgrokClient.Builder().build();
        final CreateTunnel createTunnel = new CreateTunnel.Builder()
                .withAddr(8080)
                .build();
        final Tunnel tunnel = ngrokClient.connect(createTunnel);

        // Set the ngrok URL
        setNgrokUrl(tunnel.getPublicUrl());
    }

}
