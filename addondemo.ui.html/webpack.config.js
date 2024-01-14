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
