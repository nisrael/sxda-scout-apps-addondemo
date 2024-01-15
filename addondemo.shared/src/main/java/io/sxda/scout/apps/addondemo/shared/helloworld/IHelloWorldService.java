package io.sxda.scout.apps.addondemo.shared.helloworld;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import io.sxda.scout.apps.addondemo.shared.helloworld.HelloWorldFormData;

/**
 * @author nisrael
 */
@TunnelToServer
public interface IHelloWorldService extends IService {
      HelloWorldFormData load(HelloWorldFormData input);
}
