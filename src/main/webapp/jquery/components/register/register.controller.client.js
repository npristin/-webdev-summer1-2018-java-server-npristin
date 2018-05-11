(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $registerBtn = $('#registerBtn');
        $('#registerBtn').click(register);
    }

    function register() {
        console.log('registering');

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $verifyPasswordFld = $('#verifyPasswordFld').val();

        var user = {
            username: $usernameFld,
            password: $passwordFld,
        };

        userService.register(user);
    }
})();
