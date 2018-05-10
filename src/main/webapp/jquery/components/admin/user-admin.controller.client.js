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
        $userRowTemplate = $('.wbdv-hidden');

        findAllUsers();

        $('.wbdv-create').click(createUser);
        $('#wbdv-remove').click(deleteUser);
        $('#wbdv-edit').click(updateUser);
    }

    function createUser() {
        console.log("create user")

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
        console.log("find users")

        fetch('http://localhost:8080/api/user')
            .then(function (response) {
                return response.json();
        }).then(renderUsers);
    }

    function findUserById() {}

    function deleteUser() {}
    function updateUser() {}
    function renderUser(user) {}

    function renderUsers(users) {
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone();
            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role)

            $tbody.append(clone);
        }
    }
})();
