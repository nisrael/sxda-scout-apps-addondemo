package io.sxda.scout.apps.addondemo.client;

import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.shared.services.common.code.CODES;

import io.sxda.scout.apps.addondemo.client.Desktop;

/**
 * @author nisrael
 */
public class ClientSession extends AbstractClientSession {

  public ClientSession() {
    super(true);
  }

  /**
   * @return The {@link IClientSession} which is associated with the current thread, or {@code null} if not found.
   */
  public static ClientSession get() {
    return ClientSessionProvider.currentSession(ClientSession.class);
  }

  @Override
  protected void execLoadSession() {
    //pre-load all known code types
    CODES.getAllCodeTypes("io.sxda.scout.apps.addondemo.shared");

    setDesktop(new Desktop());
  }
}
