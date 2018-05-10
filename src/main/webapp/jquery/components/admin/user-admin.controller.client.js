(function () {
    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;

    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    $(main);

    function main() {
        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template wbdv-user wbdv-hidden');
        $('.wbdv-create').click(createUser);
        $('#wbdv-remove').click(deleteUser);
        $('#wbdv-edit').click(updateUser);

    }

    function createUser() {
        console.log("hey")

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();

        var user = {
            username: $usernameFld,
            password: $passwordFld,
            firstName: $firstNameFld,
            lastName: $lastNameFld,
            role: $roleFld
        };

        fetch('http://localhost:8080/api/user', {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function findAllUsers() {
        fetch('http://localhost:8080/api/user')
            .then(function (response) {
                return response.json();
        });
    }

    function findUserById() {}

    function deleteUser() {}
    function updateUser() {}
    function renderUser(user) {}
    function renderUsers(users) {}
})();
