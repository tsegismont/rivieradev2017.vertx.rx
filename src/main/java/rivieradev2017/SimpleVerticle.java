package rivieradev2017;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * @author Thomas Segismont
 */
public class SimpleVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new SimpleVerticle(), ar -> {
      if (ar.succeeded()) {
        System.out.println("Deployed");
      } else {
        ar.cause().printStackTrace();
      }
    });
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestHandler(request -> {
      vertx.setTimer(2000, id -> {
        request.response().end("Hello rivieradev!\n");
      });
    });

    httpServer.listen(8080, ar -> {
      if (ar.succeeded()) {
        startFuture.complete();
      } else {
        startFuture.fail(ar.cause());
      }
    });
  }
}
