// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.

export const environment = {
  production: true,
  logDebug: false,
  apiEndpoint: 'orion.cpht.io/unison-api',
  printer_apiEndpoint: 'orion.cpht.io/printer-api',
  scanner_apiEndpoint: 'orion.cpht.io/scanner-api',
  integration_apiEndpoint: 'orion.cpht.io/integration-api',  
  batch_apiEndpoint: '448jilmzzh.execute-api.us-east-1.amazonaws.com/orion/BatchUploadAsync',
  sockRoot: 'orion.cpht.io',
  sockPath: '/unison-live/socket.io',
  sockNs: '/unison',
  auth0_client_id: 'ZewaRueG57rtsjl6n6FZgXE0yHk4wInS',
  auth0_domain: 'cpht.auth0.com',
  auth0_redirectUrl: '/login',
  auth0_integration_client_id: '7A9GFwU0K0koexqxF2cS7v0GI8maOJH5',
  auth0_delegation_route: '/delegation',
  auth0_delegation_grant_type: 'urn:ietf:params:oauth:grant-type:jwt-bearer',
  auth0_delegation_scope: 'openid roles',
  stripePublishableKey: 'pk_test_yrwCXVF8CtZM1xR9tp4IEW9700MuZEZs5p',
  storeEmailApi: '787vuqtivc.execute-api.us-east-1.amazonaws.com/orion/WebstoreEmail'
};
