(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserService();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $verifyPasswordFld = $('#verifyPasswordFld').val();

        $registerBtn = $('#registerBtn');
    }
    
    function register() { … }
})();
