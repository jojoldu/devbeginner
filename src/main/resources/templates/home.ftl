<a href="/login">Facebook</a>
<form action="/logout" method="post">
    <button type="submit">logout</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
<a href="/user">User</a>
<a href="/me">Me</a>