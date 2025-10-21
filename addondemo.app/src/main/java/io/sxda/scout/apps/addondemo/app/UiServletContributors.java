/*
 * Copyright (c) 2010-2025 BSI Business Systems Integration AG
 * Copyright (c) 2023-2025 Nils Israel
 *
 * This program is an extension of the original work from the Eclipse Scout Project,
 * available at https://www.eclipse.org/scout/.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package io.sxda.scout.apps.addondemo.app;

import org.eclipse.jetty.ee10.servlet.FilterHolder;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.scout.rt.jetty.IServletContributor;
import org.eclipse.scout.rt.jetty.IServletFilterContributor;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.ui.html.app.UiServletContributors.AuthFilterContributor;

import io.sxda.scout.apps.addondemo.ui.html.UiServletFilter;

import java.util.Arrays;
import java.util.List;

/**
 * {@link IServletContributor} and {@link IServletFilterContributor} for UI server.
 */
public final class UiServletContributors {

  private UiServletContributors() {
  }

  @Replace
  public static class UiAuthFilterContributor extends AuthFilterContributor {

    @Override
    public void contribute(ServletContextHandler handler) {
      FilterHolder filter = handler.addFilter(UiServletFilter.class, "/*", null);
      filter.setInitParameter("filter-exclude", StringUtility.join("\n", getFilterExcludes()));
    }

    @Override
    protected List<String> getFilterExcludes() {
      List<String> filterExcludes = super.getFilterExcludes();
      filterExcludes.addAll(Arrays.asList(
        "/favicon/*",
        "/fonts/*",
        "/logo.png",
        "/*login*.js",
        "/*logout*.js",
        "/*addondemo-theme*.css"));
      return filterExcludes;
    }
  }
}
