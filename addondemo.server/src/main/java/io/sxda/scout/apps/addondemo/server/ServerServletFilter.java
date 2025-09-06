/*
 * Copyright (c) 2010-20250906-180705 BSI Business Systems Integration AG
 * Copyright (c) 2023-20250906-180705 Nils Israel
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
package io.sxda.scout.apps.addondemo.server;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.commons.authentication.DevelopmentAccessController;
import org.eclipse.scout.rt.server.commons.authentication.ServiceTunnelAccessTokenAccessController;
import org.eclipse.scout.rt.server.commons.authentication.TrivialAccessController;
import org.eclipse.scout.rt.server.commons.authentication.TrivialAccessController.TrivialAuthConfig;

/**
 * This is the main server side servlet filter.
 *
 * @author nisrael
 */
public class ServerServletFilter implements Filter {

  private TrivialAccessController m_trivialAccessController;
  private ServiceTunnelAccessTokenAccessController m_tunnelAccessController;
  private DevelopmentAccessController m_developmentAccessController;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    m_trivialAccessController = BEANS.get(TrivialAccessController.class).init(new TrivialAuthConfig().withExclusionFilter(filterConfig.getInitParameter("filter-exclude")));
    m_tunnelAccessController = BEANS.get(ServiceTunnelAccessTokenAccessController.class).init();
    m_developmentAccessController = BEANS.get(DevelopmentAccessController.class).init();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse resp = (HttpServletResponse) response;

    if (m_trivialAccessController.handle(req, resp, chain)) {
      return;
    }

    if (m_tunnelAccessController.handle(req, resp, chain)) {
      return;
    }

    if (m_developmentAccessController.handle(req, resp, chain)) {
      return;
    }

    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
  }

  @Override
  public void destroy() {
    m_developmentAccessController.destroy();
    m_tunnelAccessController.destroy();
    m_trivialAccessController.destroy();
  }
}
