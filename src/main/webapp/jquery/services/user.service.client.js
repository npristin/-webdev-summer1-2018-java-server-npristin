function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.updateProfile = updateProfile;
    this.logout = logout;

    this.url = 'http://localhost:8080/api/user';
    this.registerUrl = 'http://localhost:8080/api/register'
    this.loginUrl = 'http://localhost:8080/api/login'
    this.updateProfileUrl = 'http://localhost:8080/api/profile'
    this.logoutUrl = 'http://localhost:8080/api/logout'
    var self = this;

    function createUser(user, callback) {
        fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function findAllUsers(callback) {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
        });
    }

    function findUserById(userId, callback) {
        return fetch(self.url + '/' + userId)
            .then(function (response) {
                return response.json();
        });
    }

    function updateUser(userId, user, callback) {
        fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function deleteUser(userId, callback) {
        fetch(self.url + '/' + userId, {
            method: 'delete'
        });
    }

    function register(user, callback) {
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function login(user, callback) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function updateProfile(user, callback) {
        fetch(self.updateProfileUrl, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function logout(callback) {
        fetch(self.logoutUrl, {
            method: 'post'
        });
    }
}
