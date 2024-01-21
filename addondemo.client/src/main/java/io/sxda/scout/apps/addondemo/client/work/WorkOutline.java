package io.sxda.scout.apps.addondemo.client.work;

import java.util.List;

import io.sxda.scout.apps.addondemo.client.ace.AcePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;

import io.sxda.scout.apps.addondemo.client.helloworld.HelloWorldPage;
import io.sxda.scout.apps.addondemo.shared.Icons;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

/**
 * @author nisrael
 */
@Order(1000)
public class WorkOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.addAll(CollectionUtility.arrayList(
      new HelloWorldPage(),
      new AcePage()
    ));
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Work");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Pencil;
  }
}
