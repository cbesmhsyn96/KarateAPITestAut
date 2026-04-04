function fn() {
  var randomInt = Math.floor(Math.random() * 10000);

  var randomEmail = "karate_user_" + randomInt + "@portfolio.com";

  return {
    email: randomEmail,
    name: "User_" + randomInt
  };
}