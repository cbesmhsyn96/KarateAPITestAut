function fn() {
  var env = karate.env || 'dev';

  var config = {
    env: env,
    accessToken: '380c40151756632c859bbf24180a1dc42b0d750dd07989308d1714b1abf28141',
    baseUrl: 'https://gorest.co.in/public'
  };

  if (env == 'dev') {
    config.apiUrl = config.baseUrl + '/v1';
  } else {
    config.apiUrl = config.baseUrl + '/v2';
  }

  karate.configure('headers', {
    'Authorization': 'Bearer ' + config.accessToken,
    'Accept': 'application/json'
  });

  return config;
}