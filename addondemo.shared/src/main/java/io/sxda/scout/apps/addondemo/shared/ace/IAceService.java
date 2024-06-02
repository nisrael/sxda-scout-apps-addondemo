package io.sxda.scout.apps.addondemo.shared.ace;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * @author nisrael
 */
@TunnelToServer
public interface IAceService extends IService {
      AceFormData load(AceFormData input);
}
