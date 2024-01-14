package io.sxda.scout.apps.addondemo.server.helloworld;

import io.sxda.scout.apps.addondemo.server.ServerSession;
import io.sxda.scout.apps.addondemo.shared.helloworld.HelloWorldFormData;
import io.sxda.scout.apps.addondemo.shared.helloworld.IHelloWorldService;

/**
 * @author nisrael
 */
public class HelloWorldService implements IHelloWorldService {

  @Override
  public HelloWorldFormData load(HelloWorldFormData input) {
    StringBuilder msg = new StringBuilder();
    msg.append("Hello ").append(ServerSession.get().getUserId()).append('!');
    input.getMessage().setValue(msg.toString());
    return input;
  }
}
