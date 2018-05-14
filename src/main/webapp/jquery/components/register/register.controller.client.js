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

        if ($passwordFld == $verifyPasswordFld) {
            var user = {
                username: $usernameFld,
                password: $passwordFld,
            };

            userService.register(user)
                .then(function (response) {
                    return response.text().then(function(text) {
                        return text ? JSON.parse(text) : {}
                    })
                }).then(function (json) {
                    if (jQuery.isEmptyObject(json)) {
                        alert("This username is already taken!");
                    } else {
                        console.log(json.id);
                        console.log("Username is available!");
                        window.location.href =
                            'https://webdev-summer1-2018-npristin.herokuapp.com/jquery/components/profile/profile.template.client.html'
                                + '?userId=' + json.id;
                    }
                });
        } else {
            alert("Passwords do not match!")
        }
    }
})();
