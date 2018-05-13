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

        userService.login(user)
            .then(function (response) {
                console.log(response);
                if (response.ok) {
                    window.location.href =
                        'http://localhost:8080/jquery/components/register/register.template.client.html';
                } else {
                    alert('Invalid username and password!');
                }
        });
    }
})();
