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
package io.sxda.scout.apps.addondemo.shared.monaco;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * Service interface for Monaco form data operations.
 *
 * @author nisrael
 */
@TunnelToServer
public interface IMonacoService extends IService {

  /**
   * Loads the Monaco form data with initial content.
   *
   * @param formData the form data to load
   * @return the loaded form data
   */
  MonacoFormData load(MonacoFormData formData);
}
