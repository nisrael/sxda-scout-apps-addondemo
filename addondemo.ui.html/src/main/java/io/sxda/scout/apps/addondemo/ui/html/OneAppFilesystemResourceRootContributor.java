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
package io.sxda.scout.apps.addondemo.ui.html;

import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.shared.ui.webresource.AbstractWebResourceResolver;
import org.eclipse.scout.rt.shared.ui.webresource.DefaultFilesystemWebResourceRootContributor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Replace
public class OneAppFilesystemResourceRootContributor extends DefaultFilesystemWebResourceRootContributor {
  @Override
  public List<Path> getRoots() {
    Path moduleRoot = findModuleRootWithAgroNamingScheme();
    if (moduleRoot == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList(moduleRoot.resolve(AbstractWebResourceResolver.OUTPUT_FOLDER_NAME));
  }

  protected static Path findModuleRootWithAgroNamingScheme() {
    Path workingDir = Paths.get("").toAbsolutePath();
    Path parentDir = workingDir.getParent();
    if (parentDir == null) {
      return null;
    }

    // try module without .dev/-dev suffix
    String folderName = workingDir.getFileName().toString();
    String appModuleName = folderName;
    if (folderName.endsWith(".dev") || folderName.endsWith("-dev")) {
      appModuleName = folderName.substring(0, folderName.length() - 4);
    }
    Path resourceRoot = parentDir.resolve(appModuleName);
    if (isValidRootModule(resourceRoot)) {
      return resourceRoot;
    }

    // try module without .app/-app suffix
    if (appModuleName.endsWith(".app") || appModuleName.endsWith("-app")) {
      appModuleName = appModuleName.substring(0, appModuleName.length() - 4);
    }
    resourceRoot = parentDir.resolve(appModuleName);
    if (isValidRootModule(resourceRoot)) {
      return resourceRoot;
    }

    // try module with ui.html suffix
    // in our case, 'addondemo.app.dev' becomes 'addondemo.app' -> fail,
    // becomes 'addondemo' -> fail, becomes 'addondemo.ui.html' -> Bingo!
    appModuleName = appModuleName + ".ui.html";

    resourceRoot = parentDir.resolve(appModuleName);
    if (isValidRootModule(resourceRoot)) {
      return resourceRoot;
    }

    return workingDir;
  }
}
