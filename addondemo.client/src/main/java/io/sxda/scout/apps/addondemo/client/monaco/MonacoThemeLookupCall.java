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
package io.sxda.scout.apps.addondemo.client.monaco;

import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Lookup call for Monaco editor theme selection.
 *
 * @author nisrael
 */
public class MonacoThemeLookupCall extends LocalLookupCall<String> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow<String>> execCreateLookupRows() {
    List<LookupRow<String>> rows = new ArrayList<>();
    rows.add(new LookupRow<>("vs", "Visual Studio Light"));
    rows.add(new LookupRow<>("vs-dark", "Visual Studio Dark"));
    rows.add(new LookupRow<>("hc-black", "High Contrast Dark"));
    rows.add(new LookupRow<>("hc-light", "High Contrast Light"));
    return rows;
  }
}
