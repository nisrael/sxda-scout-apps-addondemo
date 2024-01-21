package io.sxda.scout.apps.addondemo.server.ace;

import io.sxda.scout.apps.addondemo.server.ServerSession;
import io.sxda.scout.apps.addondemo.shared.ace.AceFormData;
import io.sxda.scout.apps.addondemo.shared.ace.IAceService;
import io.sxda.scout.apps.addondemo.shared.helloworld.HelloWorldFormData;
import io.sxda.scout.apps.addondemo.shared.helloworld.IHelloWorldService;

/**
 * @author nisrael
 */
public class AceService implements IAceService {

  @Override
  public AceFormData load(AceFormData input) {
    input.getAce().setValue("#!/bin/bash\necho 'Hello %s!'\n".formatted(ServerSession.get().getUserId()));
    return input;
  }
}
