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
 * Lookup call for Monaco editor language/mode selection.
 *
 * @author nisrael
 */
public class MonacoLanguageLookupCall extends LocalLookupCall<String> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow<String>> execCreateLookupRows() {
    List<LookupRow<String>> rows = new ArrayList<>();
    rows.add(new LookupRow<>("plaintext", "Plain Text"));
    rows.add(new LookupRow<>("javascript", "JavaScript"));
    rows.add(new LookupRow<>("typescript", "TypeScript"));
    rows.add(new LookupRow<>("json", "JSON"));
    rows.add(new LookupRow<>("html", "HTML"));
    rows.add(new LookupRow<>("css", "CSS"));
    rows.add(new LookupRow<>("java", "Java"));
    rows.add(new LookupRow<>("python", "Python"));
    rows.add(new LookupRow<>("markdown", "Markdown"));
    return rows;
  }
}
