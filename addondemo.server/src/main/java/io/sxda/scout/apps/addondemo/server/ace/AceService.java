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
    input.getAce().setValue("""
package io.sxda.scout.apps.addondemo.client.ace;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;

/**
 * @author %s
 */
public class AcePage extends AbstractPageWithNodes {

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected boolean getConfiguredTableVisible() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("sxda.acePage.title");
  }

  @Override
  protected Class<? extends IForm> getConfiguredDetailForm() {
    return AceForm.class;
  }
}
    """.formatted(ServerSession.get().getUserId()));
    return input;
  }
}
