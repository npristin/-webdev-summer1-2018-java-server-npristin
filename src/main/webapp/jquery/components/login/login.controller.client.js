(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $loginBtn = $('.btn-block');
        $loginBtn.click(login);
    }

    function login() {
        console.log("logging in!");

        $usernameFld = $('#username').val();
        $passwordFld = $('#password').val();

        var user = new User();
        user.setUsername($usernameFld);
        user.setPassword($passwordFld);

        console.log(user);

        userService.login(user)
            .then(function (response) {
                return response.text().then(function(text) {
                    return text ? JSON.parse(text) : {}
                })
            }).then(function (json) {
                if (jQuery.isEmptyObject(json)) {
                    alert("Invalid username and password!");
                } else {
                    var userId = json.id;
                    window.location.href =
                        'https://webdev-summer1-2018-npristin.herokuapp.com/jquery/components/profile/profile.template.client.html'
                            + '?userId=' + userId;
                }
            });
    }
})();
