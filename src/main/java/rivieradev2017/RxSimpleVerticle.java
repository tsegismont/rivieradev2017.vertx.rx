package rivieradev2017;

import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpServer;

/**
 * @author Thomas Segismont
 */
public class RxSimpleVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.rxDeployVerticle(RxSimpleVerticle.class.getName())
      .subscribe(id -> System.out.println("Deployed"), Throwable::printStackTrace);
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestHandler(request -> {
      vertx.setTimer(2000, id -> {
        request.response().end("Hello rivieradev!\n");
      });
    });

    httpServer.rxListen(8080).subscribe(server -> startFuture.complete(), startFuture::fail);
  }
}
