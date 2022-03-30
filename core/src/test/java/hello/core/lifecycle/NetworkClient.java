package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient () {
        System.out.println ( "url = " + url );
        connect ();
        call ( "message" );

    }

    public void setUrl ( String url ) {
        this.url = url;
    }

    public void connect () {
        System.out.println ( "connect = " + url );
    }

    public void call ( String message ) {
        System.out.println ( "message = " + message );
    }

    public void disconnect () {
        System.out.println ( "close = " + url );
    }
}
