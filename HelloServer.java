import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HelloServer {
    public static void main(String[] args) throws IOException {
        int port = 8081; // change if needed
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        // Use a lambda instead of method reference to avoid any parser confusion
        server.createContext("/", (HttpExchange exchange) -> {
            String response = "Hello World";
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        server.setExecutor(null);
        System.out.println("Server running on http://0.0.0.0:" + port + "/");
        server.start();
    }
}
