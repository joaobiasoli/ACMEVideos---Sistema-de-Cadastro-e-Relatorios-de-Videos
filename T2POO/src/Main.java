import app.ACMEVideos;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ACMEVideos acmeVideos = new ACMEVideos();

        try {
            acmeVideos.processar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}