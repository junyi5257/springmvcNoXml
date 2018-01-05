<html>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    function requestOauthToken(username, password) {
        var success = false;
        $.ajax({
            url: 'oauth/token',
            dataType: 'json',
            type: 'post',
            headers: {'Authorization': 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'},
            contentType: 'application/x-www-form-urlencoded',
            async: false,
            data: {
                username: username,
                password: password,
                grant_type: 'password'
            },
            success: function (data) {
                /**
                 * <DefaultOAuth2AccessToken>
                 *      <access_token>9bf6d055-a067-4b44-917f-2f781797d6cf</access_token>
                 *      <token_type>bearer</token_type>
                 *      <refresh_token>74500625-8e65-4498-93b1-ae66767e6d1f</refresh_token>
                 *      <expires_in>4606</expires_in>
                 *      <scope>read write trust</scope>
                 *</DefaultOAuth2AccessToken>
                */
                localStorage.setItem('token', data.access_token);
                $(".token").html();
                $(".token").html(data.access_token);
                $(".expires_in").html();
                $(".expires_in").html(data.expires_in);
                success = true;
            },
            error: function () {
                removeOauthTokenFromStorage();
            }
        });
        return success;
    }

    function getOauthTokenFromStorage() {
        return localStorage.getItem('token');
    }

    function removeOauthTokenFromStorage() {
        return localStorage.removeItem('token');
    }
</script>
<body onload="requestOauthToken('bob','abc123')">
    <h2>Helloddd World!</h2>
    <h1>token:</h1>
    <h2 class="token"></h2>
    <h1>expires_in:</h1>
    <h2 class="expires_in"></h2>
</body>
</html>