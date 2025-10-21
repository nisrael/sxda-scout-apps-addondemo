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
package io.sxda.scout.apps.addondemo.server.monaco;

import io.sxda.scout.apps.addondemo.shared.monaco.IMonacoService;
import io.sxda.scout.apps.addondemo.shared.monaco.MonacoFormData;

/**
 * Service implementation for Monaco form data operations.
 *
 * @author nisrael
 */
public class MonacoService implements IMonacoService {

  @Override
  public MonacoFormData load(MonacoFormData formData) {
    // Load sample JavaScript code for Monaco editor
    String sampleCode = """
        // Welcome to Monaco Editor!
        // This is a powerful code editor based on Visual Studio Code

        function greet(name) {
          console.log('Hello, ' + name + '!');
        }

        function fibonacci(n) {
          if (n <= 1) return n;
          return fibonacci(n - 1) + fibonacci(n - 2);
        }

        // Test the functions
        greet('World');
        console.log('Fibonacci(10):', fibonacci(10));

        // Array manipulation
        const numbers = [1, 2, 3, 4, 5];
        const squared = numbers.map(n => n * n);
        console.log('Squared:', squared);
        """;

    formData.getMonaco().setValue(sampleCode);
    return formData;
  }
}
