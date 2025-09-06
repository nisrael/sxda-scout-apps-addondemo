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
const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'addondemo': './src/main/js/addondemo.ts',
    'login': './src/main/js/login.ts',
    'logout': './src/main/js/logout.ts',
    'addondemo-theme': './src/main/js/addondemo-theme.less',
    'addondemo-theme-dark': './src/main/js/addondemo-theme-dark.less'
  };

  return config;
};
